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
 * <p>Classe Java pour ContactMedium complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ContactMedium">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFor" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}ValidFor" minOccurs="0"/>
 *         &lt;element name="medium" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}Medium" minOccurs="0"/>
 *         &lt;element name="preferred" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactMedium", propOrder = {
    "type",
    "validFor",
    "medium",
    "preferred"
})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity(name = "ContactMedium")
@Table(name = "CONTACT_MEDIUM")
@Inheritance(strategy = InheritanceType.JOINED)
public class ContactMedium
    implements Serializable
{

    private final static long serialVersionUID = 11L;
    protected String type;
    protected ValidFor validFor;
    protected Medium medium;
    protected String preferred;
    @JsonIgnore
    protected Long hjid;

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "TYPE_", length = 255)
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété validFor.
     * 
     * @return
     *     possible object is
     *     {@link ValidFor }
     *     
     */
    @ManyToOne(targetEntity = ValidFor.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "VALID_FOR_CONTACT_MEDIUM_HJID")
    public ValidFor getValidFor() {
        return validFor;
    }

    /**
     * Définit la valeur de la propriété validFor.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidFor }
     *     
     */
    public void setValidFor(ValidFor value) {
        this.validFor = value;
    }

    /**
     * Obtient la valeur de la propriété medium.
     * 
     * @return
     *     possible object is
     *     {@link Medium }
     *     
     */
    @ManyToOne(targetEntity = Medium.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "MEDIUM_CONTACT_MEDIUM_HJID")
    public Medium getMedium() {
        return medium;
    }

    /**
     * Définit la valeur de la propriété medium.
     * 
     * @param value
     *     allowed object is
     *     {@link Medium }
     *     
     */
    public void setMedium(Medium value) {
        this.medium = value;
    }

    /**
     * Obtient la valeur de la propriété preferred.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "PREFERRED", length = 255)
    public String getPreferred() {
        return preferred;
    }

    /**
     * Définit la valeur de la propriété preferred.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferred(String value) {
        this.preferred = value;
    }

    /**
     * Obtient la valeur de la propriété hjid.
     * 
     * @return
     *     possible object is
     *     {@link Long }
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
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHjid(Long value) {
        this.hjid = value;
    }

}
