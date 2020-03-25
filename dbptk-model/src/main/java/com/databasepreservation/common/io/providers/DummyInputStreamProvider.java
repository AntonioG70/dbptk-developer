/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/db-preservation-toolkit
 */
package com.databasepreservation.common.io.providers;

import java.io.InputStream;

import com.databasepreservation.model.exception.ModuleException;

/**
 * @author Miguel Guimarães <mguimaraes@keep.pt>
 */
public class DummyInputStreamProvider implements InputStreamProvider {

  @Override
  public InputStream createInputStream() throws ModuleException {
    return null;
  }

  /**
   * Free all underlying resources, except for the stream itself.
   */
  @Override
  public void cleanResources() {
    // do nothing
  }

  /**
   * @return the total number of bytes that can be provided by the InputStream
   *         created with #createInputStream
   * @throws ModuleException
   *           if the size could not be obtained
   */
  @Override
  public long getSize() throws ModuleException {
    return 0;
  }
}
