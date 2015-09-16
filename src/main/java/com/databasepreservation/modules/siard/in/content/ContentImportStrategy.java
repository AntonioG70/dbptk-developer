package com.databasepreservation.modules.siard.in.content;

import com.databasepreservation.model.exception.ModuleException;
import com.databasepreservation.model.structure.DatabaseStructure;
import com.databasepreservation.modules.DatabaseExportModule;
import com.databasepreservation.modules.siard.common.SIARDArchiveContainer;

/**
 * @author Bruno Ferreira <bferreira@keep.pt>
 */
public interface ContentImportStrategy {
        void importContent(DatabaseExportModule handler, SIARDArchiveContainer container,
          DatabaseStructure databaseStructure) throws ModuleException;
}
