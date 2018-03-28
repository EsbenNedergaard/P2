package Geometry;

import java.util.*;

public class Graph {

    private SortedSet<Node> openSet = new TreeSet<>(new NodeComparator());
    private Map<Node, Node> cameFrom = new HashMap<>();

    private Set<Node> allNodes;

    public Graph(Set<Node> allNodes) {
        this.allNodes = allNodes;


    }

    public List<Node> findShortestRoute(Node start, Node end){
        for(Node node : allNodes){

            node.setDistanceToInf();

            node.setDistanceToEnd(node);

        }

        start.setDistanceFromStart(0);

        openSet.add(start);

        while(!openSet.isEmpty()){
            Node current = openSet.first();

            if(current.equals(end)){
                return generateRoute();
            }


        }

    }

}
