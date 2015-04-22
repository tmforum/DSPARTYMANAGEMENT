package org.tmf.dsmapi.organization.event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.facade.AbstractFacade;
import org.tmf.dsmapi.organization.event.OrganizationEvent;

@Stateless
public class OrganizationEventFacade extends AbstractFacade<OrganizationEvent>{
    
    @PersistenceContext(unitName = "DSIndividualPU")
    private EntityManager em;
   

    
    /**
     *
     */
    public OrganizationEventFacade() {
        super(OrganizationEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
