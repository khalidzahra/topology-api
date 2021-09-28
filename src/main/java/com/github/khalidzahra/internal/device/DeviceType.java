package com.github.khalidzahra.internal.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Khalid on 9/28/21.
 */
public enum DeviceType {

    @SerializedName("resistor")
    RESISTOR("resistance"),
    @SerializedName("capacitor")
    CAPACITOR("capacitance"),
    @SerializedName("inductor")
    INDUCTOR("inductance"),
    @SerializedName("nmos")
    NMOS("m(l)"),
    @SerializedName("pmos")
    PMOS("m(l)"),
    @SerializedName("bjt")
    BJT("m(l)");

    private final String serializedPropertyName;

    DeviceType(String serializedPropertyName) {
        this.serializedPropertyName = serializedPropertyName;
    }

    public String getSerializedPropertyName() {
        return serializedPropertyName;
    }
}
