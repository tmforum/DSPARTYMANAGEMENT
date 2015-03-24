package org.tmf.dsmapi.hub;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.facade.AbstractFacade;

@Stateless
public class HubFacade extends AbstractFacade<Hub>{
    
    @PersistenceContext(unitName = "DSIndividualPU")
    private EntityManager em;

    /**
     *
     */
    public HubFacade() {
        super(Hub.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
