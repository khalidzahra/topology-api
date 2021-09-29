package com.github.khalidzahra;

import com.github.khalidzahra.internal.device.DeviceType;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Khalid on 9/29/21.
 */
public class TopologyAPITest extends TestCase {

    private final Path resourceDirectory = Paths.get("src", "test", "resources");
    private final Path top1Path = Paths.get(resourceDirectory.toAbsolutePath() + "/topology1.json");
    private final Path top2Path = Paths.get(resourceDirectory.toAbsolutePath() + "/topology2.json");

    @Test
    public void testReadJSON() {
        TopologyAPI topologyAPI = new TopologyAPI();
        assertTrue("Should return true", topologyAPI.readJSON(top1Path.toAbsolutePath().toString()));
        assertFalse("Should return false", topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology-tests.json"));
        assertFalse("Should return false", topologyAPI.readJSON(resourceDirectory.toAbsolutePath() + "/topology.json"));
        assertTrue("Should return true", topologyAPI.readJSON(top2Path.toAbsolutePath().toString()));
    }

    @Test
    public void testWriteJSON() {
        TopologyAPI topologyAPI = new TopologyAPI();
        // check return values of api methods
        topologyAPI.readJSON(top1Path.toAbsolutePath().toString());
        assertTrue("Should return true", topologyAPI.writeJSON("top1", resourceDirectory.toAbsolutePath() + "/topology1-test.json"));
        assertFalse("Should return false", topologyAPI.writeJSON("top2", top1Path.toAbsolutePath().toString()));
        topologyAPI.readJSON(top2Path.toAbsolutePath().toString());
        assertTrue("Should return true", topologyAPI.writeJSON("top2", resourceDirectory.toAbsolutePath() + "/topology2-test.json"));
        assertFalse("Should return false", topologyAPI.writeJSON("top3", top2Path.toAbsolutePath().toString()));
        // check if data is being saved correctly
        try {
            JsonElement top1 = JsonParser.parseReader(Files.newBufferedReader(top1Path));
            JsonElement top1Test = JsonParser.parseReader(Files.newBufferedReader(Paths.get(resourceDirectory.toAbsolutePath() + "/topology1-test.json")));
            JsonElement top2 = JsonParser.parseReader(Files.newBufferedReader(Paths.get(top2Path.toAbsolutePath().toString())));
            JsonElement top2Test = JsonParser.parseReader(Files.newBufferedReader(Paths.get(resourceDirectory.toAbsolutePath() + "/topology2-test.json")));
            assertEquals("Should hold the same data", top1, top1Test);
            assertEquals("Should hold the same data", top2, top2Test);
        } catch (IOException ignored) {
        }
    }

    @Test
    public void testQueryTopologies() {
        TopologyAPI topologyAPI = new TopologyAPI();
        assertEquals("Should have 0 elements", 0, topologyAPI.queryTopologies().size());
        topologyAPI.readJSON(top1Path.toAbsolutePath().toString());
        assertEquals("Should have 1 element", 1, topologyAPI.queryTopologies().size());
        assertEquals("Should have the id 'top1'", "top1", topologyAPI.queryTopologies().get(0).getTopologyId());
        topologyAPI.readJSON(top2Path.toAbsolutePath().toString());
        assertEquals("Should have 2 elements", 2, topologyAPI.queryTopologies().size());
        assertEquals("Should have the id 'top2'", "top2", topologyAPI.queryTopologies().get(1).getTopologyId());
    }

    @Test
    public void testDeleteTopology() {
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.readJSON(top1Path.toAbsolutePath().toString());
        assertEquals("Should have 1 element", 1, topologyAPI.queryTopologies().size());
        topologyAPI.readJSON(top2Path.toAbsolutePath().toString());
        assertEquals("Should have 2 elements", 2, topologyAPI.queryTopologies().size());
        assertFalse("Should return false", topologyAPI.deleteTopology("top3"));
        assertTrue("Should return true", topologyAPI.deleteTopology("top1"));
        assertEquals("Should have 1 element", 1, topologyAPI.queryTopologies().size());
        assertTrue("Should return true", topologyAPI.deleteTopology("top2"));
        assertEquals("Should have 0 elements", 0, topologyAPI.queryTopologies().size());
    }

    @Test
    public void testQueryDevices() {
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.readJSON(top1Path.toAbsolutePath().toString());
        assertEquals("Should have 2 elements", 2, topologyAPI.queryDevices("top1").size());
        topologyAPI.queryDevices("top1").forEach(device -> assertTrue("Should return true", device.getDeviceType() == DeviceType.RESISTOR || device.getDeviceType() == DeviceType.NMOS));
        topologyAPI.queryDevices("top1").forEach(device -> assertFalse("Should return false", device.getDeviceType() == DeviceType.CAPACITOR || device.getDeviceType() == DeviceType.PMOS));
        topologyAPI.readJSON(top2Path.toAbsolutePath().toString());
        assertEquals("Should have 2 elements", 2, topologyAPI.queryDevices("top2").size());
        topologyAPI.queryDevices("top2").forEach(device -> assertTrue("Should return true", device.getDeviceType() == DeviceType.CAPACITOR || device.getDeviceType() == DeviceType.PMOS));
        topologyAPI.queryDevices("top2").forEach(device -> assertFalse("Should return false", device.getDeviceType() == DeviceType.RESISTOR || device.getDeviceType() == DeviceType.NMOS));
    }

    @Test
    public void testQueryDevicesWithNetlistNode() {
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.readJSON(top1Path.toAbsolutePath().toString());
        assertEquals("Should have 2 elements", 2, topologyAPI.queryDevicesWithNetlistNode("top1", "n1").size());
        assertEquals("Should have 1 element", 1, topologyAPI.queryDevicesWithNetlistNode("top1", "vdd").size());
        assertEquals("Should have a device type of RESISTOR", DeviceType.RESISTOR, topologyAPI.queryDevicesWithNetlistNode("top1", "vdd").get(0).getDeviceType());
        assertEquals("Should have 1 element", 1, topologyAPI.queryDevicesWithNetlistNode("top1", "vss").size());
        assertEquals("Should have a device type of NMOS", DeviceType.NMOS, topologyAPI.queryDevicesWithNetlistNode("top1", "vss").get(0).getDeviceType());
        topologyAPI.readJSON(top2Path.toAbsolutePath().toString());
        assertEquals("Should have 2 elements", 2, topologyAPI.queryDevicesWithNetlistNode("top2", "n1").size());
        assertEquals("Should have 1 element", 1, topologyAPI.queryDevicesWithNetlistNode("top2", "vdd").size());
        assertEquals("Should have a device type of CAPACITOR", DeviceType.CAPACITOR, topologyAPI.queryDevicesWithNetlistNode("top2", "vdd").get(0).getDeviceType());
        assertEquals("Should have 1 element", 1, topologyAPI.queryDevicesWithNetlistNode("top2", "vss").size());
        assertEquals("Should have a device type of PMOS", DeviceType.PMOS, topologyAPI.queryDevicesWithNetlistNode("top2", "vss").get(0).getDeviceType());

    }

    @Test
    public void testFindTopology() {
        TopologyAPI topologyAPI = new TopologyAPI();
        topologyAPI.readJSON(top1Path.toAbsolutePath().toString());
        assertNotNull("Should not be null", topologyAPI.findTopology("top1"));
        assertEquals("Should have an id of 'top1'", "top1", topologyAPI.findTopology("top1").getTopologyId());
        topologyAPI.readJSON(top2Path.toAbsolutePath().toString());
        assertNotNull("Should not be null", topologyAPI.findTopology("top2"));
        assertEquals("Should have an id of 'top2'", "top2", topologyAPI.findTopology("top2").getTopologyId());
        assertNull("Should be null", topologyAPI.findTopology("top3"));
        assertNull("Should be null", topologyAPI.findTopology("top4"));
        topologyAPI.deleteTopology("top1");
        assertNull("Should be null", topologyAPI.findTopology("top1"));
        topologyAPI.deleteTopology("top2");
        assertNull("Should be null", topologyAPI.findTopology("top2"));
    }
}