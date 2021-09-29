package com.github.khalidzahra;

import com.github.khalidzahra.internal.device.DeviceType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Khalid on 9/29/21.
 */
public class TopologyAPITest extends TestCase {

    private final Path resourceDirectory = Paths.get("src", "test", "resources");

    public void testReadJSON() {
        TopologyAPI topologyAPI = new TopologyAPI();
        assertTrue(topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology1.json"));
        assertFalse(topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology-tests.json"));
        assertFalse(topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology.json"));
        assertTrue(topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology2.json"));
    }

    public void testWriteJSON() {
        TopologyAPI topologyAPI = new TopologyAPI();
        // check return values of api methods
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology1.json");
        assertTrue(topologyAPI.writeJSON("top1", resourceDirectory.toAbsolutePath() + "/topology1-test.json"));
        assertFalse(topologyAPI.writeJSON("top2", resourceDirectory.toAbsolutePath() + "/topology1.json"));
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology2.json");
        assertTrue(topologyAPI.writeJSON("top2", resourceDirectory.toAbsolutePath() + "/topology2-test.json"));
        assertFalse(topologyAPI.writeJSON("top3", resourceDirectory.toAbsolutePath() + "/topology2.json"));
        // check if data is being saved correctly
        try {
            JsonElement top1 = JsonParser.parseReader(new FileReader(resourceDirectory.toAbsolutePath() + "/topology1.json"));
            JsonElement top1Test = JsonParser.parseReader(new FileReader(resourceDirectory.toAbsolutePath() + "/topology1-test.json"));
            JsonElement top2 = JsonParser.parseReader(new FileReader(resourceDirectory.toAbsolutePath() + "/topology2.json"));
            JsonElement top2Test = JsonParser.parseReader(new FileReader(resourceDirectory.toAbsolutePath() + "/topology2-test.json"));
            assertEquals(top1, top1Test);
            assertEquals(top2, top2Test);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void testQueryTopologies() {
        TopologyAPI topologyAPI = new TopologyAPI();
        assertEquals(topologyAPI.queryTopologies().size(), 0);
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology1.json");
        assertEquals(topologyAPI.queryTopologies().size(), 1);
        assertEquals(topologyAPI.queryTopologies().get(0).getId(), "top1");
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology2.json");
        assertEquals(topologyAPI.queryTopologies().size(), 2);
        assertEquals(topologyAPI.queryTopologies().get(1).getId(), "top2");
    }

    public void testDeleteTopology() {
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology1.json");
        assertEquals(topologyAPI.queryTopologies().size(), 1);
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology2.json");
        assertEquals(topologyAPI.queryTopologies().size(), 2);
        assertFalse(topologyAPI.deleteTopology("top3"));
        assertTrue(topologyAPI.deleteTopology("top1"));
        assertEquals(topologyAPI.queryTopologies().size(), 1);
        assertTrue(topologyAPI.deleteTopology("top2"));
        assertEquals(topologyAPI.queryTopologies().size(), 0);
    }

    public void testQueryDevices() {
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology1.json");
        assertEquals(topologyAPI.queryDevices("top1").size(), 2);
        topologyAPI.queryDevices("top1").forEach(device -> assertTrue(device.getDeviceType() == DeviceType.RESISTOR || device.getDeviceType() == DeviceType.NMOS));
        topologyAPI.queryDevices("top1").forEach(device -> assertFalse(device.getDeviceType() == DeviceType.CAPACITOR || device.getDeviceType() == DeviceType.PMOS));
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology2.json");
        assertEquals(topologyAPI.queryDevices("top2").size(), 2);
        topologyAPI.queryDevices("top2").forEach(device -> assertTrue(device.getDeviceType() == DeviceType.CAPACITOR || device.getDeviceType() == DeviceType.PMOS));
        topologyAPI.queryDevices("top2").forEach(device -> assertFalse(device.getDeviceType() == DeviceType.RESISTOR || device.getDeviceType() == DeviceType.NMOS));
    }

    public void testQueryDevicesWithNetlistNode() {
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology1.json");
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top1", "n1").size(), 2);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top1", "vdd").size(), 1);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top1", "vdd").get(0).getDeviceType(), DeviceType.RESISTOR);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top1", "vss").size(), 1);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top1", "vss").get(0).getDeviceType(), DeviceType.NMOS);
        topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology2.json");
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top2", "n1").size(), 2);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top2", "vdd").size(), 1);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top2", "vdd").get(0).getDeviceType(), DeviceType.CAPACITOR);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top2", "vss").size(), 1);
        assertEquals(topologyAPI.queryDevicesWithNetlistNode("top2", "vss").get(0).getDeviceType(), DeviceType.PMOS);

    }
}