package WarehouseSimulation.InteractionHandler;

import WarehouseSimulation.Exception.IllegalTextInputException;
import WarehouseSimulation.GraphicalObjects.Interaction.Handler.InputFieldDataHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InputFieldDataHandlerTest {
    InputFieldDataHandler handler;

    @BeforeEach
    void beforeEach() {
        handler = new InputFieldDataHandler();
    }

    @Test
    void testGenerateProductID_01() {
        String str = "234f5aa";

        assertThrows(IllegalTextInputException.class, () -> handler.generateProductIDList(str));
    }

    @Test
    void testGenerateProductID_02() {
        String str = "234,, 80, 40";

        assertThrows(IllegalTextInputException.class, () -> handler.generateProductIDList(str));
    }

    @Test
    void testGenerateProductID_03() {
        String str = "10, 202, ";

        assertThrows(IllegalTextInputException.class, () -> handler.generateProductIDList(str));
    }
}