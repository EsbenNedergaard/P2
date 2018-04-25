package GraphicalWarehouse.InteractionHandler;

import GraphicalWarehouse.Interaction.Handler.InputFieldDataHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputFieldDataHandlerTest {
    InputFieldDataHandler handler;

    @BeforeEach
    void beforeEach() {
        handler = new InputFieldDataHandler();
    }

    @Test
    void testGenerateProductID_01() {
        String str = "234f5aa";
        handler.generateProductIDList(str);

        assertEquals("2345", handler.generateProductIDString());
    }

    @Test
    void testGenerateProductID_02() {
        String str = "234, f5aa, 2453, kdflk,, 3i4";
        handler.generateProductIDList(str);

        assertEquals("234, 5, 2453, 34", handler.generateProductIDString());
    }

    @Test
    void testGenerateProductID_03() {
        String str = "234, f5aa, 245.3, kdflk,, 3i4";
        handler.generateProductIDList(str);

        assertEquals("234, 5, 2453, 34", handler.generateProductIDString());
    }
}