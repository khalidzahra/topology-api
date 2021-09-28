package com.github.khalidzahra.component;

import com.github.khalidzahra.util.JsonKeyUtil;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Khalid on 9/28/21.
 */
public class Component {

    @SerializedName(value = JsonKeyUtil.COMPONENT_TYPE_KEY)
    private ComponentType componentType;
    private String id;
    @SerializedName(value = JsonKeyUtil.COMPONENT_PROPERTIES_KEY,
            alternate = {JsonKeyUtil.COMPONENT_PROPERTIES_RESISTANCE_KEY,
                    JsonKeyUtil.COMPONENT_PROPERTIES_CAPACITANCE_KEY,
                    JsonKeyUtil.COMPONENT_PROPERTIES_INDUCTANCE_KEY,
                    JsonKeyUtil.COMPONENT_PROPERTIES_TRANSISTOR_KEY})
    private ComponentProperties componentProperties;
    @SerializedName(value = JsonKeyUtil.COMPONENT_NETLIST_KEY)
    private HashMap<String, String> netList;

    public Component(ComponentType componentType, String id, ComponentProperties componentProperties, HashMap<String, String> netList) {
        this.componentType = componentType;
        this.id = id;
        this.componentProperties = componentProperties;
        this.netList = netList;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public String getId() {
        return id;
    }

    public ComponentProperties getComponentProperties() {
        return componentProperties;
    }

    public HashMap<String, String> getNetList() {
        return netList;
    }
}
