package com.databasepreservation.modules.siard.out.content;

import com.databasepreservation.model.data.Row;
import com.databasepreservation.model.exception.ModuleException;
import com.databasepreservation.model.structure.SchemaStructure;
import com.databasepreservation.model.structure.TableStructure;

/**
 * @author Bruno Ferreira <bferreira@keep.pt>
 */
public interface ContentExportStrategy {
        void openTable(SchemaStructure schema, TableStructure table) throws ModuleException;

        void closeTable(SchemaStructure schema, TableStructure table) throws ModuleException;

        void tableRow(Row row) throws ModuleException;
}
