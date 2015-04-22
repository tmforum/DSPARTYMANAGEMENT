package org.tmf.dsmapi.individual.event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.facade.AbstractFacade;
import org.tmf.dsmapi.individual.event.IndividualEvent;

@Stateless
public class IndividualEventFacade extends AbstractFacade<IndividualEvent>{
    
    @PersistenceContext(unitName = "DSIndividualPU")
    private EntityManager em;
   

    
    /**
     *
     */
    public IndividualEventFacade() {
        super(IndividualEvent.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
