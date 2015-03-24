package org.tmf.dsmapi.organization;

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
import org.tmf.dsmapi.individual.model.Organization;
import org.tmf.dsmapi.organization.event.OrganizationEventPublisherLocal;

/**
 *
 * @author pierregauthier
 */
@Stateless
public class OrganizationFacade extends AbstractFacade<Organization> {

    @PersistenceContext(unitName = "DSIndividualPU")
    private EntityManager em;
    @EJB
    OrganizationEventPublisherLocal publisher;

    public OrganizationFacade() {
        super(Organization.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void create(Organization entity) throws BadUsageException {
        if (entity.getId() != null) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_GENERIC, "While creating Organization, id must be null");
        }

        super.create(entity);
    }

    public Organization updateAttributs(long id, Organization partialOrganization) throws UnknownResourceException, BadUsageException {
        Organization currentOrganization = this.find(id);

        if (currentOrganization == null) {
            throw new UnknownResourceException(ExceptionType.UNKNOWN_RESOURCE);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(partialOrganization, JsonNode.class);


        partialOrganization.setId(id);
        if (BeanUtils.patch(currentOrganization, partialOrganization, node)) {
            publisher.valueChangedNotification(currentOrganization, new Date());
        }

        return currentOrganization;
    }

}
