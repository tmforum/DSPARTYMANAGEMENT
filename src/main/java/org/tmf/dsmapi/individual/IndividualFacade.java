package org.tmf.dsmapi.individual;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.tmf.dsmapi.individual.model.Characteristic;
import org.tmf.dsmapi.individual.model.Disability;
import org.tmf.dsmapi.individual.model.ExternalReference;
import org.tmf.dsmapi.individual.model.IndividualIdentification;
import org.tmf.dsmapi.individual.model.RelatedParty;

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

    public void checkCreationUpdate(Individual newIndividual) throws BadUsageException {

        if (null == newIndividual.getGivenName()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "givenName is mandatory");
        }

        if (null == newIndividual.getFamilyName()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                    "familyName is mandatory");
        }

        if (null != newIndividual.getDisability()) {
            for (Disability disability : newIndividual.getDisability()) {
                if (null == disability.getDisability()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "disability.disabilty is mandatory");
                }
            }
        }

        if (null != newIndividual.getCharacteristic()) {
            for (Characteristic characteristic : newIndividual.getCharacteristic()) {
                if (null == characteristic.getName()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.name is mandatory");
                }
                if (null == characteristic.getValue()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.value is mandatory");
                }
            }
        }

        if (null != newIndividual.getIndividualIdentification()) {
            for (IndividualIdentification individualIdentification : newIndividual.getIndividualIdentification()) {
                if (null == individualIdentification.getIdentificationId()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.id is mandatory");
                }
                if (null == individualIdentification.getType()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.type is mandatory");
                }
            }
        }

        if (null != newIndividual.getExternalReference()) {
            for (ExternalReference externalReference : newIndividual.getExternalReference()) {
                if (null == externalReference.getHref()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "externalReference.href is mandatory");
                }
                if (null == externalReference.getType()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "externalReference.type is mandatory");
                }
            }
        }

        if (null != newIndividual.getRelatedParty()) {
            for (RelatedParty relatedParty : newIndividual.getRelatedParty()) {
                if (null == relatedParty.getRole()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "relatedParty.role is mandatory");
                }
                if (null != relatedParty.getValidFor()) {
                    if (null == relatedParty.getValidFor().getStartDateTime()) {
                        relatedParty.getValidFor().setStartDateTime(new Date());
                    }
                }
            }
        }

    }

    public Individual updateAttributs(long id, Individual partialIndividual) throws UnknownResourceException, BadUsageException {
        Individual currentIndividual = this.find(id);

        if (currentIndividual == null) {
            throw new UnknownResourceException(ExceptionType.UNKNOWN_RESOURCE);
        }

        if (null != partialIndividual.getId()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_OPERATOR, 
                    "id is not patchable");
        }

        if (null != partialIndividual.getPlaceOfBirth()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_OPERATOR, 
                    "placeOfBirth is not patchable");
        }

        if (null != partialIndividual.getBirthDate()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_OPERATOR, 
                    "birthDate is not patchable");
        }

        if (null == partialIndividual.getDisability()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                    "disability is mandatory");
        }

        if (null != partialIndividual.getCharacteristic()) {
            for (Characteristic characteristic : partialIndividual.getCharacteristic()) {
                if (null == characteristic.getName()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.name is mandatory");
                }
                if (null == characteristic.getValue()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.value is mandatory");
                }
            }
        }

        if (null != partialIndividual.getIndividualIdentification()) {
            for (IndividualIdentification individualIdentification : partialIndividual.getIndividualIdentification()) {
                if (null == individualIdentification.getIdentificationId()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.id is mandatory");
                }
                if (null == individualIdentification.getType()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.type is mandatory");
                }
            }
        }

        if (null != partialIndividual.getExternalReference()) {
            for (ExternalReference externalReference : partialIndividual.getExternalReference()) {
                if (null == externalReference.getType()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "externalReference.type is mandatory");
                }
            }
        }

        if (null != partialIndividual.getRelatedParty()) {
            for (RelatedParty relatedParty : partialIndividual.getRelatedParty()) {
                if (null == relatedParty.getRole()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "relatedParty.role is mandatory");
                }
                if (null != relatedParty.getValidFor()) {
                    if (null == relatedParty.getValidFor().getStartDateTime()) {
                        relatedParty.getValidFor().setStartDateTime(new Date());
                    }
                }
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(partialIndividual, JsonNode.class);
        partialIndividual.setId(id);
        if (BeanUtils.patch(currentIndividual, partialIndividual, node)) {
            publisher.updateNotification(currentIndividual, new Date());
        }

        return currentIndividual;
    }
}
