package org.tmf.dsmapi.organization.event;

import java.util.Date;
import javax.ejb.Local;
import org.tmf.dsmapi.individual.model.Organization;
import org.tmf.dsmapi.organization.event.OrganizationEvent;


@Local
public interface OrganizationEventPublisherLocal {

    void publish(OrganizationEvent event);

    /**
     *
     * CreateNotification
     * @param bean the bean which has been created
     * @param reason the related reason
     * @param date the creation date
     */
    public void createNotification(Organization bean, Date date);

    /**
     *
     * DeletionNotification
     * @param bean the bean which has been deleted
     * @param reason the reason of the deletion
     * @param date the deletion date
     */
    public void deleteNotification(Organization bean, Date date);

    /**
     *
     * UpdateNotification (PATCH)
     * @param bean the bean which has been updated
     * @param reason the reason it has been updated for
     * @param date the update date
     */
    public void updateNotification(Organization bean, Date date);

}
