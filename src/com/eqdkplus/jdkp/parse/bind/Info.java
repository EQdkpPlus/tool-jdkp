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
 *         &lt;element ref="{}with_twink"/&gt;
 *         &lt;element ref="{}date"/&gt;
 *         &lt;element ref="{}timestamp"/&gt;
 *         &lt;element ref="{}total_players"/&gt;
 *         &lt;element ref="{}total_items"/&gt;
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
    "withTwink",
    "date",
    "timestamp",
    "totalPlayers",
    "totalItems"
})
@XmlRootElement(name = "info")
public class Info {

    @XmlElement(name = "with_twink")
    protected int withTwink;
    @XmlElement(required = true)
    protected String date;
    protected long timestamp;
    @XmlElement(name = "total_players")
    protected int totalPlayers;
    @XmlElement(name = "total_items")
    protected int totalItems;

    /**
     * Ruft den Wert der withTwink-Eigenschaft ab.
     * 
     */
    public int getWithTwink() {
        return withTwink;
    }

    /**
     * Legt den Wert der withTwink-Eigenschaft fest.
     * 
     */
    public void setWithTwink(int value) {
        this.withTwink = value;
    }

    /**
     * Ruft den Wert der date-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Legt den Wert der date-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Ruft den Wert der timestamp-Eigenschaft ab.
     * 
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Legt den Wert der timestamp-Eigenschaft fest.
     * 
     */
    public void setTimestamp(long value) {
        this.timestamp = value;
    }

    /**
     * Ruft den Wert der totalPlayers-Eigenschaft ab.
     * 
     */
    public int getTotalPlayers() {
        return totalPlayers;
    }

    /**
     * Legt den Wert der totalPlayers-Eigenschaft fest.
     * 
     */
    public void setTotalPlayers(int value) {
        this.totalPlayers = value;
    }

    /**
     * Ruft den Wert der totalItems-Eigenschaft ab.
     * 
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * Legt den Wert der totalItems-Eigenschaft fest.
     * 
     */
    public void setTotalItems(int value) {
        this.totalItems = value;
    }

}
