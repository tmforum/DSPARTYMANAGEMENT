//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.24 à 11:43:05 AM CET 
//
package org.tmf.dsmapi.individual.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * <p>
 * Classe Java pour OtherName complex type.
 *
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette
 * classe.
 *
 * <pre>
 * &lt;complexType name="OtherName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="givenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="familyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formattedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFor" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}ValidFor" minOccurs="0"/>
 *         &lt;element name="nameType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherName", propOrder = {
    "title",
    "givenName",
    "familyName",
    "middleName",
    "fullName",
    "formattedName",
    "validFor",
    "nameType",
    "tradingName"
})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity(name = "OtherName")
@Table(name = "OTHER_NAME")
@Inheritance(strategy = InheritanceType.JOINED)
public class OtherName
        implements Serializable {

    private final static long serialVersionUID = 11L;
    protected String title;
    protected String givenName;
    protected String familyName;
    protected String middleName;
    protected String fullName;
    protected String formattedName;
    protected ValidFor validFor;
    protected String nameType;
    protected String tradingName;
    @JsonIgnore
    protected Long hjid;

    /**
     * Obtient la valeur de la propriété title.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "TITLE", length = 255)
    public String getTitle() {
        return title;
    }

    /**
     * Définit la valeur de la propriété title.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Obtient la valeur de la propriété givenName.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "GIVEN_NAME", length = 255)
    public String getGivenName() {
        return givenName;
    }

    /**
     * Définit la valeur de la propriété givenName.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Obtient la valeur de la propriété familyName.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "FAMILY_NAME", length = 255)
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Définit la valeur de la propriété familyName.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * Obtient la valeur de la propriété middleName.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "MIDDLE_NAME", length = 255)
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Définit la valeur de la propriété middleName.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Obtient la valeur de la propriété fullName.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "FULL_NAME", length = 255)
    public String getFullName() {
        return fullName;
    }

    /**
     * Définit la valeur de la propriété fullName.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Obtient la valeur de la propriété formattedName.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "FORMATTED_NAME", length = 255)
    public String getFormattedName() {
        return formattedName;
    }

    /**
     * Définit la valeur de la propriété formattedName.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFormattedName(String value) {
        this.formattedName = value;
    }

    /**
     * Obtient la valeur de la propriété validFor.
     *
     * @return possible object is {@link ValidFor }
     *
     */
    @ManyToOne(targetEntity = ValidFor.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "VALID_FOR_OTHER_NAME_HJID")
    public ValidFor getValidFor() {
        return validFor;
    }

    /**
     * Définit la valeur de la propriété validFor.
     *
     * @param value allowed object is {@link ValidFor }
     *
     */
    public void setValidFor(ValidFor value) {
        this.validFor = value;
    }

    /**
     * Obtient la valeur de la propriété nameType.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "NAME_TYPE", length = 255)
    public String getNameType() {
        return nameType;
    }

    /**
     * Définit la valeur de la propriété nameType.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNameType(String value) {
        this.nameType = value;
    }

    /**
     * Obtient la valeur de la propriété tradingName.
     *
     * @return possible object is {@link String }
     *
     */
    @Basic
    @Column(name = "TRADING_NAME", length = 255)
    public String getTradingName() {
        return tradingName;
    }

    /**
     * Définit la valeur de la propriété tradingName.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTradingName(String value) {
        this.tradingName = value;
    }

    /**
     * Obtient la valeur de la propriété hjid.
     *
     * @return possible object is {@link Long }
     *
     */
    @Id
    @Column(name = "HJID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.codehaus.jackson.annotate.JsonIgnore
    public Long getHjid() {
        return hjid;
    }

    /**
     * Définit la valeur de la propriété hjid.
     *
     * @param value allowed object is {@link Long }
     *
     */
    public void setHjid(Long value) {
        this.hjid = value;
    }

}
