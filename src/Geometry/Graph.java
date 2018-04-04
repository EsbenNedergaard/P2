package Geometry;

import java.util.*;

public class Graph {
    private ArrayList<Node> closedSet = new ArrayList<>();
    private PriorityQueue<Node> openSet;
    private ArrayList<Node> allNodes = new ArrayList<>();

    public Graph(ArrayList<Node> allNodes) {
        this.allNodes = allNodes;
        openSet = new PriorityQueue<>(allNodes.size(), new NodeComparator());
    }

    public ArrayList<Node> findShortestRoute(Node start, Node end){
        for(Node node : allNodes){
            if (node.equals(start)) {
                node.setDistanceFromStart(0);
                openSet.add(node);
            }
            else{
                node.setDistanceToInf();
            }
            node.setDistanceToEnd(end);
            node.setNeighbourNodes(allNodes);
        }

        while(!openSet.isEmpty()){
            Node current = openSet.poll();
            if(current.equals(end)){
                end = current;
                break;
            }

            closedSet.add(current);

            for (Node neighbour : current.getNeighbourNodes()){
                //We check if the current node is in the already checked nodes (closed set)
                if (closedSet.contains(neighbour)){
                    continue;
                }
                if (!openSet.contains(neighbour)) {
                    openSet.add(neighbour);
                }

                //A better path exists
                if(current.getDistanceFromStart() + 1 < neighbour.getDistanceFromStart()){
                    neighbour.setCameFrom(current);
                    neighbour.setDistanceFromStart(current.getDistanceFromStart() + 1);
                }
            }
        }
        return constructPath(start, end);
    }

    private ArrayList<Node> constructPath(Node start, Node end){

        ArrayList<Node> path = new ArrayList<>();

        Node next = end;

        while(!next.equals(start)){
            path.add(next);
            next = next.getCameFrom();
        }

        path.add(start);

        Collections.reverse(path);

        return path;
    }
}
