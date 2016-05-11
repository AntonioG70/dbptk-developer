/**
 *
 */
package com.databasepreservation.model.structure.type;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.Logger;

/**
 * @author Luis Faria
 *         <p>
 *         Abstract definition of column type. All column type implementations
 *         must extend this class.
 */
public abstract class Type {
  private static final Logger LOGGER = Logger.getLogger(Type.class);

  private String originalTypeName;

  private String description;

  private String sql99TypeName;

  private String sql2003TypeName;

  // using the empty constructor is not advised
  protected Type() {
  }

  /**
   * Type abstract constructor
   *
   * @param sql99TypeName
   *          the normalized SQL99 type name
   * @param originalTypeName
   *          the name of the original type, null if not applicable
   */
  public Type(String sql99TypeName, String originalTypeName) {
    this.originalTypeName = originalTypeName;
    this.sql99TypeName = sql99TypeName;
  }

  /**
   * @return the name of the original type, null if not applicable
   */
  public String getOriginalTypeName() {
    return originalTypeName;
  }

  /**
   * @param originalTypeName
   *          the name of the original type, null if not applicable
   */
  public void setOriginalTypeName(String originalTypeName) {
    this.originalTypeName = originalTypeName;
  }

  /**
   * @param originalTypeName
   *          The name of the original type
   * @param originalColumnSize
   *          Original column size
   * @param originalDecimalDigits
   *          Original decimal digits amount
   */
  public void setOriginalTypeName(String originalTypeName, int originalColumnSize, int originalDecimalDigits) {
    this.originalTypeName = String.format("%s(%d,%d)", originalTypeName, originalColumnSize, originalDecimalDigits);
  }

  /**
   * @param originalTypeName
   *          The name of the original type
   * @param originalColumnSize
   *          Original column size
   */
  public void setOriginalTypeName(String originalTypeName, int originalColumnSize) {
    this.originalTypeName = String.format("%s(%d)", originalTypeName, originalColumnSize);
  }

  /**
   * @return The name of the SQL99 normalized type. null if not applicable
   */
  public String getSql99TypeName() {
    if (StringUtils.isBlank(sql99TypeName)){
      setSql99fromSql2003();
    }

    if (StringUtils.isBlank(sql99TypeName)) {
      LOGGER.warn("SQL99 type is not defined for type " + this.toString());
    }
    return sql99TypeName;
  }

  /**
   * @param sql99TypeName
   *          the name of the original type, null if not applicable
   */
  public void setSql99TypeName(String sql99TypeName) {
    this.sql99TypeName = sql99TypeName;
  }

  /**
   * @param typeName
   *          The name of the original type
   * @param originalColumnSize
   *          Original column size
   * @param originalDecimalDigits
   *          Original decimal digits amount
   */
  public void setSql99TypeName(String typeName, int originalColumnSize, int originalDecimalDigits) {
    this.sql99TypeName = String.format("%s(%d,%d)", typeName, originalColumnSize, originalDecimalDigits);
  }

  /**
   * @param typeName
   *          The name of the original type
   * @param originalColumnSize
   *          Original column size
   */
  public void setSql99TypeName(String typeName, int originalColumnSize) {
    this.sql99TypeName = String.format("%s(%d)", typeName, originalColumnSize);
  }

  /**
   * @return The name of the SQL2003 normalized type. null if not applicable
   */
  public String getSql2003TypeName() {
    if (StringUtils.isBlank(sql2003TypeName)){
      setSql2003fromSql99();
    }

    if (StringUtils.isBlank(sql2003TypeName)) {
      LOGGER.warn("SQL2003 type is not defined for type " + this.toString());
    }
    return sql2003TypeName;
  }

  /**
   * @param sql2003TypeName
   *          the name of the original type, null if not applicable
   */
  public void setSql2003TypeName(String sql2003TypeName) {
    this.sql2003TypeName = sql2003TypeName;
  }

  /**
   * @param typeName
   *          The name of the original type
   * @param originalColumnSize
   *          Original column size
   * @param originalDecimalDigits
   *          Original decimal digits amount
   */
  public void setSql2003TypeName(String typeName, int originalColumnSize, int originalDecimalDigits) {
    this.sql2003TypeName = String.format("%s(%d,%d)", typeName, originalColumnSize, originalDecimalDigits);
  }

  /**
   * @param typeName
   *          The name of the original type
   * @param originalColumnSize
   *          Original column size
   */
  public void setSql2003TypeName(String typeName, int originalColumnSize) {
    this.sql2003TypeName = String.format("%s(%d)", typeName, originalColumnSize);
  }

  protected void setSql2003fromSql99(){
    // default operation, may not be accurate
    sql2003TypeName = sql99TypeName;
  }

  protected void setSql99fromSql2003(){
    // default operation, may not be accurate
    sql99TypeName = sql2003TypeName;
  }

  /**
   * @return the type description, null if none
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *          the type description, null for none
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override public String toString() {
    return "Type{" +
      "description='" + description + '\'' +
      ", originalTypeName='" + originalTypeName + '\'' +
      ", sql99TypeName='" + sql99TypeName + '\'' +
      ", sql2003TypeName='" + sql2003TypeName + '\'' +
      '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    Type type = (Type) o;

    return new EqualsBuilder().append(originalTypeName, type.originalTypeName).append(description, type.description)
      .append(sql99TypeName, type.sql99TypeName).append(sql2003TypeName, type.sql2003TypeName).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(originalTypeName).append(description).append(sql99TypeName)
      .append(sql2003TypeName).toHashCode();
  }
}
