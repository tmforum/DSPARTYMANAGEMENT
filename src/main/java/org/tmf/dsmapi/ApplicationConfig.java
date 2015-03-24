package org.tmf.dsmapi;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return getRestResourceClasses();
    }

    private Set<Class<?>> getRestResourceClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        resources.add(org.tmf.dsmapi.commons.jaxrs.BadUsageExceptionMapper.class);
        resources.add(org.tmf.dsmapi.commons.jaxrs.JacksonConfigurator.class);
        resources.add(org.tmf.dsmapi.commons.jaxrs.JsonMappingExceptionMapper.class);
        resources.add(org.tmf.dsmapi.commons.jaxrs.UnknowResourceExceptionMapper.class);
        resources.add(org.tmf.dsmapi.hub.HubResource.class);
        resources.add(org.tmf.dsmapi.individual.IndividualAdminResource.class);
        resources.add(org.tmf.dsmapi.individual.IndividualResource.class);
        resources.add(org.tmf.dsmapi.organization.OrganizationAdminResource.class);
        resources.add(org.tmf.dsmapi.organization.OrganizationResource.class);
        // following code can be used to customize Jersey 2.x JSON provider:
        try {
			Class jacksonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
			resources.add(jacksonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return resources;
    }
}
