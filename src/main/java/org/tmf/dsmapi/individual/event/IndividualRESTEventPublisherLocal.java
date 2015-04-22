package org.tmf.dsmapi.individual.event;

import javax.ejb.Local;
import org.tmf.dsmapi.individual.event.IndividualEvent;
import org.tmf.dsmapi.hub.Hub;

@Local
public interface IndividualRESTEventPublisherLocal {

    public void publish(Hub hub, IndividualEvent event);
    
}
