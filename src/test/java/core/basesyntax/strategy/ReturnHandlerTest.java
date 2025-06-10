package core.basesyntax.strategy;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReturnHandlerTest {

    private OperationStrategy returnHandler;

    @BeforeEach
    void setUp() {
        returnHandler = new ReturnHandler();
        Storage.inventory.put("banana", 10);
    }

    @AfterEach
    void tearDown() {
        Storage.inventory.clear();
    }

    @Test
    void execute_increasesQuantity() {
        returnHandler.execute("banana", 5);
        assertEquals(15, Storage.inventory.get("banana"));
    }

    @Test
    void execute_notEnoughQuantity_throwsException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                returnHandler.execute("banana", -15));
        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    void isValid_positiveQuantity_true() {
        assertTrue(returnHandler.isValid("banana", 1));
    }

    @Test
    void isValid_zeroOrNegativeQuantity_false() {
        assertFalse(returnHandler.isValid("banana", 0));
        assertFalse(returnHandler.isValid("banana", -1));
    }
}
