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

    /**
     * Finds devices connected to the specified node
     * @param nodeId String variable containing the node ID
     * @return Returns List of Device containing all devices connected to the specified node.
     */
    public List<Device> getDevicesConnectedToNode(String nodeId) {
        return this.devices.stream().filter(device -> device.getNetList().containsValue(nodeId)).toList();
    }

    /**
     * Adds the specified device to the topology
     * @param device Device object to be added to the topology
     */
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
