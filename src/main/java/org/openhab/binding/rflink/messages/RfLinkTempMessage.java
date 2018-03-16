/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.rflink.messages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.types.State;
import org.openhab.binding.rflink.RfLinkBindingConstants;

/**
 * RfLink data class for temp message.
 * For temp sensor "UPM_Esic;ID=XXYY;TEMP=AAAB;HUM=CC;BAT=OK;"
 * xx=channel
 * yy=code
 * AAA=degrees celsius hex
 * B=degrees *.1 hex
 * HUM=
 *
 * @author Albert Lopez - Initial contribution
 */
public class RfLinkTempMessage extends RfLinkBaseMessage {

    private static final String KEY_ID = "ID";
    private static final String KEY_TEMP = "TEMP";
    private static final String KEY_HUM = "HUM";
    private static final String KEY_BAT = "BAT";

    private static final List<String> keys = Arrays.asList(KEY_ID, KEY_TEMP, KEY_HUM, KEY_BAT);

    public double ch = 0;
    public double code = 0;
    public double temp = 0;
    public double hum = 0;
    public double bat = 0;

    public RfLinkTempMessage() {

    }

    public RfLinkTempMessage(String data) {
        encodeMessage(data);
    }

    @Override
    public void encodeMessage(String data) {

        super.encodeMessage(data);

        // if (values.containsKey(KEY_ID)) {
        // ch = Integer.parseInt(values.get(KEY_ID ),16)& 0xFF00;
        // code = Integer.parseInt(values.get(KEY_ID),16)& 0x00FF;
        //
        // }

        if (values.containsKey(KEY_TEMP)) {
            temp = Integer.parseInt(values.get(KEY_TEMP), 16) / 10.0f;
        }

        if (values.containsKey(KEY_HUM)) {
            hum = Integer.parseInt(values.get(KEY_HUM), 16);
        }

    }

    @Override
    public List<String> keys() {
        return keys;
    }

    @Override
    public HashMap<String, State> getStates() {

        HashMap<String, State> map = new HashMap<>();

        map.put(RfLinkBindingConstants.CHANNEL_TEMPERATURE, new DecimalType(temp));
        map.put(RfLinkBindingConstants.CHANNEL_HUMIDITY, new DecimalType(hum));

        return map;

    }

    @Override
    public String toString() {
        String str = "";

        str += super.toString();
        str += ", Temperature  = " + temp;
        str += ", Humidity = " + hum;

        return str;
    }

}
