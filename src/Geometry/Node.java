package Geometry;

public class Node extends Point2D{
    private static final int INFINITY = 1000000;

    private int distanceFromStart;
    private int distanceToEnd;

    public Node(){

    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public void setDistanceToInf(){
        this.distanceFromStart = INFINITY;
    }

    public void setDistanceToEnd(Node endNode){
        int Xdistance = Math.abs(endNode.getX() - this.getX());
        int Ydistance = Math.abs(endNode.getY() - this.getY());

        this.distanceToEnd = Xdistance + Ydistance;
    }

    public int getTotalDistance() {
        return distanceFromStart + distanceToEnd;
    }

}
