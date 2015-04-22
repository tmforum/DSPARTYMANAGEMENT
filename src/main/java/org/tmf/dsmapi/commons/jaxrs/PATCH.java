/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmf.dsmapi.commons.jaxrs;

/**
 *
 * @author pierregauthier
 */
import javax.ws.rs.HttpMethod;
import java.lang.annotation.*;
import javax.ws.rs.NameBinding;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("PATCH")
@Documented
@NameBinding
public @interface PATCH {

}
