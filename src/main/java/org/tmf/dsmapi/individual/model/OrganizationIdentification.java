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
 * <p>Classe Java pour OrganizationIdentification complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OrganizationIdentification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuingAuthority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuingDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validFor" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}ValidFor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationIdentification", propOrder = {
    "type",
    "identificationId",
    "issuingAuthority",
    "issuingDate",
    "validFor"
})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity(name = "OrganizationIdentification")
@Table(name = "ORGANIZATION_IDENTIFICATION")
@Inheritance(strategy = InheritanceType.JOINED)
public class OrganizationIdentification
    implements Serializable
{

    private final static long serialVersionUID = 11L;
    protected String type;
    protected String identificationId;
    protected String issuingAuthority;
    protected String issuingDate;
    protected ValidFor validFor;
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
     * Obtient la valeur de la propriété identificationId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "IDENTIFICATION_ID", length = 255)
    public String getIdentificationId() {
        return identificationId;
    }

    /**
     * Définit la valeur de la propriété identificationId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationId(String value) {
        this.identificationId = value;
    }

    /**
     * Obtient la valeur de la propriété issuingAuthority.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "ISSUING_AUTHORITY", length = 255)
    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    /**
     * Définit la valeur de la propriété issuingAuthority.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingAuthority(String value) {
        this.issuingAuthority = value;
    }

    /**
     * Obtient la valeur de la propriété issuingDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "ISSUING_DATE", length = 255)
    public String getIssuingDate() {
        return issuingDate;
    }

    /**
     * Définit la valeur de la propriété issuingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingDate(String value) {
        this.issuingDate = value;
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
    @JoinColumn(name = "VALID_FOR_ORGANIZATION_IDENT_0")
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
