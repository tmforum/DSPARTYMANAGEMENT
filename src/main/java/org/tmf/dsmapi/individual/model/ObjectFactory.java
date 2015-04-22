//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.24 à 11:43:05 AM CET 
//


package org.tmf.dsmapi.individual.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tmf.dsmapi.individual.model package. 
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

    private final static QName _Characteristic_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "Characteristic");
    private final static QName _Medium_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "Medium");
    private final static QName _Individual_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "Individual");
    private final static QName _ContactMedium_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "ContactMedium");
    private final static QName _ValidFor_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "ValidFor");
    private final static QName _Organization_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "Organization");
    private final static QName _Disability_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "Disability");
    private final static QName _IndividualIdentification_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "IndividualIdentification");
    private final static QName _ExternalReference_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "ExternalReference");
    private final static QName _OrganizationIdentification_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "OrganizationIdentification");
    private final static QName _RelatedParty_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "RelatedParty");
    private final static QName _ExistsDuring_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "ExistsDuring");
    private final static QName _OtherName_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "OtherName");
    private final static QName _OrganizationParentRelationship_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "OrganizationParentRelationship");
    private final static QName _OrganizationChildRelationship_QNAME = new QName("http://orange.com/api/partyManagement/tmf/v2/model/business", "OrganizationChildRelationship");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tmf.dsmapi.individual.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Characteristic }
     * 
     */
    public Characteristic createCharacteristic() {
        return new Characteristic();
    }

    /**
     * Create an instance of {@link Medium }
     * 
     */
    public Medium createMedium() {
        return new Medium();
    }

    /**
     * Create an instance of {@link ContactMedium }
     * 
     */
    public ContactMedium createContactMedium() {
        return new ContactMedium();
    }

    /**
     * Create an instance of {@link ValidFor }
     * 
     */
    public ValidFor createValidFor() {
        return new ValidFor();
    }

    /**
     * Create an instance of {@link Organization }
     * 
     */
    public Organization createOrganization() {
        return new Organization();
    }

    /**
     * Create an instance of {@link Disability }
     * 
     */
    public Disability createDisability() {
        return new Disability();
    }

    /**
     * Create an instance of {@link IndividualIdentification }
     * 
     */
    public IndividualIdentification createIndividualIdentification() {
        return new IndividualIdentification();
    }

    /**
     * Create an instance of {@link Individual }
     * 
     */
    public Individual createIndividual() {
        return new Individual();
    }

    /**
     * Create an instance of {@link OrganizationIdentification }
     * 
     */
    public OrganizationIdentification createOrganizationIdentification() {
        return new OrganizationIdentification();
    }

    /**
     * Create an instance of {@link RelatedParty }
     * 
     */
    public RelatedParty createRelatedParty() {
        return new RelatedParty();
    }

    /**
     * Create an instance of {@link ExternalReference }
     * 
     */
    public ExternalReference createExternalReference() {
        return new ExternalReference();
    }

    /**
     * Create an instance of {@link OrganizationParentRelationship }
     * 
     */
    public OrganizationParentRelationship createOrganizationParentRelationship() {
        return new OrganizationParentRelationship();
    }

    /**
     * Create an instance of {@link OtherName }
     * 
     */
    public OtherName createOtherName() {
        return new OtherName();
    }

    /**
     * Create an instance of {@link OrganizationChildRelationship }
     * 
     */
    public OrganizationChildRelationship createOrganizationChildRelationship() {
        return new OrganizationChildRelationship();
    }

    /**
     * Create an instance of {@link ExistsDuring }
     * 
     */
    public ExistsDuring createExistsDuring() {
        return new ExistsDuring();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Characteristic }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "Characteristic")
    public JAXBElement<Characteristic> createCharacteristic(Characteristic value) {
        return new JAXBElement<Characteristic>(_Characteristic_QNAME, Characteristic.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Medium }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "Medium")
    public JAXBElement<Medium> createMedium(Medium value) {
        return new JAXBElement<Medium>(_Medium_QNAME, Medium.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Individual }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "Individual")
    public JAXBElement<Individual> createIndividual(Individual value) {
        return new JAXBElement<Individual>(_Individual_QNAME, Individual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContactMedium }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "ContactMedium")
    public JAXBElement<ContactMedium> createContactMedium(ContactMedium value) {
        return new JAXBElement<ContactMedium>(_ContactMedium_QNAME, ContactMedium.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidFor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "ValidFor")
    public JAXBElement<ValidFor> createValidFor(ValidFor value) {
        return new JAXBElement<ValidFor>(_ValidFor_QNAME, ValidFor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "Organization")
    public JAXBElement<Organization> createOrganization(Organization value) {
        return new JAXBElement<Organization>(_Organization_QNAME, Organization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Disability }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "Disability")
    public JAXBElement<Disability> createDisability(Disability value) {
        return new JAXBElement<Disability>(_Disability_QNAME, Disability.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndividualIdentification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "IndividualIdentification")
    public JAXBElement<IndividualIdentification> createIndividualIdentification(IndividualIdentification value) {
        return new JAXBElement<IndividualIdentification>(_IndividualIdentification_QNAME, IndividualIdentification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExternalReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "ExternalReference")
    public JAXBElement<ExternalReference> createExternalReference(ExternalReference value) {
        return new JAXBElement<ExternalReference>(_ExternalReference_QNAME, ExternalReference.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationIdentification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "OrganizationIdentification")
    public JAXBElement<OrganizationIdentification> createOrganizationIdentification(OrganizationIdentification value) {
        return new JAXBElement<OrganizationIdentification>(_OrganizationIdentification_QNAME, OrganizationIdentification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelatedParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "RelatedParty")
    public JAXBElement<RelatedParty> createRelatedParty(RelatedParty value) {
        return new JAXBElement<RelatedParty>(_RelatedParty_QNAME, RelatedParty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistsDuring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "ExistsDuring")
    public JAXBElement<ExistsDuring> createExistsDuring(ExistsDuring value) {
        return new JAXBElement<ExistsDuring>(_ExistsDuring_QNAME, ExistsDuring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "OtherName")
    public JAXBElement<OtherName> createOtherName(OtherName value) {
        return new JAXBElement<OtherName>(_OtherName_QNAME, OtherName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationParentRelationship }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "OrganizationParentRelationship")
    public JAXBElement<OrganizationParentRelationship> createOrganizationParentRelationship(OrganizationParentRelationship value) {
        return new JAXBElement<OrganizationParentRelationship>(_OrganizationParentRelationship_QNAME, OrganizationParentRelationship.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationChildRelationship }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://orange.com/api/partyManagement/tmf/v2/model/business", name = "OrganizationChildRelationship")
    public JAXBElement<OrganizationChildRelationship> createOrganizationChildRelationship(OrganizationChildRelationship value) {
        return new JAXBElement<OrganizationChildRelationship>(_OrganizationChildRelationship_QNAME, OrganizationChildRelationship.class, null, value);
    }

}
