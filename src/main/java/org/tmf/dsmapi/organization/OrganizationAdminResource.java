package org.tmf.dsmapi.organization;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.jaxrs.Report;
import org.tmf.dsmapi.individual.model.Characteristic;
import org.tmf.dsmapi.individual.model.ExistsDuring;
import org.tmf.dsmapi.individual.model.ExternalReference;
import org.tmf.dsmapi.individual.model.Organization;
import org.tmf.dsmapi.individual.model.OrganizationIdentification;
import org.tmf.dsmapi.individual.model.OtherName;
import org.tmf.dsmapi.individual.model.RelatedParty;
import org.tmf.dsmapi.individual.model.ValidFor;
import org.tmf.dsmapi.organization.event.OrganizationEvent;
import org.tmf.dsmapi.organization.event.OrganizationEventFacade;
import org.tmf.dsmapi.organization.event.OrganizationEventPublisherLocal;

@Stateless
@Path("admin/organization")
public class OrganizationAdminResource {

    @EJB
    OrganizationFacade partyFacade;
    @EJB
    OrganizationEventFacade eventFacade;
    @EJB
    OrganizationEventPublisherLocal publisher;

    @GET
    @Produces({"application/json"})
    public List<Organization> findAll() {
        return partyFacade.findAll();
    }

    /**
     *
     * For test purpose only
     * @param entities
     * @return
     */
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response post(List<Organization> entities) {

        if (entities == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        int previousRows = partyFacade.count();
        int affectedRows;

        // Try to persist entities
        try {
            affectedRows = partyFacade.create(entities);
            for (Organization entitie : entities) {
                publisher.createNotification(entitie, new Date());
            }
        } catch (BadUsageException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        Report stat = new Report(partyFacade.count());
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        // 201 OK
        return Response.created(null).
                entity(stat).
                build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response update(@PathParam("id") long id, Organization entity) throws UnknownResourceException {
        Response response = null;
        Organization party = partyFacade.find(id);
        if (party != null) {
            entity.setId(id);
            partyFacade.edit(entity);
            publisher.valueChangedNotification(entity, new Date());
            // 201 OK + location
            response = Response.status(Response.Status.CREATED).entity(entity).build();

        } else {
            // 404 not found
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    /**
     *
     * For test purpose only
     * @return
     * @throws org.tmf.dsmapi.commons.exceptions.UnknownResourceException
     */
    @DELETE
    public Report deleteAll() throws UnknownResourceException {

        eventFacade.removeAll();
        int previousRows = partyFacade.count();
        partyFacade.removeAll();
        List<Organization> pis = partyFacade.findAll();
        for (Organization pi : pis) {
            delete(pi.getId());
        }

        int currentRows = partyFacade.count();
        int affectedRows = previousRows - currentRows;

        Report stat = new Report(currentRows);
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        return stat;
    }

    /**
     *
     * For test purpose only
     * @param id
     * @return
     * @throws UnknownResourceException
     */
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) throws UnknownResourceException {
        try {
            int previousRows = partyFacade.count();
            Organization entity = partyFacade.find(id);

            // Event deletion
            publisher.deletionNotification(entity, new Date());
            try {
                //Pause for 4 seconds to finish notification
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OrganizationAdminResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            // remove event(s) binding to the resource
            List<OrganizationEvent> events = eventFacade.findAll();
            for (OrganizationEvent event : events) {
                if (event.getEvent().getId().equals(id)) {
                    eventFacade.remove(event.getId());
                }
            }
            //remove resource
            partyFacade.remove(id);

            int affectedRows = 1;
            Report stat = new Report(partyFacade.count());
            stat.setAffectedRows(affectedRows);
            stat.setPreviousRows(previousRows);

            // 200 
            Response response = Response.ok(stat).build();
            return response;
        } catch (UnknownResourceException ex) {
            Logger.getLogger(OrganizationAdminResource.class.getName()).log(Level.SEVERE, null, ex);
            Response response = Response.status(Response.Status.NOT_FOUND).build();
            return response;
        }
    }

    @GET
    @Produces({"application/json"})
    @Path("event")
    public List<OrganizationEvent> findAllEvents() {
        return eventFacade.findAll();
    }

    @GET
    @Produces({"application/json"})
    @Path("proto")
    public Organization proto() {
        Organization organization = new Organization();
        Long xxx = new Long(128);
        organization.setId(xxx);
        organization.setHref("href");
        organization.setIsLegalEntity(true);
        organization.setType("Company");
        organization.setTradingName("Telekom");
        organization.setNameType("Co.");
        organization.setStatus("Initialized");
        
        ExistsDuring existsDuring = new ExistsDuring();
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(2000, 01, 01);
        existsDuring.setStartDateTime(gc.getTime());
        existsDuring.setEndDateTime(new Date());
        organization.setExistsDuring(existsDuring);
        
        OtherName otherName = new OtherName();
        otherName.setNameType("Co.");
        otherName.setTradingName("Orange");
        ValidFor valid = new ValidFor();
        valid.setStartDateTime(gc.getTime());
        valid.setEndDateTime(new Date());
        otherName.setValidFor(valid);
        organization.setOtherName(otherName);
        
        Characteristic charac = new Characteristic();
        charac.setName("industry");
        charac.setValue("telecom");
        organization.setCharacteristic(charac);
        
        OrganizationIdentification orgIdent = new OrganizationIdentification();
        orgIdent.setIdentificationId("374748328");
        orgIdent.setIssuingAuthority("");
        orgIdent.setType("CompanyRegistrationNumber");
        orgIdent.setValidFor(valid);
        organization.setOrganizationIdentification(orgIdent);
        
        organization.setExternalReference(new ArrayList<ExternalReference>());
        
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setHref("href");
        relatedParty.setId("12");
        relatedParty.setRole("Vendor");
        relatedParty.setName("Vendor name");
        relatedParty.setValidFor(valid);
        organization.setRelatedParty(relatedParty);
        
        return organization;
    }
    
    
    @DELETE
    @Path("event")
    public Report deleteAllEvent() {

        int previousRows = eventFacade.count();
        eventFacade.removeAll();
        int currentRows = eventFacade.count();
        int affectedRows = previousRows - currentRows;

        Report stat = new Report(currentRows);
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        return stat;
    }

    @DELETE
    @Path("event/{id}")
    public Response deleteEvent(@PathParam("id") String id) throws UnknownResourceException {

        int previousRows = eventFacade.count();
        List<OrganizationEvent> events = eventFacade.findAll();
        for (OrganizationEvent event : events) {
            if (event.getEvent().getId().equals(id)) {
                eventFacade.remove(event.getId());

            }
        }
        int currentRows = eventFacade.count();
        int affectedRows = previousRows - currentRows;

        Report stat = new Report(currentRows);
        stat.setAffectedRows(affectedRows);
        stat.setPreviousRows(previousRows);

        // 200 
        Response response = Response.ok(stat).build();
        return response;
    }

    /**
     *
     * @return
     */
    @GET
    @Path("count")
    @Produces({"application/json"})
    public Report count() {
        return new Report(partyFacade.count());
    }
}
