package pt.gov.dgarq.roda.common.convert.db.modules.postgreSql.out;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;

import pt.gov.dgarq.roda.common.convert.db.model.data.Cell;
import pt.gov.dgarq.roda.common.convert.db.model.exception.ModuleException;
import pt.gov.dgarq.roda.common.convert.db.model.structure.TableStructure;
import pt.gov.dgarq.roda.common.convert.db.model.structure.type.Type;
import pt.gov.dgarq.roda.common.convert.db.modules.jdbc.out.JDBCExportModule;
import pt.gov.dgarq.roda.common.convert.db.modules.postgreSql.PostgreSQLHelper;

/**
 * <p>
 * Module to export data to a PostgreSQL database management system via JDBC
 * driver. The postgresql-8.3-603.jdbc3.jar driver supports PostgreSQL version
 * 7.4 to 8.3.
 * </p>
 * 
 * <p>
 * To use this module, the PostgreSQL server must be configured:
 * </p>
 * <ol>
 * <li>Server must be configured to accept TCP/IP connections. This can be done
 * by setting <code>listen_addresses = 'localhost'</code> (or
 * <code>tcpip_socket = true</code> in older versions) in the postgresql.conf
 * file.</li>
 * <li>The client authentication setup in the pg_hba.conf file may need to be
 * configured, adding a line like
 * <code>host all all 127.0.0.1 255.0.0.0 trust</code>. The JDBC driver
 * supports the trust, ident, password, md5, and crypt authentication methods.
 * </li>
 * </ol>
 * 
 * @author Luis Faria
 * 
 */
public class PostgreSQLJDBCExportModule extends JDBCExportModule {

	private final Logger logger = 
			Logger.getLogger(PostgreSQLJDBCExportModule.class);
	
	private static final String POSTGRES_CONNECTION_DATABASE = "postgres";
	
	private final String hostname;
	
	private final int port;
		
	private final String database;
	
	private final String username;
	
	private final String password;
	
	private final boolean encrypt;
	
	
	/**
	 * Create a new PostgreSQL JDBC export module
	 * 
	 * @param hostname
	 *            the name of the PostgreSQL server host (e.g. localhost)
	 * @param database
	 *            the name of the database to connect to
	 * @param username
	 *            the name of the user to use in connection
	 * @param password
	 *            the password of the user to use in connection
	 * @param encrypt
	 *            encrypt connection
	 */
	public PostgreSQLJDBCExportModule(String hostname, String database,
			String username, String password, boolean encrypt) {
		super("org.postgresql.Driver", createConnectionURL(hostname, -1, 
				database, username, password, encrypt), new PostgreSQLHelper());
		this.hostname = hostname;
		this.port = -1;
		this.database = database;
		this.username = username;
		this.password = password;
		this.encrypt = encrypt;
	}

	/**
	 * Create a new PostgreSQL JDBC export module
	 * 
	 * @param hostname
	 *            the name of the PostgreSQL server host (e.g. localhost)
	 * @param port
	 *            the port of where the PostgreSQL server is listening, default
	 *            is 5432
	 * @param database
	 *            the name of the database to connect to
	 * @param username
	 *            the name of the user to use in connection
	 * @param password
	 *            the password of the user to use in connection
	 * @param encrypt
	 *            encrypt connection
	 */
	public PostgreSQLJDBCExportModule(String hostname, int port,
			String database, String username, String password, boolean encrypt) {
		super("org.postgresql.Driver", createConnectionURL(hostname, port, 
				database, username, password, encrypt), new PostgreSQLHelper());
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
		this.encrypt = encrypt;
	}
	
	public static String createConnectionURL(String hostname, int port, 
			String database, String username, String password, boolean encrypt) {
		return "jdbc:postgresql://" + hostname + (port >= 0 ? ":" + port : "") 
				+ "/" + database + "?user=" + username + "&password=" + password
				+ (encrypt ? "&ssl=true" : "");
	}
	
	public String createConnectionURL(String databaseName) {
		return createConnectionURL(hostname, port, databaseName, 
				username, password, encrypt);
	}
	
	public void initDatabase() throws ModuleException {
		String connectionURL = 
				createConnectionURL(POSTGRES_CONNECTION_DATABASE);
		
		if (canDropDatabase) {
			try {
				getConnection(POSTGRES_CONNECTION_DATABASE, connectionURL)
						.createStatement().executeUpdate(sqlHelper
								.dropDatabase(database));
			} catch (SQLException e) {
				throw new ModuleException(
						"Error droping database " + database, e);
			}
			
		} else {
			logger.debug("here");
			if (databaseExists(POSTGRES_CONNECTION_DATABASE, database, 
					connectionURL)) {
				throw new ModuleException("Cannot create database " + database 
						+ ". Please choose another name or delete the database "
						+ "'" + database + "'.");
			}
		}
		
		try {
			logger.debug("Creating database " + database);
			getConnection(POSTGRES_CONNECTION_DATABASE, connectionURL).
					createStatement().executeUpdate(
							sqlHelper.createDatabaseSQL(database));
			
		} catch (SQLException e) {
			throw new ModuleException("Error creating database " + database, e);
		}
	}
	
	public void handleDataCloseTable(String tableId) throws ModuleException {
		try {
			TableStructure table = 
					databaseStructure.lookupTableStructure(tableId);
			table.getSchema().setNewSchemaName(replacedPrefix);			
			logger.debug("table ID: " + currentTableStructure.getId());
			getStatement().executeUpdate(
					((PostgreSQLHelper) getSqlHelper()).grantPermissionsSQL(
							currentTableStructure.getId()));
			table.getSchema().setOriginalSchemaName();
		} catch (SQLException e) {
			throw new ModuleException(
					"Error granting permissions to public", e);
		}
		super.handleDataCloseTable(tableId);
	}
	
	protected void handleSimpleTypeNumericApproximateDataCell(String data,
			PreparedStatement ps, int index, Cell cell, Type type) 
					throws NumberFormatException, SQLException {
		if (data != null) {
			logger.debug("set approx: " + data);
			if (type.getSql99TypeName().equalsIgnoreCase("FLOAT")) {
				ps.setFloat(index, Float.valueOf(data));
			} else {
				ps.setDouble(index, Double.valueOf(data));
			}
		} else {
			ps.setNull(index, Types.FLOAT);
		}		
	}
}
