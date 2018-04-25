package GraphicalWarehouse.InteractionHandler;

import Exceptions.IllegalTextInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

        assertThrows(IllegalTextInputException.class, () -> handler.generateProductIDList(str));
    }

    @Test
    void testGenerateProductID_02() {
        String str = " ";

        assertThrows(IllegalTextInputException.class, () -> handler.generateProductIDList(str));
    }

    @Test
    void testGenerateProductID_03() {
        String str = "234, 5, 740, 45, 34k";

        assertThrows(IllegalTextInputException.class, () -> handler.generateProductIDList(str));
    }

    @Test
    void testGenerateProductID_04() {

    }
}