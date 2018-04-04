package Geometry;

import java.util.*;

public class Graph {
    private ArrayList<Node> closedSet = new ArrayList<>();
    private SortedSet<Node> openSet = new TreeSet<>(new NodeComparator());
    private ArrayList<Node> allNodes = new ArrayList<>();

    public Graph(ArrayList<Node> allNodes) {
        this.allNodes = allNodes;
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
            Node current = openSet.first();
            openSet.remove(openSet.first());
            if(current.equals(end)){
                end = current;
                break;
            }

            closedSet.add(current);

            for (Node neighbour : current.getNeighbourNodes()){
                if (closedSet.contains(neighbour)){
                    continue;
                }
                openSet.add(neighbour);

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
