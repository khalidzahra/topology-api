package com.github.khalidzahra.internal.util.json;

import com.github.khalidzahra.internal.device.Device;
import com.github.khalidzahra.internal.topology.Topology;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Created by Khalid on 9/28/21.
 */
public class GsonHandler {

    /**
     * GsonBuilder object that will be used throughout project
     */
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Device.class, new DeviceSerializer())
            .setPrettyPrinting().create();

    /**
     * Reads specified JSON file and creates Topology object
     *
     * @param filePath String variable containing path to the JSON file
     * @return Returns Topology object. Returns null if file does not exist or object is not found within the file.
     */
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

    /**
     * Saves the given topology to the specified file
     * @param filePath String variable containing path to the JSON file
     * @param topology Topology object to be saved
     * @return Returns true if save was successful
     */
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
