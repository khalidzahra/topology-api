package com.github.khalidzahra.internal.device;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Khalid on 9/28/21.
 */
public class DeviceProperties {

    private double min;
    private double max;

    @SerializedName("default")
    private double defaultValue;

    public DeviceProperties(double min, double max, double defaultValue) {
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(double defaultValue) {
        this.defaultValue = defaultValue;
    }
}
