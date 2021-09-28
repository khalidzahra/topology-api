package com.github.khalidzahra.util.json;

import com.github.khalidzahra.component.Component;
import com.github.khalidzahra.util.JsonKeyUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Locale;

/**
 * Created by Khalid on 9/28/21.
 */
public class ComponentSerializer implements JsonSerializer<Component> {

    @Override
    public JsonElement serialize(Component component, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JsonKeyUtil.COMPONENT_TYPE_KEY, component.getComponentType().toString().toLowerCase(Locale.ROOT));
        jsonObject.addProperty("id", component.getId());
        jsonObject.add(component.getComponentType().getSerializedPropertyName(), jsonSerializationContext.serialize(component.getComponentProperties()));
        jsonObject.add(JsonKeyUtil.COMPONENT_NETLIST_KEY, jsonSerializationContext.serialize(component.getNetList()));

        return jsonObject;
    }
}
