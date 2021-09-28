package com.github.khalidzahra.internal.device;

import com.github.khalidzahra.internal.util.JsonKeyUtil;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Khalid on 9/28/21.
 */
public class Device {

    @SerializedName(value = JsonKeyUtil.DEVICE_TYPE_KEY)
    private DeviceType deviceType;
    private String id;
    @SerializedName(value = JsonKeyUtil.DEVICE_PROPERTIES_KEY,
            alternate = {JsonKeyUtil.DEVICE_PROPERTIES_RESISTANCE_KEY,
                    JsonKeyUtil.DEVICE_PROPERTIES_CAPACITANCE_KEY,
                    JsonKeyUtil.DEVICE_PROPERTIES_INDUCTANCE_KEY,
                    JsonKeyUtil.DEVICE_PROPERTIES_TRANSISTOR_KEY})
    private DeviceProperties deviceProperties;
    @SerializedName(value = JsonKeyUtil.DEVICE_NETLIST_KEY)
    private HashMap<String, String> netList;

    public Device(DeviceType deviceType, String id, DeviceProperties deviceProperties, HashMap<String, String> netList) {
        this.deviceType = deviceType;
        this.id = id;
        this.deviceProperties = deviceProperties;
        this.netList = netList;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getId() {
        return id;
    }

    public DeviceProperties getDeviceProperties() {
        return deviceProperties;
    }

    public HashMap<String, String> getNetList() {
        return netList;
    }
}
