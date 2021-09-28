package com.github.khalidzahra.internal.topology;

import com.github.khalidzahra.internal.device.Device;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalid on 9/28/21.
 */
public class Topology {

    private String id;
    @SerializedName("components")
    private List<Device> devices;

    public Topology(String id) {
        this.id = id;
        this.devices = new ArrayList<>();
    }

    public Topology(String id, List<Device> deviceList) {
        this.id = id;
        this.devices = deviceList;
    }

    public List<Device> getDevicesConnectedToNode(String nodeId) {
        return this.devices.stream().filter(device -> device.getNetList().containsValue(nodeId)).toList();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public String getId() {
        return id;
    }

    public List<Device> getDevices() {
        return devices;
    }
}
