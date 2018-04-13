package Geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTimeGridTest {
    private final int MAX_TIME = 3;
    private final int GRID_HEIGHT = 3;
    private final int GRID_LENGTH = 3;
    private SpaceTimeGrid spaceTimeGrid;


    @BeforeEach
    void beforeEach() {
        List<Node> inputSet;
        inputSet = new ArrayList<>();
        for (int x = 0; x < GRID_LENGTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                Node temp = new Node(new Point2D(x, y));
                inputSet.add(temp);
            }
        }

        BaseLayer baseLayer = new BaseLayer(inputSet);
        spaceTimeGrid = new SpaceTimeGrid(baseLayer, MAX_TIME);
    }

    @Test
    void getNodeLayerList() {
    }

    @Test
    void getMaxTime() {
    }

    @Test
    void getBaseLayer() {
    }

    @Test
    void getAllNodes() {
    }

    @Test
    void getNodePointer(){

    }

    @Test
    void removeNode() {

    }

    @Test
    void removeNeighbourNode() {
        //We make a pointer to (0,0), (0, 1) and (1,0) in the first layer
        Node temp1 = spaceTimeGrid.getNodePointer(0,0,0);
        Node temp2 = spaceTimeGrid.getNodePointer(0,1,0);
        Node temp3 = spaceTimeGrid.getNodePointer(1,0,0);

        //We make a pointer to (0,0) in the second layer
        Node nodeToRemove = spaceTimeGrid.getNodePointer(0,0,1);

        assertEquals(3,  temp1.getNeighbourNodes().size());
        assertEquals(4,  temp2.getNeighbourNodes().size());
        assertEquals(4,  temp3.getNeighbourNodes().size());

        spaceTimeGrid.removeNode(nodeToRemove);
        assertEquals(2, temp1.getNeighbourNodes().size());
        assertEquals(3, temp2.getNeighbourNodes().size());
        assertEquals(3, temp3.getNeighbourNodes().size());
    }

}