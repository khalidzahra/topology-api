package com.github.khalidzahra.topology;

import java.util.List;

/**
 * Created by Khalid on 9/28/21.
 */
public class TopologyRegistry {

    private static List<Topology> topologyList;

    public static void registerTopology(Topology topology) {
        topologyList.add(topology);
    }

    public static Topology findTopology(String id) {
        return topologyList.stream().filter(topology -> topology.getId().equals(id)).findAny().orElse(null);
    }

    public static boolean deregisterTopology(String id) {
        Topology topology = findTopology(id);
        if (topology == null) return false;
        return topologyList.remove(topology);
    }

    public static List<Topology> getTopologyList() {
        return topologyList;
    }
}
