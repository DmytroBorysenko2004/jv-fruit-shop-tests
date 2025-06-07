package core.basesyntax;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import core.basesyntax.strategy.BalanceHandler;
import core.basesyntax.strategy.OperationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BalanceHandlerTest {

    private final OperationStrategy balanceHandler = new BalanceHandler();

    @BeforeEach
    void setUp() {
        Storage.inventory.clear();
    }

    @Test
    void execute_validInput_addsToInventory() {
        balanceHandler.execute("apple", 100);
        assertEquals(100, Storage.inventory.get("apple"));
    }

    @Test
    void execute_notEnoughQuantity_throwsException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                balanceHandler.execute("apple", -15));
        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    void isValid_zeroQuantity_true() {
        assertTrue(balanceHandler.isValid("apple", 0));
    }

    @Test
    void isValid_negativeQuantity_false() {
        assertFalse(balanceHandler.isValid("apple", -1));
    }
}
