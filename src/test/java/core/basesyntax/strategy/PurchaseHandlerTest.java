package core.basesyntax.strategy;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseHandlerTest {

    private OperationStrategy purchaseHandler = new PurchaseHandler();

    @BeforeEach
    void setUp() {
        purchaseHandler = new PurchaseHandler();
        Storage.inventory.put("apple", 10);
    }

    @AfterEach
    void tearDown() {
        Storage.inventory.clear();
    }

    @Test
    void execute_decreasesQuantity() {
        purchaseHandler.execute("apple", 5);
        assertEquals(5, Storage.inventory.get("apple"));
    }

    @Test
    void execute_invalidQuantity_throwsException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                purchaseHandler.execute("apple", -15));
        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    void execute_notEnoughQuantity_throwsException() {
        Storage.inventory.put("apple", 5);
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                purchaseHandler.execute("apple", 10));
        assertTrue(exception.getMessage().contains("Not enough product"));
    }

    @Test
    void isValid_positiveQuantity_true() {
        assertTrue(purchaseHandler.isValid("apple", 1));
    }

    @Test
    void isValid_zeroOrNegativeQuantity_false() {
        assertFalse(purchaseHandler.isValid("apple", 0));
        assertFalse(purchaseHandler.isValid("apple", -5));
    }
}
