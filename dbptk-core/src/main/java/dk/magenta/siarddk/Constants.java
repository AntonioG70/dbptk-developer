package dk.magenta.siarddk;

import java.io.File;

/**
 * @author Andreas Kring <andreas@magenta.dk>
 *
 */
public class Constants {

  // System dependent file seperator: "/" on Linux and "\" on Windows
  public static final String FILE_SEPARATOR = File.separator;

  public static final String FILE_EXTENSION_SEPARATOR = ".";

  // Extensions for files
  public static final String XML_EXTENSION = "xml";
  public static final String XSD_EXTENSION = "xsd";

  // Name of the context documentation folder within the archive
  public static final String CONTEXT_DOCUMENTATION_RELATIVE_PATH = "ContextDocumentation";

  // Path to schemas in the /src/main/resources folder
  public static final String SCHEMA_RESOURCE_FOLDER = "schema";

  // Key for context documentation folder (given on command line)
  public static final String CONTEXT_DOCUMENTATION_FOLDER = "contextDocumentationFolder";

  // Keys used in the metadata contexts
  public static final String CONTEXT_DOCUMENTATION_INDEX = "contextDocumentationIndex";
  public static final String ARCHIVE_INDEX = "archiveIndex";
  public static final String TABLE_INDEX = "tableIndex";
  public static final String FILE_INDEX = "fileIndex";
  public static final String DOC_INDEX = "docIndex";
  public static final String XML_SCHEMA = "XMLSchema";
}
