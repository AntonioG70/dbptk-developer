/**
 * Factory for setting up SIARDDK strategies
 * 
 * @author Andreas Kring <andreas@magenta.dk>
 * 
 */

package dk.magenta.siarddk;

import java.nio.file.Path;
import java.util.List;

import com.databasepreservation.modules.DatabaseHandler;
import com.databasepreservation.modules.siard.common.SIARDArchiveContainer;
import com.databasepreservation.modules.siard.common.path.MetadataPathStrategy;
import com.databasepreservation.modules.siard.out.content.ContentExportStrategy;
import com.databasepreservation.modules.siard.out.metadata.MetadataExportStrategy;
import com.databasepreservation.modules.siard.out.output.SIARDExportDefault;
import com.databasepreservation.modules.siard.out.path.ContentPathExportStrategy;
import com.databasepreservation.modules.siard.out.write.FolderWriteStrategy;
import com.databasepreservation.modules.siard.out.write.WriteStrategy;

import dk.magenta.common.SIARDMarshaller;
import dk.magenta.common.StandardSIARDMarshaller;

public class SIARDDKExportModule {

	private MetadataExportStrategy metadataExportStrategy;
	private SIARDArchiveContainer mainContainer;
	private ContentExportStrategy contentExportStrategy;
	private WriteStrategy writeStrategy;
	private ContentPathExportStrategy contentPathExportStrategy;
	private MetadataPathStrategy metadataPathStrategy;
	private SIARDMarshaller siardMarshaller;
	
	private List<String> exportModuleArgs;
	private FileIndexFileStrategy fileIndexFileStrategy;
	
	public SIARDDKExportModule(Path siardPackage, List<String> exportModuleArgs) {
		this.exportModuleArgs = exportModuleArgs;
		
		mainContainer = new SIARDArchiveContainer(siardPackage, SIARDArchiveContainer.OutputContainerType.INSIDE_ARCHIVE);
		writeStrategy = new FolderWriteStrategy();
		siardMarshaller = new StandardSIARDMarshaller();
		fileIndexFileStrategy = new FileIndexFileStrategy(this);
		contentPathExportStrategy = new SIARDDKContentExportPathStrategy();
		metadataPathStrategy = new SIARDDKMetadataPathStrategy();
		metadataExportStrategy = new SIARDDKMetadataExportStrategy(this);
		contentExportStrategy = new SIARDDKContentExportStrategy(this);
	}
	
	public DatabaseHandler getDatabaseHandler() {
		return new SIARDExportDefault(contentExportStrategy, mainContainer, writeStrategy, metadataExportStrategy);
	}
	
	public List<String> getExportModuleArgs() {
		return exportModuleArgs;
	}

	public WriteStrategy getWriteStrategy() {
		return writeStrategy;
	}
	
	public FileIndexFileStrategy getFileIndexFileStrategy() {
		return fileIndexFileStrategy;
	}
	
	public SIARDMarshaller getSiardMarshaller() {
		return siardMarshaller;
	}
	
	public MetadataPathStrategy getMetadataPathStrategy() {
		return metadataPathStrategy;
	}
	
	public ContentPathExportStrategy getContentExportStrategy() {
		return contentPathExportStrategy;
	}
	
	public SIARDArchiveContainer getMainContainer() {
		return mainContainer;
	}
}
