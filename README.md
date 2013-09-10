Database Preservation Toolkit
=============================

The Database Preservation Toolkit allows conversion between Database formats, including connection to live systems, for purposes of digitally preserve databases. The toolkit allows conversion of live or backed-up databases into preservation formats such as DBML, and XML format created for the purpose of database preservation. The toolkit also allows conversion of the preservation format into live systems to allow the full functionality of databases. For example, it supports a specialized export into MySQL, optimized for PhpMyAdmin, so the database can fully be experimented using a web interface.


## How to build from source

1. Download the [latest stable release](https://github.com/keeps/db-preservation-toolkit/releases).
2. Unzip and open the folder on a command-line terminal
3. Build with Maven

```bash
$ mvn clean package
```

Binaries will be on the `target` folder

## Download pre-compiled version

Binaries with all dependencies included:
* [db-preservation-toolkit-1.0.0-jar-with-dependencies.jar](https://keeps.github.io/db-preservation-toolkit/releases/db-preservation-toolkit-1.0.0-jar-with-dependencies.jar)

## How to use

To use the program, open a command-line and try out the following command:

```bash
$ java -jar db-preservation-toolkit-1.0.0-jar-with-dependencies.jar 
Synopsys: java -jar roda-common-convert-db.jar -i IMPORT_MODULE [options...] -o EXPORT_MODULE [options...]
Available import modules:
	SQLServerJDBC serverName [port|instance] database username password useIntegratedSecurity encrypt
	PostgreSQLJDBC hostName database [port] username password encrypt
	MySQLJDBC hostName [port] database username password
	Oracle8i hostName port database username password <- untested!
	MSAccess database.mdb
	ODBC source [username password]
	DBML baseDir
Available export modules:
	SQLServerJDBC serverName [port|instance] database username password useIntegratedSecurity encrypt
	PostgreSQLJDBC hostName database [port] username password encrypt
	MySQLJDBC hostName [port] database username password
	PhpMyAdmin hostName [port] database username password
	DBML baseDir
	PostgreSQLFile sqlFile <- SQL file optimized for PostgreSQL
	MySQLFile sqlFile <- SQL file optimized for MySQL
	SQLServerFile sqlFile <- SQL file optimized for SQL Server
	GenericSQLFile sqlFile <- generic SQL file
```

You have to select an input and an output module, providing for each its configuration.


For example, if you want to connect to a live MySQL database and export its content to DBML format, you can use the following command.

```bash
$ java -jar db-preservation-toolkit-1.0.0-jar-with-dependencies.jar \
-i MySQLJDBC localhost example_db username p4ssw0rd \
-o DBML example_db_dbml_export
```

## Related publications & presentations

* Presentation ["Database migration: CLI"](http://hdl.handle.net/1822/17856) by José Ramalho at "A Pratical Approach to Database Archiving", Danish National Archives, Copenhagen, Denmark, 2012-02-07.
* Presentation ["RODA: a service-oriented digital repository: database archiving"](http://hdl.handle.net/1822/17860) by José Ramalho at "A Pratical Approach to Database Archiving", Danish National Archives, Copenhagen, Denmark, 2012-02-07.
* Presentation ["RODA - Repository of Authentic Digital Objects"](http://hdl.handle.net/1822/7405) by Luis Faria at the International Workshop on Database Preservation, Edinburgh, 2007.
* José Carlos Ramalho, [Relational database preservation through XML modelling](http://hdl.handle.net/1822/7120), in proceedings of the International Workshop on Markup of Overlapping Structures (Extreme Markup 2007), Montréal, Canada, 2007.
* Marta Jacinto, [Bidirectional conversion between XML documents and relational data bases](http://hdl.handle.net/1822/601), in proceedings of the International Conference on CSCW in Design, Rio de Janeiro, 2002.
* Ricardo Freitas, [Significant properties in the preservation of relational databases](http://hdl.handle.net/1822/13702), Springer, 2010.


Other related publications:
* Neal Fitzgerald, "Using data archiving tools to preserve archival records in business systems – a case study", in proocedings of iPRES 2013, Lisbon, 2013.

## Information & Commercial support

For more information or commercial support, contact [KEEP SOLUTIONS](http://www.keep.pt/contactos/?lang=en).
