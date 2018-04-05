package Geometry;

import java.util.ArrayList;

/*TODO: få lavet et nabo-system i forhold til vores varehus, som ved hvilke punkter man kan gå fra og til, da det gør vi kan være ligeglade
  med, hvilke punkter vi ikke kan gå igennem, hvis vi bare har ende punkterne af gangene, og kan afgøre, fra hvilke punkter man kan gå til andre punkter,
  nemmeste ville nok være at lave en liste af "right end points" og en liste af "left end points" også kan bare tjekke om varens y-koordinat stemmer overens med
  et endepunkt, så kører vi bare igennem et punkt efter et andet, og tager dem med lavest f-værdi som er naboer til vores nuværende punkt indtil vi når slutpunktet  */

public class Node extends Point2D implements Comparable<Node> {
    private static final int INFINITY = 1000000;
    private int distanceFromStart;
    private int distanceToEnd;
    private Node cameFrom;
    private ArrayList<Node> neighbourNodes;
    private int time;

    public Node(Point2D p, int time) {
        super(p);
        this.time = time;
    }

    public Node(Point2D p) {
        super(p);
    }

    public int getTime() {
        return time;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public ArrayList<Node> getNeighbourNodes() {
        return neighbourNodes;
    }

    public Node getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public void setDistanceToInf() {
        this.distanceFromStart = INFINITY;
    }

    public void setDistanceToEnd(Node endNode) {
        int Xdistance = Math.abs(endNode.getX() - this.getX());
        int Ydistance = Math.abs(endNode.getY() - this.getY());

        this.distanceToEnd = Xdistance + Ydistance;
    }

    public void setNeighbourNodes(ArrayList<Node> allNodes) {
        neighbourNodes = new ArrayList<Node>();
        for (Node node : allNodes) {
            if (this.isNeighbour(node)) {
                neighbourNodes.add(node);
            }
        }
    }

    private boolean isNeighbour(Node node) {
        if (this.getTime() + 1 == node.getTime()) {
            if (this.getX() == node.getX() + 1 && this.getY() == node.getY()) {
                return true;
            } else if (this.getX() == node.getX() - 1 && this.getY() == node.getY()) {
                return true;
            } else if (this.getX() == node.getX() && this.getY() == node.getY() + 1) {
                return true;
            } else if (this.getX() == node.getX() && this.getY() == node.getY() - 1) {
                return true;
            } else if (this.getX() == node.getY() && this.getY() == node.getY()) {
                return true;
            }
        }
        return false;
    }

    public int getTotalDistance() {
        return distanceFromStart + distanceToEnd;
    }

    @Override
    public int compareTo(Node that) {
        NodeComparator myComp = new NodeComparator();
        return myComp.compare(this, that);
    }
}
