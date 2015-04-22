package org.tmf.dsmapi.subscriber;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tmf.dsmapi.commons.facade.AbstractFacade;

/**
 *
 * @author ecus6396
 */
@Stateless
public class EventApiFacade extends AbstractFacade<EventBag> {

    @PersistenceContext(unitName = "DSIndividualPU")
    private EntityManager em;

    public EventApiFacade() {
        super(EventBag.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
