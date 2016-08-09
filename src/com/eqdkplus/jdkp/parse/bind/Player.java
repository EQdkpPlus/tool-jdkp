//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.01.28 um 07:22:17 PM CET 
//


package com.eqdkplus.jdkp.parse.bind;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}id"/&gt;
 *         &lt;element ref="{}name"/&gt;
 *         &lt;element ref="{}active"/&gt;
 *         &lt;element ref="{}hidden"/&gt;
 *         &lt;element ref="{}main_id"/&gt;
 *         &lt;element ref="{}main_name"/&gt;
 *         &lt;element ref="{}class_id"/&gt;
 *         &lt;element ref="{}class_name"/&gt;
 *         &lt;element ref="{}race_id" minOccurs="0"/&gt;
 *         &lt;element ref="{}race_name" minOccurs="0"/&gt;
 *         &lt;element ref="{}points"/&gt;
 *         &lt;element ref="{}items"/&gt;
 *         &lt;element ref="{}adjustments" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "name",
    "active",
    "hidden",
    "mainId",
    "mainName",
    "classId",
    "className",
    "raceId",
    "raceName",
    "points",
    "items",
    "adjustments"
})
@XmlRootElement(name = "player")
public class Player {

    protected int id;
    @XmlElement(required = true)
    protected String name;
    protected int active;
    protected int hidden;
    @XmlElement(name = "main_id")
    protected int mainId;
    @XmlElement(name = "main_name", required = true)
    protected String mainName;
    @XmlElement(name = "class_id")
    protected int classId;
    @XmlElement(name = "class_name", required = true)
    protected String className;
    @XmlElement(name = "race_id")
    protected Integer raceId;
    @XmlElement(name = "race_name")
    protected String raceName;
    @XmlElement(required = true)
    protected Points points;
    @XmlElement(required = true)
    protected Items items;
    protected Adjustments adjustments;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der active-Eigenschaft ab.
     * 
     */
    public int getActive() {
        return active;
    }

    /**
     * Legt den Wert der active-Eigenschaft fest.
     * 
     */
    public void setActive(int value) {
        this.active = value;
    }

    /**
     * Ruft den Wert der hidden-Eigenschaft ab.
     * 
     */
    public int getHidden() {
        return hidden;
    }

    /**
     * Legt den Wert der hidden-Eigenschaft fest.
     * 
     */
    public void setHidden(int value) {
        this.hidden = value;
    }

    /**
     * Ruft den Wert der mainId-Eigenschaft ab.
     * 
     */
    public int getMainId() {
        return mainId;
    }

    /**
     * Legt den Wert der mainId-Eigenschaft fest.
     * 
     */
    public void setMainId(int value) {
        this.mainId = value;
    }

    /**
     * Ruft den Wert der mainName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainName() {
        return mainName;
    }

    /**
     * Legt den Wert der mainName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainName(String value) {
        this.mainName = value;
    }

    /**
     * Ruft den Wert der classId-Eigenschaft ab.
     * 
     */
    public int getClassId() {
        return classId;
    }

    /**
     * Legt den Wert der classId-Eigenschaft fest.
     * 
     */
    public void setClassId(int value) {
        this.classId = value;
    }

    /**
     * Ruft den Wert der className-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Legt den Wert der className-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Ruft den Wert der raceId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRaceId() {
        return raceId;
    }

    /**
     * Legt den Wert der raceId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRaceId(Integer value) {
        this.raceId = value;
    }

    /**
     * Ruft den Wert der raceName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaceName() {
        return raceName;
    }

    /**
     * Legt den Wert der raceName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaceName(String value) {
        this.raceName = value;
    }

    /**
     * Ruft den Wert der points-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Points }
     *     
     */
    public Points getPoints() {
        return points;
    }

    /**
     * Legt den Wert der points-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Points }
     *     
     */
    public void setPoints(Points value) {
        this.points = value;
    }

    /**
     * Ruft den Wert der items-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Items }
     *     
     */
    public Items getItems() {
        return items;
    }

    /**
     * Legt den Wert der items-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Items }
     *     
     */
    public void setItems(Items value) {
        this.items = value;
    }

    /**
     * Ruft den Wert der adjustments-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Adjustments }
     *     
     */
    public Adjustments getAdjustments() {
        return adjustments;
    }

    /**
     * Legt den Wert der adjustments-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Adjustments }
     *     
     */
    public void setAdjustments(Adjustments value) {
        this.adjustments = value;
    }

}
