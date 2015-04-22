package org.tmf.dsmapi.organization.event;

import javax.ejb.Local;
import org.tmf.dsmapi.organization.event.OrganizationEvent;
import org.tmf.dsmapi.hub.Hub;

@Local
public interface OrganizationRESTEventPublisherLocal {

    public void publish(Hub hub, OrganizationEvent event);
    
}
