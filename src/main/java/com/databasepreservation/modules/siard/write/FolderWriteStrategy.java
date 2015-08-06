package com.databasepreservation.modules.siard.write;

import com.databasepreservation.model.exception.ModuleException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Bruno Ferreira <bferreira@keep.pt>
 */
public class FolderWriteStrategy implements WriteStrategy {
	@Override
	public OutputStream createOutputStream(OutputContainer container, String path) throws ModuleException {
		Path filepath = container.getPath().resolve(path);

		if( !Files.exists(filepath) ){
			try {
				if( !Files.exists(filepath.getParent()) ){
					Files.createDirectories(filepath.getParent());
				}
				Files.createFile(filepath);
			} catch (IOException e) {
				throw new ModuleException("Error while creating the file: " + filepath.toString(),e);
			}
		}

		try {
			return Files.newOutputStream(filepath);
		} catch (IOException e) {
			throw new ModuleException("Error while getting the file: " + filepath.toString(),e);
		}
	}

	@Override
	public boolean isSimultaneousWritingSupported() {
		return true;
	}

	@Override
	public void finish(OutputContainer baseContainer) throws ModuleException {
		// nothing to do
	}

	@Override
	public void setup(OutputContainer baseContainer) throws ModuleException {
		// nothing to do
	}
}
