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

    public boolean loadAndRegisterTopology(String filePath) {
        Topology topology = GsonHandler.loadTopology(filePath);
        return registerTopology(topology);
    }

    public boolean saveTopology(String topologyID, String filePath) {
        Topology topology = findTopology(topologyID);
        if (topology == null) return false;
        return GsonHandler.saveTopology(filePath, topology);
    }

    public boolean registerTopology(Topology topology) {
        if (topology == null) return false;
        return topologyList.add(topology);
    }

    public Topology findTopology(String id) {
        return topologyList.stream().filter(topology -> topology.getId().equals(id)).findAny().orElse(null);
    }

    public boolean deregisterTopology(String topologyID) {
        Topology topology = findTopology(topologyID);
        if (topology == null) return false;
        return topologyList.remove(topology);
    }

    public List<Topology> getTopologyList() {
        return topologyList;
    }
}
