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
        //We make a pointer to (0,0) in the first layer
        Node temp = spaceTimeGrid.getNodeLayerList().get(0).getNodeList().get(0);

        //We make a pointer to (0,0) in the second layer
        Node nodeToRemove = spaceTimeGrid.getNodeLayerList().get(1).getNodeList().get(0);

        assertEquals(3,  temp.getNeighbourNodes().size());
        spaceTimeGrid.removeNode(nodeToRemove);
        assertEquals(2, temp.getNeighbourNodes().size());
        System.out.println("TEST");
    }
}