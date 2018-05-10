package WarehouseSimulation.GraphicalObjects;

import BackEnd.Geometry.Node;
import BackEnd.Geometry.Point2D;
import WarehouseSimulation.GraphicalObjects.Colors.Colors;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import static Warehouse.GUIWarehouse.TILE_SIZE;

public class OrderPickerGraphic extends Circle {

    private final static double PICKER_SPEED_IN_MPS = 2;
    private final static int SCREEN_HZ = 60; /* change this to screen HZ */
    private List<Node> routeList;
    private double moveDistancePerUpdate;
    private int indexOfTargetNode;

    public OrderPickerGraphic(List<Node> routeList, String color) {
        this.routeList = routeList;
        this.moveDistancePerUpdate = calculateMoveDistancePerUpdate();
        this.indexOfTargetNode = 1;
        setDesign(color);
        setStartLocation();
    }

    public OrderPickerGraphic(List<Node> routeList) {
        this.routeList = routeList;
        this.moveDistancePerUpdate = calculateMoveDistancePerUpdate();
        this.indexOfTargetNode = 1;
        setDesign(Colors.BLUE.getColor());
        setStartLocation();
    }

    private void setStartLocation() {
        relocate(2.5, 2.5);
        setTranslateX(routeList.get(0).getX() * TILE_SIZE);
        setTranslateY(routeList.get(0).getY() * TILE_SIZE);
    }

    private void setDesign(String color) {
        setRadius(TILE_SIZE / 2.5);
        setFill(Color.valueOf(color));
    }

    private double calculateMoveDistancePerUpdate() {
        return PICKER_SPEED_IN_MPS * TILE_SIZE * 1 / SCREEN_HZ;
    }

    // Call this 60 times / sec
    public boolean move(final int UPDATE_COUNTER) {
        if(!routeIsDone()) {
            if(moveIsVertical())
                moveVertical();
            else if (moveIsHorizontal())
                moveHorizontal();
            else
                waiting();

            if(isAtTargetNode(UPDATE_COUNTER)) {
                forcePickerToTargetNode();
                moveOnToNextTargetNode();
            }
            return true;
        }
        return false;
    }

    private boolean routeIsDone() {
        return indexOfTargetNode == routeList.size();
    }

    private boolean moveIsVertical() {
        return getLastVisitedNode().getXPixels() != getTargetNode().getXPixels() &&
               getLastVisitedNode().getYPixels() == getTargetNode().getYPixels();
    }

    private boolean moveIsHorizontal() {
        return getLastVisitedNode().getYPixels() != getTargetNode().getYPixels() &&
               getLastVisitedNode().getXPixels() == getTargetNode().getXPixels();
    }

    private void moveVertical() {
        if(isLeftMove()) moveLeft();
        else moveRight();
    }

    private boolean isLeftMove() {
        return getLastVisitedNode().getX() > getTargetNode().getX();
    }

    private void moveLeft() {
        setTranslateX(getTranslateX() - moveDistancePerUpdate);
    }

    private void moveRight() {
        setTranslateX(getTranslateX() + moveDistancePerUpdate);
    }

    private void moveHorizontal() {
        if(isUpMove()) moveUp();
        else moveDown();
    }

    private boolean isUpMove() {
        return getLastVisitedNode().getY() < getTargetNode().getY();
    }

    private void moveUp() {
        setTranslateY(getTranslateY() + moveDistancePerUpdate);
    }

    private void moveDown() {
        setTranslateY(getTranslateY() - moveDistancePerUpdate);
    }

    // TODO: Do something while waiting?
    private void waiting() {}

    private Node getLastVisitedNode() {
        return this.routeList.get(indexOfTargetNode - 1);
    }

    private Node getTargetNode() {
        if (indexOfTargetNode >= routeList.size())
            throw new IndexOutOfBoundsException("Index out of bound");

        return this.routeList.get(indexOfTargetNode);
    }

    Point2D getCurrentPosition() {
        return new Point2D((int) getTranslateX() / TILE_SIZE, (int) getTranslateY() / TILE_SIZE);
    }

    private boolean isAtTargetNode(final int UPDATE_COUNTER) {
        return UPDATE_COUNTER % (TILE_SIZE / moveDistancePerUpdate) == 0;
    }

    private void forcePickerToTargetNode() {
        setTranslateX(getTargetNode().getXPixels());
        setTranslateY(getTargetNode().getYPixels());
    }

    private void moveOnToNextTargetNode() {
        indexOfTargetNode++;
    }

    public void startOver() {
        indexOfTargetNode = 1;
    }

}