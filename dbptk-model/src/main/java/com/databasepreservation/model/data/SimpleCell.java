/**
 *
 */
package com.databasepreservation.model.data;

/**
 * @author Luis Faria
 *         <p>
 *         Container of simple data
 */
public class SimpleCell extends Cell {
  private String simpledata;

  /**
   * Simple cell constructor with empty data
   *
   * @param id
   *          the cell id
   */
  public SimpleCell(String id) {
    super(id);
  }

  /**
   * Simple cell constructor
   *
   * @param id
   *          the cell id, equal to 'tableId.columnName.rowIndex'
   * @param simpledata
   *          the content of the cell
   */
  public SimpleCell(String id, String simpledata) {
    super(id);
    this.simpledata = simpledata;
  }

  /**
   * @return the content of the cell
   */
  public String getSimpledata() {
    return simpledata;
  }

  /**
   * @param simpledata
   *          the content of the cell
   */
  public void setSimpledata(String simpledata) {
    this.simpledata = simpledata;
  }

}
