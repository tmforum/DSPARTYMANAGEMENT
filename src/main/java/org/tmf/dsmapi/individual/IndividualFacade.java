package org.tmf.dsmapi.individual;

import java.util.Date;
import org.tmf.dsmapi.commons.facade.AbstractFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.tmf.dsmapi.commons.exceptions.BadUsageException;
import org.tmf.dsmapi.commons.exceptions.ExceptionType;
import org.tmf.dsmapi.commons.exceptions.UnknownResourceException;
import org.tmf.dsmapi.commons.utils.BeanUtils;
import org.tmf.dsmapi.individual.model.Individual;
import org.tmf.dsmapi.individual.event.IndividualEventPublisherLocal;

/**
 *
 * @author pierregauthier
 */
@Stateless
public class IndividualFacade extends AbstractFacade<Individual> {

    @PersistenceContext(unitName = "DSIndividualPU")
    private EntityManager em;
    @EJB
    IndividualEventPublisherLocal publisher;

    public IndividualFacade() {
        super(Individual.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Individual entity) throws BadUsageException {
        if (entity.getId() != null) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_GENERIC, "While creating Individual, id must be null");
        }

        super.create(entity);
    }

    public Individual updateAttributs(long id, Individual partialIndividual) throws UnknownResourceException, BadUsageException {
        Individual currentIndividual = this.find(id);

        if (currentIndividual == null) {
            throw new UnknownResourceException(ExceptionType.UNKNOWN_RESOURCE);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(partialIndividual, JsonNode.class);


        partialIndividual.setId(id);
        if (BeanUtils.patch(currentIndividual, partialIndividual, node)) {
            publisher.valueChangedNotification(currentIndividual, new Date());
        }

        return currentIndividual;
    }
}
