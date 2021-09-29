package com.github.khalidzahra.internal.topology;

import com.github.khalidzahra.internal.util.json.GsonHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalid on 9/28/21.
 */
public class TopologyRegistry {

    private final List<Topology> topologyList;

    public TopologyRegistry() {
        this.topologyList = new ArrayList<>();
    }

    /**
     * Loads the topology from the specified file and adds it to topologyList.
     * @param filePath String variable containing path to the JSON file
     * @return Returns true if the topology is successfully loaded and registered
     */
    public boolean loadAndRegisterTopology(String filePath) {
        Topology topology = GsonHandler.loadTopology(filePath);
        return registerTopology(topology);
    }

    /**
     * Writes the specified topology to the specified file
     * @param topologyID String variable containing the topology ID
     * @param filePath String variable containing path to the JSON file
     * @return Returns true if the topology is successfully found and saved
     */
    public boolean saveTopology(String topologyID, String filePath) {
        Topology topology = findTopology(topologyID);
        if (topology == null) return false;
        return GsonHandler.saveTopology(filePath, topology);
    }

    /**
     * Adds the topology to topologyList
     * @param topology Topology object to be registered
     * @return Returns true if the topology is successfully registered
     */
    public boolean registerTopology(Topology topology) {
        if (topology == null) return false;
        return topologyList.add(topology);
    }

    /**
     * Finds the topology matching the specified ID
     * @param topologyID String variable containing the topology ID
     * @return Returns Topology object matching the specified ID. Returns null if no object matching the ID is found.
     */
    public Topology findTopology(String topologyID) {
        return topologyList.stream().filter(topology -> topology.getId().equals(topologyID)).findAny().orElse(null);
    }

    /**
     * Unloads the topology from memory
     * @param topologyID String variable containing the topology ID
     * @return Returns true if topology is successfully found and unloaded.
     */
    public boolean deregisterTopology(String topologyID) {
        Topology topology = findTopology(topologyID);
        if (topology == null) return false;
        return topologyList.remove(topology);
    }

    public List<Topology> getTopologyList() {
        return topologyList;
    }
}
