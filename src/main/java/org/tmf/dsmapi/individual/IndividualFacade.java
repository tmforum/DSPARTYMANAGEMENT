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
import org.tmf.dsmapi.individual.model.ValidFor;

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

    public void checkCreationUpdate(Individual newIndividual) throws BadUsageException, UnknownResourceException {

        if (newIndividual.getId() != null) {
            if (this.find(newIndividual.getId()) != null) {
                throw new BadUsageException(ExceptionType.BAD_USAGE_GENERIC,
                        "Duplicate Exception, Individual with same id :" + newIndividual.getId() + " alreay exists");
            }
        }

        if (null == newIndividual.getGivenName()
                || newIndividual.getGivenName().isEmpty()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS, "givenName is mandatory");
        }

        if (null == newIndividual.getFamilyName()
                || newIndividual.getFamilyName().isEmpty()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                    "familyName is mandatory");
        }

        if (null != newIndividual.getDisability()
                && !newIndividual.getDisability().isEmpty()) {
            for (Disability disability : newIndividual.getDisability()) {
                if (null == disability.getDisability()
                        || disability.getDisability().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "disability.disabilty is mandatory");
                }
            }
        }

        if (null != newIndividual.getCharacteristic()
                && !newIndividual.getCharacteristic().isEmpty()) {
            for (Characteristic characteristic : newIndividual.getCharacteristic()) {
                if (null == characteristic.getName()
                        || characteristic.getName().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.name is mandatory");
                }
                if (null == characteristic.getValue()
                        || characteristic.getValue().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.value is mandatory");
                }
            }
        }

        if (null != newIndividual.getIndividualIdentification()
                && !newIndividual.getIndividualIdentification().isEmpty()) {
            for (IndividualIdentification individualIdentification : newIndividual.getIndividualIdentification()) {
                if (null == individualIdentification.getIdentificationId()
                        || individualIdentification.getIdentificationId().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.id is mandatory");
                }
                if (null == individualIdentification.getType()
                        || individualIdentification.getType().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.type is mandatory");
                }
            }
        }

        if (null != newIndividual.getExternalReference()
                && !newIndividual.getExternalReference().isEmpty()) {
            for (ExternalReference externalReference : newIndividual.getExternalReference()) {
                if (null == externalReference.getHref()
                        || externalReference.getHref().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "externalReference.href is mandatory");
                }
                if (null == externalReference.getType()
                        || externalReference.getType().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "externalReference.type is mandatory");
                }
            }
        }

        if (null != newIndividual.getRelatedParty()
                && !newIndividual.getRelatedParty().isEmpty()) {
            for (RelatedParty relatedParty : newIndividual.getRelatedParty()) {
                if (null == relatedParty.getRole()
                        || relatedParty.getRole().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "relatedParty.role is mandatory");
                }
                if (null != relatedParty.getValidFor()) {
                    if (null == relatedParty.getValidFor().getStartDateTime()) {
                        relatedParty.getValidFor().setStartDateTime(new Date());
                    }
                } else {
                    ValidFor validFor = new ValidFor();
                    validFor.setStartDateTime(new Date());
                    relatedParty.setValidFor(validFor);
                }
            }
        }

    }

    public Individual patchAttributs(long id, Individual partialIndividual) throws UnknownResourceException, BadUsageException {
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

        if (null == partialIndividual.getDisability()
                && !partialIndividual.getDisability().isEmpty()) {
            throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                    "disability is mandatory");
        }

        if (null != partialIndividual.getCharacteristic()
                && !partialIndividual.getCharacteristic().isEmpty()) {
            for (Characteristic characteristic : partialIndividual.getCharacteristic()) {
                if (null == characteristic.getName()
                        || characteristic.getName().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.name is mandatory");
                }
                if (null == characteristic.getValue()
                        || characteristic.getValue().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "characteristic.value is mandatory");
                }
            }
        }

        if (null != partialIndividual.getIndividualIdentification()
                && !partialIndividual.getIndividualIdentification().isEmpty()) {
            for (IndividualIdentification individualIdentification : partialIndividual.getIndividualIdentification()) {
                if (null == individualIdentification.getIdentificationId()
                        || individualIdentification.getIdentificationId().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.id is mandatory");
                }
                if (null == individualIdentification.getType()
                        || individualIdentification.getType().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "individualIdentification.type is mandatory");
                }
            }
        }

        if (null != partialIndividual.getExternalReference()
                && !partialIndividual.getExternalReference().isEmpty()) {
            for (ExternalReference externalReference : partialIndividual.getExternalReference()) {
                if (null == externalReference.getType()
                        || externalReference.getType().isEmpty()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "externalReference.type is mandatory");
                }
            }
        }

        if (null != partialIndividual.getRelatedParty()
                && !partialIndividual.getRelatedParty().isEmpty()) {
            for (RelatedParty relatedParty : partialIndividual.getRelatedParty()) {
                if (null == relatedParty.getRole()) {
                    throw new BadUsageException(ExceptionType.BAD_USAGE_MANDATORY_FIELDS,
                            "relatedParty.role is mandatory");
                }
                if (null != relatedParty.getValidFor()) {
                    if (null == relatedParty.getValidFor().getStartDateTime()) {
                        relatedParty.getValidFor().setStartDateTime(new Date());
                    }
                } else {
                    ValidFor validFor = new ValidFor();
                    validFor.setStartDateTime(new Date());
                    relatedParty.setValidFor(validFor);
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
