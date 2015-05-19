package org.tmf.dsmapi.individual;

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
import org.tmf.dsmapi.individual.model.Individual;
import org.tmf.dsmapi.individual.event.IndividualEvent;
import org.tmf.dsmapi.individual.event.IndividualEventFacade;
import org.tmf.dsmapi.individual.event.IndividualEventPublisherLocal;
import org.tmf.dsmapi.individual.model.Characteristic;
import org.tmf.dsmapi.individual.model.ContactMedium;
import org.tmf.dsmapi.individual.model.Disability;
import org.tmf.dsmapi.individual.model.ExternalReference;
import org.tmf.dsmapi.individual.model.IndividualIdentification;
import org.tmf.dsmapi.individual.model.OtherName;
import org.tmf.dsmapi.individual.model.RelatedParty;

@Stateless
@Path("admin/individual")
public class IndividualAdminResource {

    @EJB
    IndividualFacade partyFacade;
    @EJB
    IndividualEventFacade eventFacade;
//    @EJB
//    IndividualEventPublisherLocal publisher;

    @GET
    @Produces({"application/json"})
    public List<Individual> findAll() {
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
    public Response post(List<Individual> entities, @Context UriInfo info) throws UnknownResourceException {

        if (entities == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
        }

        int previousRows = partyFacade.count();
        int affectedRows=0;

        // Try to persist entities
        try {
            for (Individual entitie : entities) {
                partyFacade.create(entitie);
                entitie.setHref(info.getAbsolutePath() + "/" + Long.toString(entitie.getId()));
                partyFacade.edit(entitie);
                affectedRows = affectedRows + 1;
//                publisher.createNotification(entitie, new Date());
            }
//            affectedRows = partyFacade.create(entities);
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
    public Response update(@PathParam("id") long id, Individual entity) throws UnknownResourceException {
        Response response = null;
        Individual party = partyFacade.find(id);
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
        List<Individual> pis = partyFacade.findAll();
        for (Individual pi : pis) {
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
        Individual entity = partyFacade.find(id);

        // Event deletion
//        publisher.deleteNotification(entity, new Date());
        try {
            //Pause for 4 seconds to finish notification
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IndividualAdminResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        // remove event(s) binding to the resource
        List<IndividualEvent> events = eventFacade.findAll();
        for (IndividualEvent event : events) {
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
    public List<IndividualEvent> findAllEvents() {
        return eventFacade.findAll();
    }

    @GET
    @Produces({"application/json"})
    @Path("proto")
    public Individual proto() {
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

        individual.setHref("http://serverLocalisation:port/DSPartyManagement/api/partyManagement/v2/individual/42");
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
        return individual;
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
        List<IndividualEvent> events = eventFacade.findAll();
        for (IndividualEvent event : events) {
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
