package org.tmf.dsmapi.individual.event;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.individual.model.Individual;
import org.tmf.dsmapi.individual.event.IndividualEvent;
import org.tmf.dsmapi.individual.event.IndividualEventTypeEnum;
import org.tmf.dsmapi.individual.event.IndividualRESTEventPublisherLocal;
import org.tmf.dsmapi.hub.Hub;
import org.tmf.dsmapi.hub.HubFacade;

/**
 *
 * Should be async or called with MDB
 */
@Stateless
@Asynchronous
public class IndividualEventPublisher implements IndividualEventPublisherLocal {

    @EJB
    HubFacade hubFacade;
    @EJB
    IndividualEventFacade eventFacade;
    @EJB
    IndividualRESTEventPublisherLocal restEventPublisherLocal;

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
    public void publish(IndividualEvent event) {
        try {
            eventFacade.create(event);
        } catch (BadUsageException ex) {
            Logger.getLogger(IndividualEventPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Hub> hubList = hubFacade.findAll();
        Iterator<Hub> it = hubList.iterator();
        while (it.hasNext()) {
            Hub hub = it.next();
            restEventPublisherLocal.publish(hub, event);
        }
    }

    @Override
    public void createNotification(Individual bean, Date date) {
        IndividualEvent event = new IndividualEvent();
        event.setEventTime(date);
        event.setEventType(IndividualEventTypeEnum.IndividualCreateNotification);
        event.setResource(bean);
        publish(event);

    }

    @Override
    public void deleteNotification(Individual bean, Date date) {
        IndividualEvent event = new IndividualEvent();
        event.setEventTime(date);
        event.setEventType(IndividualEventTypeEnum.IndividualDeleteNotification);
        event.setResource(bean);
        publish(event);
    }
	
    @Override
    public void updateNotification(Individual bean, Date date) {
        IndividualEvent event = new IndividualEvent();
        event.setEventTime(date);
        event.setEventType(IndividualEventTypeEnum.IndividualUpdateNotification);
        event.setResource(bean);
        publish(event);
    }

}
