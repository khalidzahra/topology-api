package com.github.khalidzahra.internal.util.json;

import com.github.khalidzahra.internal.device.Device;
import com.github.khalidzahra.internal.util.JsonKeyUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Locale;

/**
 * Created by Khalid on 9/28/21.
 * <p>
 * Custom JsonSerializer implementation to be used by the GsonBuilder object in GsonHandler
 */
public class DeviceSerializer implements JsonSerializer<Device> {

    @Override
    public JsonElement serialize(Device device, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonKeyUtil.DEVICE_TYPE_KEY, device.getDeviceType().toString().toLowerCase(Locale.ROOT));
        jsonObject.addProperty("id", device.getDeviceId());
        jsonObject.add(device.getDeviceType().getSerializedPropertyName(), jsonSerializationContext.serialize(device.getDeviceProperties()));
        jsonObject.add(JsonKeyUtil.DEVICE_NETLIST_KEY, jsonSerializationContext.serialize(device.getNetList()));

        return jsonObject;
    }
}
