package com.github.khalidzahra;

import com.github.khalidzahra.internal.device.Device;
import com.github.khalidzahra.internal.topology.Topology;
import com.github.khalidzahra.internal.topology.TopologyRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalid on 9/28/21.
 */
public class TopologyAPI {

    private final TopologyRegistry topologyRegistry;

    public TopologyAPI() {
        this.topologyRegistry = new TopologyRegistry();
    }

    public boolean readJSON(String filePath) {
        return topologyRegistry.loadAndRegisterTopology(filePath);
    }

    public boolean writeJSON(String topologyID, String filePath) {
        return topologyRegistry.saveTopology(topologyID, filePath);
    }

    public List<Topology> queryTopologies() {
        return topologyRegistry.getTopologyList();
    }

    public boolean deleteTopology(String topologyID) {
        return topologyRegistry.deregisterTopology(topologyID);
    }

    public List<Device> queryDevices(String topologyID) {
        Topology topology = topologyRegistry.findTopology(topologyID);
        return topology == null ? new ArrayList<>() : topology.getDevices();
    }

    public List<Device> queryDevicesWithNetlistNode(String topologyID, String netlistNodeID) {
        Topology topology = topologyRegistry.findTopology(topologyID);
        return topology == null ? new ArrayList<>() : topology.getDevicesConnectedToNode(netlistNodeID);
    }

}
