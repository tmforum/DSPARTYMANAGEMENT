/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmf.dsmapi.individual.event;

public enum IndividualEventTypeEnum {

    IndividualCreateNotification("IndividualCreateNotification"),
    IndividualUpdateNotification("IndividualUpdateNotification"),
    IndividualDeleteNotification("IndividualDeleteNotification");

    private String text;

    IndividualEventTypeEnum(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return this.text;
    }

    /**
     *
     * @param text
     * @return
     */
    public static org.tmf.dsmapi.individual.event.IndividualEventTypeEnum fromString(String text) {
        if (text != null) {
            for (IndividualEventTypeEnum b : IndividualEventTypeEnum.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}