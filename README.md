# Topology API
This is a Java API library for loading, saving and handling electronic topologies saved in JSON format. 
### Why Java?
Java was the language chosen for this project because of its strong object oriented nature and due to the easy integration with JUnit and Maven. Another factor was also the existence of the Gson library for handling JSON objects.
### How to use
- Package the project with maven to produce a JAR file
- Add the JAR file to your project structure as a dependency
- Create an instance of the API class when your application starts

Example:

```java
    public Main {
        public static void main(String[] args) {
            TopologyAPI topologyAPI = new ToplogyAPI();
            toplogyAPI.readJSON("path/to/file");
            Topology topology = topologyAPI.findTopology("top1");
        }
    }
```
### Additional design considerations
Added a ```findTopology(topologyID)``` method so that end user can use this method to access a specific topology instead of searching through the topology list.