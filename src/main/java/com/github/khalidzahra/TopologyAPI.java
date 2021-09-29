package com.github.khalidzahra;

import com.github.khalidzahra.internal.device.Device;
import com.github.khalidzahra.internal.topology.Topology;
import com.github.khalidzahra.internal.topology.TopologyRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalid on 9/28/21.
 * <p>
 * An instance of this class must be created and stored in order to be able to access the API methods.
 */
public class TopologyAPI {

    /**
     * Topology registry instance which will store all currently loaded topologies
     */
    private final TopologyRegistry topologyRegistry;

    /**
     * API class constructor.
     * <p>
     * An instance of the API class must be created and stored on application start.
     */
    public TopologyAPI() {
        this.topologyRegistry = new TopologyRegistry();
    }

    /**
     * Reads the topology from the specified JSON file and loads it into memory
     *
     * @param filePath String variable containing the path to the JSON file
     * @return Returns true if the topology is successfully loaded
     */
    public boolean readJSON(String filePath) {
        return topologyRegistry.loadAndRegisterTopology(filePath);
    }

    /**
     * Writes the topology details from memory to the specified file on disk
     *
     * @param topologyID String variable containing the topology ID
     * @param filePath   String variable containing the path to the JSON file
     * @return Returns true if the topology is successfully written
     */
    public boolean writeJSON(String topologyID, String filePath) {
        return topologyRegistry.saveTopology(topologyID, filePath);
    }

    /**
     * Queries all topologies currently stored in memory
     *
     * @return Returns a List of Topology containing all loaded topologies. Returns an empty list if no topologies are found.
     */
    public List<Topology> queryTopologies() {
        return topologyRegistry.getTopologyList();
    }

    /**
     * Deletes the specified topology from memory
     *
     * @param topologyID String variable containing the topology ID
     * @return Returns true if topology is successfully deleted
     */
    public boolean deleteTopology(String topologyID) {
        return topologyRegistry.deregisterTopology(topologyID);
    }

    /**
     * Queries the devices connected within a topology
     *
     * @param topologyID String variable containing the topology ID
     * @return Returns a List of Device containing all connected devices. Returns an empty list if no devices are found.
     */
    public List<Device> queryDevices(String topologyID) {
        Topology topology = topologyRegistry.findTopology(topologyID);
        return topology == null ? new ArrayList<>() : topology.getDevices();
    }

    /**
     * Finds devices that are connected to a particular node
     *
     * @param topologyID    String variable containing the topology ID
     * @param netlistNodeID String variable containing the node ID
     * @return Returns a List of Device containing all connected devices. Returns an empty list if no devices are found.
     */
    public List<Device> queryDevicesWithNetlistNode(String topologyID, String netlistNodeID) {
        Topology topology = topologyRegistry.findTopology(topologyID);
        return topology == null ? new ArrayList<>() : topology.getDevicesConnectedToNode(netlistNodeID);
    }

}
