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
 *         &lt;element ref="{}eqdkp" minOccurs="0"/&gt;
 *         &lt;element ref="{}game" minOccurs="0"/&gt;
 *         &lt;element ref="{}info" minOccurs="0"/&gt;
 *         &lt;element ref="{}players" minOccurs="0"/&gt;
 *         &lt;element ref="{}multidkp_pools" minOccurs="0"/&gt;
 *         &lt;element ref="{}itempools" minOccurs="0"/&gt;
 *         &lt;element ref="{}status"/&gt;
 *         &lt;element ref="{}error" minOccurs="0"/&gt;
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
    "eqdkp",
    "game",
    "info",
    "players",
    "multidkpPools",
    "itempools",
    "status",
    "error"
})
@XmlRootElement(name = "response")
public class Response {

    protected Eqdkp eqdkp;
    protected Game game;
    protected Info info;
    protected Players players;
    @XmlElement(name = "multidkp_pools")
    protected MultidkpPools multidkpPools;
    protected Itempools itempools;
    @XmlElement(required = true)
    protected String status;
    protected String error;

    /**
     * Ruft den Wert der eqdkp-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Eqdkp }
     *     
     */
    public Eqdkp getEqdkp() {
        return eqdkp;
    }

    /**
     * Legt den Wert der eqdkp-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Eqdkp }
     *     
     */
    public void setEqdkp(Eqdkp value) {
        this.eqdkp = value;
    }

    /**
     * Ruft den Wert der game-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Game }
     *     
     */
    public Game getGame() {
        return game;
    }

    /**
     * Legt den Wert der game-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Game }
     *     
     */
    public void setGame(Game value) {
        this.game = value;
    }

    /**
     * Ruft den Wert der info-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Info }
     *     
     */
    public Info getInfo() {
        return info;
    }

    /**
     * Legt den Wert der info-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Info }
     *     
     */
    public void setInfo(Info value) {
        this.info = value;
    }

    /**
     * Ruft den Wert der players-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Players }
     *     
     */
    public Players getPlayers() {
        return players;
    }

    /**
     * Legt den Wert der players-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Players }
     *     
     */
    public void setPlayers(Players value) {
        this.players = value;
    }

    /**
     * Ruft den Wert der multidkpPools-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MultidkpPools }
     *     
     */
    public MultidkpPools getMultidkpPools() {
        return multidkpPools;
    }

    /**
     * Legt den Wert der multidkpPools-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MultidkpPools }
     *     
     */
    public void setMultidkpPools(MultidkpPools value) {
        this.multidkpPools = value;
    }

    /**
     * Ruft den Wert der itempools-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Itempools }
     *     
     */
    public Itempools getItempools() {
        return itempools;
    }

    /**
     * Legt den Wert der itempools-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Itempools }
     *     
     */
    public void setItempools(Itempools value) {
        this.itempools = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Ruft den Wert der error-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Legt den Wert der error-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

}
