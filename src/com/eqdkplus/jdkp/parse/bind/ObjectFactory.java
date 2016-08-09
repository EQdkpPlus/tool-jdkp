//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.01.28 um 07:22:17 PM CET 
//


package com.eqdkplus.jdkp.parse.bind;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.eqdkplus.jdkp.parse.bind package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Active_QNAME = new QName("", "active");
    private final static QName _BaseLayout_QNAME = new QName("", "base_layout");
    private final static QName _ClassId_QNAME = new QName("", "class_id");
    private final static QName _ClassName_QNAME = new QName("", "class_name");
    private final static QName _Date_QNAME = new QName("", "date");
    private final static QName _Desc_QNAME = new QName("", "desc");
    private final static QName _DkpName_QNAME = new QName("", "dkp_name");
    private final static QName _Error_QNAME = new QName("", "error");
    private final static QName _EventId_QNAME = new QName("", "event_id");
    private final static QName _GameId_QNAME = new QName("", "game_id");
    private final static QName _Guild_QNAME = new QName("", "guild");
    private final static QName _Hidden_QNAME = new QName("", "hidden");
    private final static QName _Id_QNAME = new QName("", "id");
    private final static QName _ItempoolId_QNAME = new QName("", "itempool_id");
    private final static QName _Language_QNAME = new QName("", "language");
    private final static QName _Layout_QNAME = new QName("", "layout");
    private final static QName _MainId_QNAME = new QName("", "main_id");
    private final static QName _MainName_QNAME = new QName("", "main_name");
    private final static QName _MultidkpId_QNAME = new QName("", "multidkp_id");
    private final static QName _Name_QNAME = new QName("", "name");
    private final static QName _PointsAdjustment_QNAME = new QName("", "points_adjustment");
    private final static QName _PointsAdjustmentHtml_QNAME = new QName("", "points_adjustment_html");
    private final static QName _PointsAdjustmentWithTwink_QNAME = new QName("", "points_adjustment_with_twink");
    private final static QName _PointsAdjustmentWithTwinkHtml_QNAME = new QName("", "points_adjustment_with_twink_html");
    private final static QName _PointsCurrent_QNAME = new QName("", "points_current");
    private final static QName _PointsCurrentHtml_QNAME = new QName("", "points_current_html");
    private final static QName _PointsCurrentWithTwink_QNAME = new QName("", "points_current_with_twink");
    private final static QName _PointsCurrentWithTwinkHtml_QNAME = new QName("", "points_current_with_twink_html");
    private final static QName _PointsEarned_QNAME = new QName("", "points_earned");
    private final static QName _PointsEarnedHtml_QNAME = new QName("", "points_earned_html");
    private final static QName _PointsEarnedWithTwink_QNAME = new QName("", "points_earned_with_twink");
    private final static QName _PointsEarnedWithTwinkHtml_QNAME = new QName("", "points_earned_with_twink_html");
    private final static QName _PointsSpent_QNAME = new QName("", "points_spent");
    private final static QName _PointsSpentHtml_QNAME = new QName("", "points_spent_html");
    private final static QName _PointsSpentWithTwink_QNAME = new QName("", "points_spent_with_twink");
    private final static QName _PointsSpentWithTwinkHtml_QNAME = new QName("", "points_spent_with_twink_html");
    private final static QName _RaceId_QNAME = new QName("", "race_id");
    private final static QName _RaceName_QNAME = new QName("", "race_name");
    private final static QName _Reason_QNAME = new QName("", "reason");
    private final static QName _ServerName_QNAME = new QName("", "server_name");
    private final static QName _ServerLoc_QNAME = new QName("", "server_loc");
    private final static QName _Status_QNAME = new QName("", "status");
    private final static QName _Timestamp_QNAME = new QName("", "timestamp");
    private final static QName _TotalItems_QNAME = new QName("", "total_items");
    private final static QName _TotalPlayers_QNAME = new QName("", "total_players");
    private final static QName _Value_QNAME = new QName("", "value");
    private final static QName _Version_QNAME = new QName("", "version");
    private final static QName _WithTwink_QNAME = new QName("", "with_twink");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.eqdkplus.jdkp.parse.bind
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link Eqdkp }
     * 
     */
    public Eqdkp createEqdkp() {
        return new Eqdkp();
    }

    /**
     * Create an instance of {@link Game }
     * 
     */
    public Game createGame() {
        return new Game();
    }

    /**
     * Create an instance of {@link Info }
     * 
     */
    public Info createInfo() {
        return new Info();
    }

    /**
     * Create an instance of {@link Players }
     * 
     */
    public Players createPlayers() {
        return new Players();
    }

    /**
     * Create an instance of {@link Player }
     * 
     */
    public Player createPlayer() {
        return new Player();
    }

    /**
     * Create an instance of {@link Points }
     * 
     */
    public Points createPoints() {
        return new Points();
    }

    /**
     * Create an instance of {@link MultidkpPoints }
     * 
     */
    public MultidkpPoints createMultidkpPoints() {
        return new MultidkpPoints();
    }

    /**
     * Create an instance of {@link Items }
     * 
     */
    public Items createItems() {
        return new Items();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Adjustments }
     * 
     */
    public Adjustments createAdjustments() {
        return new Adjustments();
    }

    /**
     * Create an instance of {@link Adjustment }
     * 
     */
    public Adjustment createAdjustment() {
        return new Adjustment();
    }

    /**
     * Create an instance of {@link MultidkpPools }
     * 
     */
    public MultidkpPools createMultidkpPools() {
        return new MultidkpPools();
    }

    /**
     * Create an instance of {@link MultidkpPool }
     * 
     */
    public MultidkpPool createMultidkpPool() {
        return new MultidkpPool();
    }

    /**
     * Create an instance of {@link Events }
     * 
     */
    public Events createEvents() {
        return new Events();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link MdkpItempools }
     * 
     */
    public MdkpItempools createMdkpItempools() {
        return new MdkpItempools();
    }

    /**
     * Create an instance of {@link Itempools }
     * 
     */
    public Itempools createItempools() {
        return new Itempools();
    }

    /**
     * Create an instance of {@link Itempool }
     * 
     */
    public Itempool createItempool() {
        return new Itempool();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "active")
    public JAXBElement<Integer> createActive(Integer value) {
        return new JAXBElement<Integer>(_Active_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "base_layout")
    public JAXBElement<String> createBaseLayout(String value) {
        return new JAXBElement<String>(_BaseLayout_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "class_id")
    public JAXBElement<Integer> createClassId(Integer value) {
        return new JAXBElement<Integer>(_ClassId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "class_name")
    public JAXBElement<String> createClassName(String value) {
        return new JAXBElement<String>(_ClassName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "date")
    public JAXBElement<String> createDate(String value) {
        return new JAXBElement<String>(_Date_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "desc")
    public JAXBElement<String> createDesc(String value) {
        return new JAXBElement<String>(_Desc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dkp_name")
    public JAXBElement<String> createDkpName(String value) {
        return new JAXBElement<String>(_DkpName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "error")
    public JAXBElement<String> createError(String value) {
        return new JAXBElement<String>(_Error_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "event_id")
    public JAXBElement<Integer> createEventId(Integer value) {
        return new JAXBElement<Integer>(_EventId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "game_id")
    public JAXBElement<String> createGameId(String value) {
        return new JAXBElement<String>(_GameId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "guild")
    public JAXBElement<String> createGuild(String value) {
        return new JAXBElement<String>(_Guild_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "hidden")
    public JAXBElement<Integer> createHidden(Integer value) {
        return new JAXBElement<Integer>(_Hidden_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id")
    public JAXBElement<Integer> createId(Integer value) {
        return new JAXBElement<Integer>(_Id_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "itempool_id")
    public JAXBElement<Integer> createItempoolId(Integer value) {
        return new JAXBElement<Integer>(_ItempoolId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "language")
    public JAXBElement<String> createLanguage(String value) {
        return new JAXBElement<String>(_Language_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "layout")
    public JAXBElement<String> createLayout(String value) {
        return new JAXBElement<String>(_Layout_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "main_id")
    public JAXBElement<Integer> createMainId(Integer value) {
        return new JAXBElement<Integer>(_MainId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "main_name")
    public JAXBElement<String> createMainName(String value) {
        return new JAXBElement<String>(_MainName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "multidkp_id")
    public JAXBElement<Integer> createMultidkpId(Integer value) {
        return new JAXBElement<Integer>(_MultidkpId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_adjustment")
    public JAXBElement<Double> createPointsAdjustment(Double value) {
        return new JAXBElement<Double>(_PointsAdjustment_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_adjustment_html")
    public JAXBElement<String> createPointsAdjustmentHtml(String value) {
        return new JAXBElement<String>(_PointsAdjustmentHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_adjustment_with_twink")
    public JAXBElement<Double> createPointsAdjustmentWithTwink(Double value) {
        return new JAXBElement<Double>(_PointsAdjustmentWithTwink_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_adjustment_with_twink_html")
    public JAXBElement<String> createPointsAdjustmentWithTwinkHtml(String value) {
        return new JAXBElement<String>(_PointsAdjustmentWithTwinkHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_current")
    public JAXBElement<Double> createPointsCurrent(Double value) {
        return new JAXBElement<Double>(_PointsCurrent_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_current_html")
    public JAXBElement<String> createPointsCurrentHtml(String value) {
        return new JAXBElement<String>(_PointsCurrentHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_current_with_twink")
    public JAXBElement<Double> createPointsCurrentWithTwink(Double value) {
        return new JAXBElement<Double>(_PointsCurrentWithTwink_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_current_with_twink_html")
    public JAXBElement<String> createPointsCurrentWithTwinkHtml(String value) {
        return new JAXBElement<String>(_PointsCurrentWithTwinkHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_earned")
    public JAXBElement<Double> createPointsEarned(Double value) {
        return new JAXBElement<Double>(_PointsEarned_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_earned_html")
    public JAXBElement<String> createPointsEarnedHtml(String value) {
        return new JAXBElement<String>(_PointsEarnedHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_earned_with_twink")
    public JAXBElement<Double> createPointsEarnedWithTwink(Double value) {
        return new JAXBElement<Double>(_PointsEarnedWithTwink_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_earned_with_twink_html")
    public JAXBElement<String> createPointsEarnedWithTwinkHtml(String value) {
        return new JAXBElement<String>(_PointsEarnedWithTwinkHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_spent")
    public JAXBElement<Double> createPointsSpent(Double value) {
        return new JAXBElement<Double>(_PointsSpent_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_spent_html")
    public JAXBElement<String> createPointsSpentHtml(String value) {
        return new JAXBElement<String>(_PointsSpentHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_spent_with_twink")
    public JAXBElement<Double> createPointsSpentWithTwink(Double value) {
        return new JAXBElement<Double>(_PointsSpentWithTwink_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "points_spent_with_twink_html")
    public JAXBElement<String> createPointsSpentWithTwinkHtml(String value) {
        return new JAXBElement<String>(_PointsSpentWithTwinkHtml_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "race_id")
    public JAXBElement<Integer> createRaceId(Integer value) {
        return new JAXBElement<Integer>(_RaceId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "race_name")
    public JAXBElement<String> createRaceName(String value) {
        return new JAXBElement<String>(_RaceName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "reason")
    public JAXBElement<String> createReason(String value) {
        return new JAXBElement<String>(_Reason_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "server_name")
    public JAXBElement<String> createServerName(String value) {
        return new JAXBElement<String>(_ServerName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "server_loc")
    public JAXBElement<String> createServerLoc(String value) {
        return new JAXBElement<String>(_ServerLoc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "status")
    public JAXBElement<String> createStatus(String value) {
        return new JAXBElement<String>(_Status_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "timestamp")
    public JAXBElement<Long> createTimestamp(Long value) {
        return new JAXBElement<Long>(_Timestamp_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "total_items")
    public JAXBElement<Integer> createTotalItems(Integer value) {
        return new JAXBElement<Integer>(_TotalItems_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "total_players")
    public JAXBElement<Integer> createTotalPlayers(Integer value) {
        return new JAXBElement<Integer>(_TotalPlayers_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "value")
    public JAXBElement<Double> createValue(Double value) {
        return new JAXBElement<Double>(_Value_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "version")
    public JAXBElement<String> createVersion(String value) {
        return new JAXBElement<String>(_Version_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "with_twink")
    public JAXBElement<Integer> createWithTwink(Integer value) {
        return new JAXBElement<Integer>(_WithTwink_QNAME, Integer.class, null, value);
    }

}
