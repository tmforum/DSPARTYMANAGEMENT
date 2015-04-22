//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.24 à 11:43:05 AM CET 
//


package org.tmf.dsmapi.individual.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * <p>Classe Java pour Organization complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Organization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="href" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isLegalEntity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="existsDuring" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}ExistsDuring" minOccurs="0"/>
 *         &lt;element name="tradingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherName" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}OtherName" minOccurs="0"/>
 *         &lt;element name="characteristic" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}Characteristic" minOccurs="0"/>
 *         &lt;element name="organizationIdentification" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}OrganizationIdentification" minOccurs="0"/>
 *         &lt;element name="externalReference" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}ExternalReference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="relatedParty" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}RelatedParty" minOccurs="0"/>
 *         &lt;element name="organizationParentRelationship" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}OrganizationParentRelationship" minOccurs="0"/>
 *         &lt;element name="organizationChildRelationship" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}OrganizationChildRelationship" minOccurs="0"/>
 *         &lt;element name="contactMedium" type="{http://orange.com/api/partyManagement/tmf/v2/model/business}ContactMedium" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organization", propOrder = {
    "id",
    "href",
    "isLegalEntity",
    "type",
    "existsDuring",
    "tradingName",
    "nameType",
    "status",
    "otherName",
    "characteristic",
    "organizationIdentification",
    "externalReference",
    "relatedParty",
    "organizationParentRelationship",
    "organizationChildRelationship",
    "contactMedium"
})
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity(name = "Organization")
@Table(name = "ORGANIZATION")
@Inheritance(strategy = InheritanceType.JOINED)
public class Organization
    implements Serializable
{

    private final static long serialVersionUID = 11L;
    protected Long id;
    protected String href;
    protected String isLegalEntity;
    protected String type;
    protected ExistsDuring existsDuring;
    protected String tradingName;
    protected String nameType;
    protected String status;
    protected OtherName otherName;
    protected Characteristic characteristic;
    protected OrganizationIdentification organizationIdentification;
    protected List<ExternalReference> externalReference;
    protected RelatedParty relatedParty;
    protected OrganizationParentRelationship organizationParentRelationship;
    protected OrganizationChildRelationship organizationChildRelationship;
    protected List<ContactMedium> contactMedium;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Id
    @Column(name = "ID", scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété href.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "HREF", length = 255)
    public String getHref() {
        return href;
    }

    /**
     * Définit la valeur de la propriété href.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Obtient la valeur de la propriété isLegalEntity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "IS_LEGAL_ENTITY", length = 255)
    public String getIsLegalEntity() {
        return isLegalEntity;
    }

    /**
     * Définit la valeur de la propriété isLegalEntity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLegalEntity(String value) {
        this.isLegalEntity = value;
    }

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
     * Obtient la valeur de la propriété existsDuring.
     * 
     * @return
     *     possible object is
     *     {@link ExistsDuring }
     *     
     */
    @ManyToOne(targetEntity = ExistsDuring.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "EXISTS_DURING_ORGANIZATION_H_0")
    public ExistsDuring getExistsDuring() {
        return existsDuring;
    }

    /**
     * Définit la valeur de la propriété existsDuring.
     * 
     * @param value
     *     allowed object is
     *     {@link ExistsDuring }
     *     
     */
    public void setExistsDuring(ExistsDuring value) {
        this.existsDuring = value;
    }

    /**
     * Obtient la valeur de la propriété tradingName.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradingName(String value) {
        this.tradingName = value;
    }

    /**
     * Obtient la valeur de la propriété nameType.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameType(String value) {
        this.nameType = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "STATUS", length = 255)
    public String getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
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
     * Obtient la valeur de la propriété otherName.
     * 
     * @return
     *     possible object is
     *     {@link OtherName }
     *     
     */
    @ManyToOne(targetEntity = OtherName.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "OTHER_NAME_ORGANIZATION_HJID")
    public OtherName getOtherName() {
        return otherName;
    }

    /**
     * Définit la valeur de la propriété otherName.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherName }
     *     
     */
    public void setOtherName(OtherName value) {
        this.otherName = value;
    }

    /**
     * Obtient la valeur de la propriété characteristic.
     * 
     * @return
     *     possible object is
     *     {@link Characteristic }
     *     
     */
    @ManyToOne(targetEntity = Characteristic.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "CHARACTERISTIC_ORGANIZATION__0")
    public Characteristic getCharacteristic() {
        return characteristic;
    }

    /**
     * Définit la valeur de la propriété characteristic.
     * 
     * @param value
     *     allowed object is
     *     {@link Characteristic }
     *     
     */
    public void setCharacteristic(Characteristic value) {
        this.characteristic = value;
    }

    /**
     * Obtient la valeur de la propriété organizationIdentification.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationIdentification }
     *     
     */
    @ManyToOne(targetEntity = OrganizationIdentification.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "ORGANIZATION_IDENTIFICATION__0")
    public OrganizationIdentification getOrganizationIdentification() {
        return organizationIdentification;
    }

    /**
     * Définit la valeur de la propriété organizationIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationIdentification }
     *     
     */
    public void setOrganizationIdentification(OrganizationIdentification value) {
        this.organizationIdentification = value;
    }

    /**
     * Gets the value of the externalReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the externalReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExternalReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExternalReference }
     * 
     * 
     */
    @OneToMany(targetEntity = ExternalReference.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "EXTERNAL_REFERENCE_ORGANIZAT_0")
    public List<ExternalReference> getExternalReference() {
        if (externalReference == null) {
            externalReference = new ArrayList<ExternalReference>();
        }
        return this.externalReference;
    }

    /**
     * 
     * 
     */
    public void setExternalReference(List<ExternalReference> externalReference) {
        this.externalReference = externalReference;
    }

    /**
     * Obtient la valeur de la propriété relatedParty.
     * 
     * @return
     *     possible object is
     *     {@link RelatedParty }
     *     
     */
    @ManyToOne(targetEntity = RelatedParty.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "RELATED_PARTY_ORGANIZATION_H_0")
    public RelatedParty getRelatedParty() {
        return relatedParty;
    }

    /**
     * Définit la valeur de la propriété relatedParty.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedParty }
     *     
     */
    public void setRelatedParty(RelatedParty value) {
        this.relatedParty = value;
    }

    /**
     * Obtient la valeur de la propriété organizationParentRelationship.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationParentRelationship }
     *     
     */
    @ManyToOne(targetEntity = OrganizationParentRelationship.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "ORGANIZATION_PARENT_RELATION_1")
    public OrganizationParentRelationship getOrganizationParentRelationship() {
        return organizationParentRelationship;
    }

    /**
     * Définit la valeur de la propriété organizationParentRelationship.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationParentRelationship }
     *     
     */
    public void setOrganizationParentRelationship(OrganizationParentRelationship value) {
        this.organizationParentRelationship = value;
    }

    /**
     * Obtient la valeur de la propriété organizationChildRelationship.
     * 
     * @return
     *     possible object is
     *     {@link OrganizationChildRelationship }
     *     
     */
    @ManyToOne(targetEntity = OrganizationChildRelationship.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "ORGANIZATION_CHILD_RELATIONS_1")
    public OrganizationChildRelationship getOrganizationChildRelationship() {
        return organizationChildRelationship;
    }

    /**
     * Définit la valeur de la propriété organizationChildRelationship.
     * 
     * @param value
     *     allowed object is
     *     {@link OrganizationChildRelationship }
     *     
     */
    public void setOrganizationChildRelationship(OrganizationChildRelationship value) {
        this.organizationChildRelationship = value;
    }

    /**
     * Gets the value of the contactMedium property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactMedium property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactMedium().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactMedium }
     * 
     * 
     */
    @OneToMany(targetEntity = ContactMedium.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "CONTACT_MEDIUM_ORGANIZATION__0")
    public List<ContactMedium> getContactMedium() {
        if (contactMedium == null) {
            contactMedium = new ArrayList<ContactMedium>();
        }
        return this.contactMedium;
    }

    /**
     * 
     * 
     */
    public void setContactMedium(List<ContactMedium> contactMedium) {
        this.contactMedium = contactMedium;
    }

}
