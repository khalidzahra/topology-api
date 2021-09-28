package com.github.khalidzahra.topology;

import com.github.khalidzahra.component.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalid on 9/28/21.
 */
public class Topology {

    private String id;
    private List<Component> components;

    public Topology(String id) {
        this.id = id;
        this.components = new ArrayList<>();
    }

    public Topology(String id, List<Component> componentList) {
        this.id = id;
        this.components = componentList;
    }

    public List<Component> getComponentsConnectedToNode(String nodeId) {
        return this.components.stream().filter(component -> component.getNetList().containsValue(nodeId)).toList();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public String getId() {
        return id;
    }

    public List<Component> getComponents() {
        return components;
    }
}
