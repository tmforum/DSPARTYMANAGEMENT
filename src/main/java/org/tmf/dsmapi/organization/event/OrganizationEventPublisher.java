package org.tmf.dsmapi.organization.event;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.individual.model.Organization;
import org.tmf.dsmapi.organization.event.OrganizationEvent;
import org.tmf.dsmapi.organization.event.OrganizationEventTypeEnum;
import org.tmf.dsmapi.organization.event.OrganizationRESTEventPublisherLocal;
import org.tmf.dsmapi.hub.Hub;
import org.tmf.dsmapi.hub.HubFacade;

/**
 *
 * Should be async or called with MDB
 */
@Stateless
@Asynchronous
public class OrganizationEventPublisher implements OrganizationEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    OrganizationEventFacade eventFacade;
    @EJB
    OrganizationRESTEventPublisherLocal restEventPublisherLocal;

    /** 
     * Add business logic below. (Right-click in editor and choose
     * "Insert Code > Add Business Method")
     * Access Hubs using callbacks and send to http publisher 
     *(pool should be configured around the RESTEventPublisher bean)
     * Loop into array of Hubs
     * Call RestEventPublisher - Need to implement resend policy plus eviction
     * Filtering is done in RestEventPublisher based on query expression
    */ 
    @Override
    public void publish(OrganizationEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(OrganizationEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(Organization bean, Date date) {
        OrganizationEvent event = new OrganizationEvent();
        event.setEventTime(date);
        event.setEventType(OrganizationEventTypeEnum.OrganizationCreateNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deleteNotification(Organization bean, Date date) {
        OrganizationEvent event = new OrganizationEvent();
        event.setEventTime(date);
        event.setEventType(OrganizationEventTypeEnum.OrganizationDeleteNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(Organization bean, Date date) {
        OrganizationEvent event = new OrganizationEvent();
        event.setEventTime(date);
        event.setEventType(OrganizationEventTypeEnum.OrganizationUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

}
