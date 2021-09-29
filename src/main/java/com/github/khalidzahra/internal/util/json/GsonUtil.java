package com.github.khalidzahra.internal.util.json;

import com.github.khalidzahra.internal.device.Device;
import com.github.khalidzahra.internal.topology.Topology;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Khalid on 9/28/21.
 */
public class GsonUtil {

    /**
     * GsonBuilder object that will be used throughout project
     */
    private static final Gson GSON_OBJECT = new GsonBuilder()
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
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            topology = getGson().fromJson(reader, Topology.class);
        } catch (IOException e) {
            return null;
        }
        return topology;
    }

    /**
     * Saves the given topology to the specified file
     *
     * @param filePath String variable containing path to the JSON file
     * @param topology Topology object to be saved
     * @return Returns true if save was successful
     */
    public static boolean saveTopology(String filePath, Topology topology) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            getGson().toJson(topology, writer);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static Gson getGson() {
        return GSON_OBJECT;
    }
}
