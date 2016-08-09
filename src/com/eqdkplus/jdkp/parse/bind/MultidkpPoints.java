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
 *         &lt;element ref="{}multidkp_id"/&gt;
 *         &lt;element ref="{}points_current"/&gt;
 *         &lt;element ref="{}points_current_with_twink"/&gt;
 *         &lt;element ref="{}points_earned"/&gt;
 *         &lt;element ref="{}points_earned_with_twink"/&gt;
 *         &lt;element ref="{}points_spent"/&gt;
 *         &lt;element ref="{}points_spent_with_twink"/&gt;
 *         &lt;element ref="{}points_adjustment"/&gt;
 *         &lt;element ref="{}points_adjustment_with_twink"/&gt;
 *         &lt;element ref="{}points_current_html" minOccurs="0"/&gt;
 *         &lt;element ref="{}points_current_with_twink_html" minOccurs="0"/&gt;
 *         &lt;element ref="{}points_earned_html" minOccurs="0"/&gt;
 *         &lt;element ref="{}points_earned_with_twink_html" minOccurs="0"/&gt;
 *         &lt;element ref="{}points_spent_html" minOccurs="0"/&gt;
 *         &lt;element ref="{}points_spent_with_twink_html" minOccurs="0"/&gt;
 *         &lt;element ref="{}points_adjustment_html" minOccurs="0"/&gt;
 *         &lt;element ref="{}points_adjustment_with_twink_html" minOccurs="0"/&gt;
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
    "multidkpId",
    "pointsCurrent",
    "pointsCurrentWithTwink",
    "pointsEarned",
    "pointsEarnedWithTwink",
    "pointsSpent",
    "pointsSpentWithTwink",
    "pointsAdjustment",
    "pointsAdjustmentWithTwink",
    "pointsCurrentHtml",
    "pointsCurrentWithTwinkHtml",
    "pointsEarnedHtml",
    "pointsEarnedWithTwinkHtml",
    "pointsSpentHtml",
    "pointsSpentWithTwinkHtml",
    "pointsAdjustmentHtml",
    "pointsAdjustmentWithTwinkHtml"
})
@XmlRootElement(name = "multidkp_points")
public class MultidkpPoints {

    @XmlElement(name = "multidkp_id")
    protected int multidkpId;
    @XmlElement(name = "points_current")
    protected double pointsCurrent;
    @XmlElement(name = "points_current_with_twink")
    protected double pointsCurrentWithTwink;
    @XmlElement(name = "points_earned")
    protected double pointsEarned;
    @XmlElement(name = "points_earned_with_twink")
    protected double pointsEarnedWithTwink;
    @XmlElement(name = "points_spent")
    protected double pointsSpent;
    @XmlElement(name = "points_spent_with_twink")
    protected double pointsSpentWithTwink;
    @XmlElement(name = "points_adjustment")
    protected double pointsAdjustment;
    @XmlElement(name = "points_adjustment_with_twink")
    protected double pointsAdjustmentWithTwink;
    @XmlElement(name = "points_current_html")
    protected String pointsCurrentHtml;
    @XmlElement(name = "points_current_with_twink_html")
    protected String pointsCurrentWithTwinkHtml;
    @XmlElement(name = "points_earned_html")
    protected String pointsEarnedHtml;
    @XmlElement(name = "points_earned_with_twink_html")
    protected String pointsEarnedWithTwinkHtml;
    @XmlElement(name = "points_spent_html")
    protected String pointsSpentHtml;
    @XmlElement(name = "points_spent_with_twink_html")
    protected String pointsSpentWithTwinkHtml;
    @XmlElement(name = "points_adjustment_html")
    protected String pointsAdjustmentHtml;
    @XmlElement(name = "points_adjustment_with_twink_html")
    protected String pointsAdjustmentWithTwinkHtml;

    /**
     * Ruft den Wert der multidkpId-Eigenschaft ab.
     * 
     */
    public int getMultidkpId() {
        return multidkpId;
    }

    /**
     * Legt den Wert der multidkpId-Eigenschaft fest.
     * 
     */
    public void setMultidkpId(int value) {
        this.multidkpId = value;
    }

    /**
     * Ruft den Wert der pointsCurrent-Eigenschaft ab.
     * 
     */
    public double getPointsCurrent() {
        return pointsCurrent;
    }

    /**
     * Legt den Wert der pointsCurrent-Eigenschaft fest.
     * 
     */
    public void setPointsCurrent(double value) {
        this.pointsCurrent = value;
    }

    /**
     * Ruft den Wert der pointsCurrentWithTwink-Eigenschaft ab.
     * 
     */
    public double getPointsCurrentWithTwink() {
        return pointsCurrentWithTwink;
    }

    /**
     * Legt den Wert der pointsCurrentWithTwink-Eigenschaft fest.
     * 
     */
    public void setPointsCurrentWithTwink(double value) {
        this.pointsCurrentWithTwink = value;
    }

    /**
     * Ruft den Wert der pointsEarned-Eigenschaft ab.
     * 
     */
    public double getPointsEarned() {
        return pointsEarned;
    }

    /**
     * Legt den Wert der pointsEarned-Eigenschaft fest.
     * 
     */
    public void setPointsEarned(double value) {
        this.pointsEarned = value;
    }

    /**
     * Ruft den Wert der pointsEarnedWithTwink-Eigenschaft ab.
     * 
     */
    public double getPointsEarnedWithTwink() {
        return pointsEarnedWithTwink;
    }

    /**
     * Legt den Wert der pointsEarnedWithTwink-Eigenschaft fest.
     * 
     */
    public void setPointsEarnedWithTwink(double value) {
        this.pointsEarnedWithTwink = value;
    }

    /**
     * Ruft den Wert der pointsSpent-Eigenschaft ab.
     * 
     */
    public double getPointsSpent() {
        return pointsSpent;
    }

    /**
     * Legt den Wert der pointsSpent-Eigenschaft fest.
     * 
     */
    public void setPointsSpent(double value) {
        this.pointsSpent = value;
    }

    /**
     * Ruft den Wert der pointsSpentWithTwink-Eigenschaft ab.
     * 
     */
    public double getPointsSpentWithTwink() {
        return pointsSpentWithTwink;
    }

    /**
     * Legt den Wert der pointsSpentWithTwink-Eigenschaft fest.
     * 
     */
    public void setPointsSpentWithTwink(double value) {
        this.pointsSpentWithTwink = value;
    }

    /**
     * Ruft den Wert der pointsAdjustment-Eigenschaft ab.
     * 
     */
    public double getPointsAdjustment() {
        return pointsAdjustment;
    }

    /**
     * Legt den Wert der pointsAdjustment-Eigenschaft fest.
     * 
     */
    public void setPointsAdjustment(double value) {
        this.pointsAdjustment = value;
    }

    /**
     * Ruft den Wert der pointsAdjustmentWithTwink-Eigenschaft ab.
     * 
     */
    public double getPointsAdjustmentWithTwink() {
        return pointsAdjustmentWithTwink;
    }

    /**
     * Legt den Wert der pointsAdjustmentWithTwink-Eigenschaft fest.
     * 
     */
    public void setPointsAdjustmentWithTwink(double value) {
        this.pointsAdjustmentWithTwink = value;
    }

    /**
     * Ruft den Wert der pointsCurrentHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsCurrentHtml() {
        return pointsCurrentHtml;
    }

    /**
     * Legt den Wert der pointsCurrentHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsCurrentHtml(String value) {
        this.pointsCurrentHtml = value;
    }

    /**
     * Ruft den Wert der pointsCurrentWithTwinkHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsCurrentWithTwinkHtml() {
        return pointsCurrentWithTwinkHtml;
    }

    /**
     * Legt den Wert der pointsCurrentWithTwinkHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsCurrentWithTwinkHtml(String value) {
        this.pointsCurrentWithTwinkHtml = value;
    }

    /**
     * Ruft den Wert der pointsEarnedHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsEarnedHtml() {
        return pointsEarnedHtml;
    }

    /**
     * Legt den Wert der pointsEarnedHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsEarnedHtml(String value) {
        this.pointsEarnedHtml = value;
    }

    /**
     * Ruft den Wert der pointsEarnedWithTwinkHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsEarnedWithTwinkHtml() {
        return pointsEarnedWithTwinkHtml;
    }

    /**
     * Legt den Wert der pointsEarnedWithTwinkHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsEarnedWithTwinkHtml(String value) {
        this.pointsEarnedWithTwinkHtml = value;
    }

    /**
     * Ruft den Wert der pointsSpentHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsSpentHtml() {
        return pointsSpentHtml;
    }

    /**
     * Legt den Wert der pointsSpentHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsSpentHtml(String value) {
        this.pointsSpentHtml = value;
    }

    /**
     * Ruft den Wert der pointsSpentWithTwinkHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsSpentWithTwinkHtml() {
        return pointsSpentWithTwinkHtml;
    }

    /**
     * Legt den Wert der pointsSpentWithTwinkHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsSpentWithTwinkHtml(String value) {
        this.pointsSpentWithTwinkHtml = value;
    }

    /**
     * Ruft den Wert der pointsAdjustmentHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsAdjustmentHtml() {
        return pointsAdjustmentHtml;
    }

    /**
     * Legt den Wert der pointsAdjustmentHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsAdjustmentHtml(String value) {
        this.pointsAdjustmentHtml = value;
    }

    /**
     * Ruft den Wert der pointsAdjustmentWithTwinkHtml-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointsAdjustmentWithTwinkHtml() {
        return pointsAdjustmentWithTwinkHtml;
    }

    /**
     * Legt den Wert der pointsAdjustmentWithTwinkHtml-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsAdjustmentWithTwinkHtml(String value) {
        this.pointsAdjustmentWithTwinkHtml = value;
    }

}
