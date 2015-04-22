/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmf.dsmapi.commons.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.codehaus.jackson.JsonNode;

/**
 *
 * @author maig7313
 */
public class BeanUtils {

    private static final PropertyUtilsBean PUB = new PropertyUtilsBean();

    /**
     *
     * @param bean
     * @param name
     * @return
     */
    public static Object getNestedProperty(Object bean, String name) {
        try {
            return PUB.getNestedProperty(bean, name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param bean
     * @param name
     * @param value
     */
    public static void setNestedProperty(Object bean, String name, Object value) {
        try {
            PUB.setNestedProperty(bean, name, value);
        } catch (Exception ex) {
        }
    }

    /**
     *
     * @param bean
     * @param patchBean
     * @param node
     */
//    public static void patch(Object bean, Object patch, JsonNode node) {
//        String name;
//        JsonNode child;
//        Object value;
//        Object patchValue;
//        Iterator<String> it = node.getFieldNames();
//        while (it.hasNext()) {
//            name = it.next();
//            patchValue = BeanUtils.getNestedProperty(patch, name);
//            child = node.get(name);
//            if (child.isObject()) {
//                value = BeanUtils.getNestedProperty(bean, name);
//                if (value != null) {
//                    patch(value, patchValue, child);
//                    BeanUtils.setNestedProperty(bean, name, value);
//                } else {
//                    BeanUtils.setNestedProperty(bean, name, patchValue);
//                }
//            } else {
//                BeanUtils.setNestedProperty(bean, name, patchValue);
//            }
//        }
//    }    
    public static boolean patch(Object bean, Object patch, JsonNode node) {
        String name;
        JsonNode child;
        Object value;
        Object patchValue;
        Iterator<String> it = node.getFieldNames();
        boolean isModified=false;
        while (it.hasNext()) {
            name = it.next();
            patchValue = BeanUtils.getNestedProperty(patch, name);
            child = node.get(name);
            if (null != patchValue) {
                if (child.isArray()) {
                    if (!((ArrayList) patchValue).isEmpty()) {
                        value = BeanUtils.getNestedProperty(bean, name);
                        patch(value, patchValue, child);
                        BeanUtils.setNestedProperty(bean, name, patchValue);
                        isModified=true;
                    }
                } else {
                    value = BeanUtils.getNestedProperty(bean, name);
                    patch(value, patchValue, child);
                    BeanUtils.setNestedProperty(bean, name, patchValue);
                    isModified=true;
                }
            }
        }
        return isModified;
    }

    public static boolean verify(Object patch, JsonNode node, String attribut) {
        boolean find = false;
        String name;
        JsonNode child;
        Object value;
        Object patchValue;
        Iterator<String> it = node.getFieldNames();
        while (it.hasNext()) {
            name = it.next();
            patchValue = BeanUtils.getNestedProperty(patch, name);
            child = node.get(name);
            if (child.isArray()) {
                if (!((ArrayList) patchValue).isEmpty()) {
                    if (name.equalsIgnoreCase(attribut)) {
                        find = true;
                        break;
                    }
//                    value = BeanUtils.getNestedProperty(patch, name);
//                    verify(value, child, attribut);
                }
            } else {
//                Logger.getLogger("VERIFY").log(Level.INFO, "NAME : " + name);
//                Logger.getLogger("VERIFY").log(Level.INFO, "CHILD : " + child);
                if (name.equalsIgnoreCase(attribut)) {
                    find = true;
                    break;
                }
            }
        }
        return find;
    }

}
