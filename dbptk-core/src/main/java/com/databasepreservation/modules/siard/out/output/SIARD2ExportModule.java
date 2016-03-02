package com.databasepreservation.modules.siard.out.output;

import java.nio.file.Path;

import com.databasepreservation.model.modules.DatabaseExportModule;
import com.databasepreservation.modules.siard.common.SIARDArchiveContainer;
import com.databasepreservation.modules.siard.common.path.MetadataPathStrategy;
import com.databasepreservation.modules.siard.common.path.SIARD2MetadataPathStrategy;
import com.databasepreservation.modules.siard.out.content.ContentExportStrategy;
import com.databasepreservation.modules.siard.out.content.SIARD2ContentExportStrategy;
import com.databasepreservation.modules.siard.out.metadata.MetadataExportStrategy;
import com.databasepreservation.modules.siard.out.metadata.SIARD2MetadataExportStrategy;
import com.databasepreservation.modules.siard.out.path.SIARD2ContentPathExportStrategy;
import com.databasepreservation.modules.siard.out.write.WriteStrategy;
import com.databasepreservation.modules.siard.out.write.ZipWriteStrategy;

/**
 * @author Bruno Ferreira <bferreira@keep.pt>
 */
public class SIARD2ExportModule {
  private final SIARD2ContentPathExportStrategy contentPathStrategy;
  private final MetadataPathStrategy metadataPathStrategy;

  private final SIARDArchiveContainer mainContainer;
  private final WriteStrategy writeStrategy;

  private final Path tableFilter;

  private MetadataExportStrategy metadataStrategy;
  private ContentExportStrategy contentStrategy;

  public SIARD2ExportModule(Path siardPackage, boolean compressZip, boolean prettyXML, Path tableFilter) {
    contentPathStrategy = new SIARD2ContentPathExportStrategy();
    metadataPathStrategy = new SIARD2MetadataPathStrategy();
    if (compressZip) {
      writeStrategy = new ZipWriteStrategy(ZipWriteStrategy.CompressionMethod.DEFLATE);
    } else {
      writeStrategy = new ZipWriteStrategy(ZipWriteStrategy.CompressionMethod.STORE);
    }
    mainContainer = new SIARDArchiveContainer(siardPackage, SIARDArchiveContainer.OutputContainerType.MAIN);

    metadataStrategy = new SIARD2MetadataExportStrategy(metadataPathStrategy, contentPathStrategy);
    contentStrategy = new SIARD2ContentExportStrategy(contentPathStrategy, writeStrategy, mainContainer, prettyXML);

    this.tableFilter = tableFilter;
  }

  public DatabaseExportModule getDatabaseHandler() {
    return new SIARDExportDefault(contentStrategy, mainContainer, writeStrategy, metadataStrategy, tableFilter);
  }
}
