package org.tmf.dsmapi.hub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.jaxrs.Report;
import org.tmf.dsmapi.individual.event.IndividualEvent;
import org.tmf.dsmapi.individual.event.IndividualEventTypeEnum;
import org.tmf.dsmapi.individual.model.Characteristic;
import org.tmf.dsmapi.individual.model.ContactMedium;
import org.tmf.dsmapi.individual.model.Disability;
import org.tmf.dsmapi.individual.model.ExistsDuring;
import org.tmf.dsmapi.individual.model.ExternalReference;
import org.tmf.dsmapi.individual.model.Individual;
import org.tmf.dsmapi.individual.model.IndividualIdentification;
import org.tmf.dsmapi.individual.model.Organization;
import org.tmf.dsmapi.individual.model.OrganizationChildRelationship;
import org.tmf.dsmapi.individual.model.OrganizationIdentification;
import org.tmf.dsmapi.individual.model.OrganizationParentRelationship;
import org.tmf.dsmapi.individual.model.OtherName;
import org.tmf.dsmapi.individual.model.RelatedParty;
import org.tmf.dsmapi.organization.event.OrganizationEvent;
import org.tmf.dsmapi.organization.event.OrganizationEventTypeEnum;

@Stateless
@Path("/partyManagement/v2/hub")
public class HubResource {

    @EJB
    HubFacade hubFacade;

    public HubResource() {
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(Hub entity) throws BadUsageException {
        entity.setId(null);
        hubFacade.create(entity);
        Response response = Response.ok(entity).build();
        return response;
    }

    @DELETE
    public Report deleteAllHub() {

        int previousRows = hubFacade.count();
        hubFacade.removeAll();
        int currentRows = hubFacade.count();
        int affectedRows = previousRows - currentRows;

        Report stat = new Report(currentRows);
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        return stat;
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) throws UnknownResourceException {
        hubFacade.remove(id);
    }

    @GET
    @Produces({"application/json"})
    public List<Hub> findAll() {
        return hubFacade.findAll();
    }

    @GET
    @Produces({"application/json"})
    @Path("proto")
    public Hub proto() {
        Hub hub = new Hub();
        hub.setCallback("Callback");
        hub.setQuery("queryString");
        hub.setId("id");
        return hub;
    }

    @GET
    @Produces({"application/json"})
    @Path("proto/individual/event")
    public IndividualEvent protoindividualevent() {
        IndividualEvent event = new IndividualEvent();
        IndividualEventTypeEnum x = IndividualEventTypeEnum.IndividualCreationNotification;
        event.setEventType(x);
        event.setEventTime(new Date());
        event.setId("42");
        Individual individual = new Individual();

        Date bd = new Date();
        individual.setBirthDate(bd);

        List<Characteristic> clist = new ArrayList<Characteristic>();
        individual.setCharacteristic(clist);

        List<ContactMedium> cmlist = new ArrayList<ContactMedium>();
        individual.setContactMedium(cmlist);

        individual.setCountryOfBirth("CountryOfBirth");

        List<Disability> dis = new ArrayList<Disability>();
        individual.setDisability(dis);

        List<ExternalReference> er = new ArrayList<ExternalReference>();
        individual.setExternalReference(er);

        individual.setFamilyName("FamilyName");
        individual.setFormattedName("FormattedName");
        individual.setFullName("FullName");
        individual.setGender("Gender");

        individual.setGivenName("GivenName");

        individual.setHref("Href");
        Long xxx = new Long(42);

        individual.setId(xxx);

        List<IndividualIdentification> ident = new ArrayList<IndividualIdentification>();
        individual.setIndividualIdentification(ident);

        individual.setLocation("Location");
        individual.setMaritalStatus("MaritalStatus");

        individual.setMiddleName("MiddleName");
        individual.setNationality("Nationality");

        List<OtherName> ot = new ArrayList<OtherName>();
        individual.setOtherName(ot);

        individual.setPlaceOfBirth("PlaceOfBirth");

        List<RelatedParty> rp = new ArrayList<RelatedParty>();

        individual.setRelatedParty(rp);

        individual.setStatus("Status");
        individual.setTitle("Title");

        event.setEvent(individual);
        return event;
    }
    
    @GET
    @Produces({"application/json"})
    @Path("proto/organization/event")
    public OrganizationEvent protoorganizationevent() {
        OrganizationEvent event = new OrganizationEvent();
        OrganizationEventTypeEnum eventType = OrganizationEventTypeEnum.OrganizationCreationNotification;
        event.setEventType(eventType);
        event.setEventTime(new Date());
        event.setId("42");
        Organization organization = new Organization();
        Characteristic x = new  Characteristic();
        organization.setCharacteristic(x);
        
        List<ContactMedium> cmlist  = new ArrayList<ContactMedium>();
        organization.setContactMedium(cmlist);
        
        ExistsDuring xx = new ExistsDuring();
        organization.setExistsDuring(xx);
        
        List<ExternalReference> erList  = new ArrayList<ExternalReference>();
        organization.setExternalReference(erList);
        
        
        organization.setHref("Href");
        organization.setId(new Long(42));
        organization.setIsLegalEntity("IsLegalEntity");
        organization.setNameType("NameType");
        OrganizationChildRelationship ock = new OrganizationChildRelationship();
        organization.setOrganizationChildRelationship(ock);
        
        OrganizationIdentification oid = new OrganizationIdentification();
                organization.setOrganizationIdentification(oid);
        OrganizationParentRelationship orel = new OrganizationParentRelationship();
                
        organization.setOrganizationParentRelationship(orel);
        OtherName cccc = new OtherName();
        organization.setOtherName(cccc);
        
        RelatedParty cccccc = new RelatedParty();
        organization.setRelatedParty(cccccc);
        organization.setStatus("Status");
        organization.setTradingName("TradingName");
        organization.setType("Type");
        event.setEvent(organization);
        return event;
    }

}
