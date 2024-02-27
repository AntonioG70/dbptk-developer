/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE file at the root of the source
 * tree and available online at
 *
 * https://github.com/keeps/db-preservation-toolkit
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.21 at 01:40:35 PM CEST 
//

package dk.sa.xmlns.diark._1_0.docindex;

import java.math.BigInteger;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.NormalizedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * A type for a document
 * 
 * <p>
 * Java class for documentType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="documentType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dID" type="{http://www.sa.dk/xmlns/diark/1.0}documentIDType"/&gt;
 *         &lt;element name="pID" type="{http://www.sa.dk/xmlns/diark/1.0}documentIDType" minOccurs="0"/&gt;
 *         &lt;element name="mID" type="{http://www.sa.dk/xmlns/diark/1.0}mediaIDType"/&gt;
 *         &lt;element name="dCf" type="{http://www.sa.dk/xmlns/diark/1.0}dCfType"/&gt;
 *         &lt;element name="oFn" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/&gt;
 *         &lt;element name="aFt" type="{http://www.sa.dk/xmlns/diark/1.0}archivalFileType"/&gt;
 *         &lt;element name="gmlXsd" type="{http://www.sa.dk/xmlns/diark/1.0}gmlXsdType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentType", propOrder = {"did", "pid", "mid", "dCf", "oFn", "aFt", "gmlXsd"})
public class DocumentType {

  @XmlElement(name = "dID", required = true)
  @XmlSchemaType(name = "positiveInteger")
  protected BigInteger did;
  @XmlElementRef(name = "pID", namespace = "http://www.sa.dk/xmlns/diark/1.0", type = JAXBElement.class, required = false)
  protected JAXBElement<BigInteger> pid;
  @XmlElement(name = "mID", required = true)
  @XmlSchemaType(name = "positiveInteger")
  protected BigInteger mid;
  @XmlElement(required = true)
  protected String dCf;
  @XmlElement(required = true)
  @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
  @XmlSchemaType(name = "normalizedString")
  protected String oFn;
  @XmlElement(required = true)
  protected String aFt;
  protected String gmlXsd;

  /**
   * Gets the value of the did property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getDID() {
    return did;
  }

  /**
   * Sets the value of the did property.
   * 
   * @param value
   *          allowed object is {@link BigInteger }
   * 
   */
  public void setDID(BigInteger value) {
    this.did = value;
  }

  /**
   * Gets the value of the pid property.
   * 
   * @return possible object is {@link JAXBElement }{@code <}{@link BigInteger }
   *         {@code >}
   * 
   */
  public JAXBElement<BigInteger> getPID() {
    return pid;
  }

  /**
   * Sets the value of the pid property.
   * 
   * @param value
   *          allowed object is {@link JAXBElement }{@code <}{@link BigInteger }
   *          {@code >}
   * 
   */
  public void setPID(JAXBElement<BigInteger> value) {
    this.pid = value;
  }

  /**
   * Gets the value of the mid property.
   * 
   * @return possible object is {@link BigInteger }
   * 
   */
  public BigInteger getMID() {
    return mid;
  }

  /**
   * Sets the value of the mid property.
   * 
   * @param value
   *          allowed object is {@link BigInteger }
   * 
   */
  public void setMID(BigInteger value) {
    this.mid = value;
  }

  /**
   * Gets the value of the dCf property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDCf() {
    return dCf;
  }

  /**
   * Sets the value of the dCf property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setDCf(String value) {
    this.dCf = value;
  }

  /**
   * Gets the value of the oFn property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOFn() {
    return oFn;
  }

  /**
   * Sets the value of the oFn property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setOFn(String value) {
    this.oFn = value;
  }

  /**
   * Gets the value of the aFt property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getAFt() {
    return aFt;
  }

  /**
   * Sets the value of the aFt property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setAFt(String value) {
    this.aFt = value;
  }

  /**
   * Gets the value of the gmlXsd property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getGmlXsd() {
    return gmlXsd;
  }

  /**
   * Sets the value of the gmlXsd property.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setGmlXsd(String value) {
    this.gmlXsd = value;
  }

}
