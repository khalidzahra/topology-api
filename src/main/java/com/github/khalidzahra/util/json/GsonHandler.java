package com.github.khalidzahra.util.json;

import com.github.khalidzahra.component.Component;
import com.github.khalidzahra.topology.Topology;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.*;

/**
 * Created by Khalid on 9/28/21.
 */
public class GsonHandler {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Component.class, new ComponentSerializer())
            .setPrettyPrinting().create();

    public static Topology loadTopology(String filePath) {
        Topology topology;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            topology = getGson().fromJson(reader, Topology.class);
        } catch (FileNotFoundException e) {
            return null;
        }
        return topology;
    }

    public static boolean saveTopology(String filePath, Topology topology) {
        try {
            getGson().toJson(topology, new FileWriter(filePath));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static Gson getGson() {
        return gson;
    }
}
