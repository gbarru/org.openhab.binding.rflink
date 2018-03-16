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
 * RfLink data class for RF RGB Led Controller
 *
 * @author Albert López - Initial contribution
 */
public class RfLinkRFRGBControllerMessage extends RfLinkBaseMessage {

    /*
     * private static final String KEY_TURN_ON_STANDBY = "ON_OFF";//Switch 01
     * private static final String KEY_MODE_UP = "MODE_UP"; //05
     * private static final String KEY_MODE_DOWN = "MODE_DOWN"; //0b
     * private static final String KEY_SPEED_UP = "SPEED_UP"; //09
     * private static final String KEY_SPEED_DOWN = "SPEED_DOWN"; //07
     * private static final String KEY_COLOR_UP = "COLOR_UP"; //0a
     * private static final String KEY_COLOR_DOWN = "COLOR_DOWN"; //0d
     * private static final String KEY_BRIGHT_UP = "BRIGHT_UP"; //0c
     * private static final String KEY_BRIGHT_DOWN = "BRIGHT_DOWN"; //0f
     * private static final String KEY_COLOR_WHITE = "WHITE"; //0e
     * private static final String KEY_COLOR_RED = "RED"; //1.00
     * private static final String KEY_COLOR_GREEN = "GREEN"; //1.01
     * private static final String KEY_COLOR_BLUE = "BLUE"; //1.02
     * private static final String KEY_COLOR_YELLOW = "WELLOW"; //1.03
     * private static final String KEY_COLOR_CYAN = "CYAN"; //1.04
     * private static final String KEY_COLOR_PINK = "PINK"; //1.05
     */
    private static final String KEY_ID = "ID";
    private static final String KEY_SWITCH = "SWITCH";
    private static final String KEY_CMD = "CMD";

    private static final List<String> keys = Arrays.asList(KEY_ID, KEY_SWITCH, KEY_CMD);

    public double id = 0;
    public double switch_number = 0;
    public int cmd = 0;

    public RfLinkRFRGBControllerMessage() {

    }

    public RfLinkRFRGBControllerMessage(String data) {
        encodeMessage(data);
    }

    @Override
    public void encodeMessage(String data) {

        super.encodeMessage(data);

        if (values.containsKey(KEY_ID)) {
            id = Integer.parseInt(values.get(KEY_ID), 16);
        }

        if (values.containsKey(KEY_SWITCH)) {
            switch_number = Integer.parseInt(values.get(KEY_SWITCH), 16);
        }

        if (values.containsKey(KEY_CMD)) {
            cmd = Integer.parseInt(values.get(KEY_CMD), 16);
        }

    }

    @Override
    public List<String> keys() {
        return keys;
    }

    @Override
    public HashMap<String, State> getStates() {

        HashMap<String, State> map = new HashMap<>();

        map.put(RfLinkBindingConstants.CHANNEL_ID, new DecimalType(id));
        map.put(RfLinkBindingConstants.CHANNEL_SWITCH, new DecimalType(switch_number));
        map.put(RfLinkBindingConstants.CHANNEL_CMD, new DecimalType(cmd));

        return map;

    }

    @Override
    public String toString() {
        String str = "";

        str += super.toString();
        str += "Mando RF RGB LED EV1527";
        str += ", ID = " + id;
        str += ", SWITCH = " + switch_number;
        str += ", CMD = " + cmd;

        return str;
    }

}
