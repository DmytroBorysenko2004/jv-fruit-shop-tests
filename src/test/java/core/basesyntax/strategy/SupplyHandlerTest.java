package core.basesyntax.strategy;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyHandlerTest {

    private OperationStrategy supplyHandler;

    @BeforeEach
    void setUp() {
        supplyHandler = new SupplyHandler();
    }

    @AfterEach
    void tearDown() {
        Storage.inventory.clear();
    }

    @Test
    void execute_addsQuantity() {
        supplyHandler.execute("orange", 10);
        assertEquals(10, Storage.inventory.get("orange"));
    }

    @Test
    void execute_notEnoughQuantity_throwsException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                supplyHandler.execute("orange", -15));
        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    void isValid_positiveQuantity_true() {
        assertTrue(supplyHandler.isValid("orange", 1));
    }

    @Test
    void isValid_zeroOrNegativeQuantity_false() {
        assertFalse(supplyHandler.isValid("orange", 0));
        assertFalse(supplyHandler.isValid("orange", -5));
    }
}
