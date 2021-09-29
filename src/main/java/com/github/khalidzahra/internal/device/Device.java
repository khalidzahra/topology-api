package com.github.khalidzahra.internal.device;

import com.github.khalidzahra.internal.util.JsonKeyUtil;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Khalid on 9/28/21.
 */
public class Device {

    @SerializedName(JsonKeyUtil.DEVICE_TYPE_KEY)
    private final DeviceType deviceType;
    @SerializedName("id")
    private final String deviceId;
    @SerializedName(value = JsonKeyUtil.DEVICE_PROPERTIES_KEY,
            alternate = {JsonKeyUtil.DEVICE_PROPERTIES_RESISTANCE_KEY,
                    JsonKeyUtil.DEVICE_PROPERTIES_CAPACITANCE_KEY,
                    JsonKeyUtil.DEVICE_PROPERTIES_INDUCTANCE_KEY,
                    JsonKeyUtil.DEVICE_PROPERTIES_TRANSISTOR_KEY})
    private final DeviceProperties deviceProperties;
    @SerializedName(JsonKeyUtil.DEVICE_NETLIST_KEY)
    private final Map<String, String> netList;

    public Device(DeviceType deviceType, String deviceId, DeviceProperties deviceProperties, Map<String, String> netList) {
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.deviceProperties = deviceProperties;
        this.netList = netList;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceProperties getDeviceProperties() {
        return deviceProperties;
    }

    public Map<String, String> getNetList() {
        return netList;
    }
}
