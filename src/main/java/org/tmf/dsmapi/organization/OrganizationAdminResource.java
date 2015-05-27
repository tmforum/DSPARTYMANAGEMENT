package org.tmf.dsmapi.organization;

import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.jaxrs.Report;
import org.tmf.dsmapi.individual.model.Characteristic;
import org.tmf.dsmapi.individual.model.ContactMedium;
import org.tmf.dsmapi.individual.model.ExistsDuring;
import org.tmf.dsmapi.individual.model.ExternalReference;
import org.tmf.dsmapi.individual.model.Organization;
import org.tmf.dsmapi.individual.model.OrganizationChildRelationship;
import org.tmf.dsmapi.individual.model.OrganizationIdentification;
import org.tmf.dsmapi.individual.model.OrganizationParentRelationship;
import org.tmf.dsmapi.individual.model.OtherName;
import org.tmf.dsmapi.individual.model.RelatedParty;
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
//    @EJB
//    OrganizationEventPublisherLocal publisher;

    @GET
    @Produces({"application/json"})
    public List<Organization> findAll() {
        return partyFacade.findAll();
    }

    /**
     *
     * For test purpose only
     *
     * @param entities
     * @return
     */
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response post(List<Organization> entities, @Context UriInfo info) throws UnknownResourceException {

        if (entities == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        int previousRows = partyFacade.count();
        int affectedRows=0;

        // Try to persist entities
        try {
            for (Organization entitie : entities) {
                partyFacade.checkCreationUpdate(entitie,"CREATE");
                partyFacade.create(entitie);
                entitie.setHref(info.getAbsolutePath() + "/" + Long.toString(entitie.getId()));
                partyFacade.edit(entitie);
                affectedRows = affectedRows + 1;
//                publisher.createNotification(entitie, new Date());
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
//            publisher.updateNotification(entity, new Date());
            // 200 OK + location
            response = Response.status(Response.Status.OK).entity(entity).build();

        } else {
            // 404 not found
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }

    /**
     *
     * For test purpose only
     *
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
     *
     * @param id
     * @return
     * @throws UnknownResourceException
     */
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) throws UnknownResourceException {
        int previousRows = partyFacade.count();
        Organization entity = partyFacade.find(id);

        // Event deletion
//        publisher.deleteNotification(entity, new Date());
        try {
            //Pause for 4 seconds to finish notification
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(OrganizationAdminResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        // remove event(s) binding to the resource
        List<OrganizationEvent> events = eventFacade.findAll();
        for (OrganizationEvent event : events) {
            if (event.getResource().getId().equals(id)) {
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
        Characteristic x = new Characteristic();
        organization.setCharacteristic(x);

        List<ContactMedium> cmlist = new ArrayList<ContactMedium>();
        organization.setContactMedium(cmlist);

        ExistsDuring xx = new ExistsDuring();
        organization.setExistsDuring(xx);

        List<ExternalReference> erList = new ArrayList<ExternalReference>();
        organization.setExternalReference(erList);

        organization.setHref("http://serverLocalisation:port/DSPartyManagement/api/partyManagement/v2/organization/42");
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
            if (event.getResource().getId().equals(id)) {
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
