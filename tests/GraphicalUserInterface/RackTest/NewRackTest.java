package GraphicalUserInterface.RackTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import Exceptions.IllegalRackDimensionException;
import GraphicalUserInterface.Rack;
import org.junit.jupiter.api.Test;

public class NewRackTest {

    Rack testRack;

    @Test
    void testNewRack01() {
        assertThrows(IllegalRackDimensionException.class, () -> {
            testRack = new Rack("A", 2, 2, 0, 0);
        });
    }

    @Test
    void testNewRack02() {
        assertThrows(IllegalRackDimensionException.class, () -> {
            testRack = new Rack("A", 10, 10, 1, 1);
        });
    }

}
