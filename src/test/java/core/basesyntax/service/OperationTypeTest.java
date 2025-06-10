package core.basesyntax.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Test;

class OperationTypeTest {

    @Test
    void fromCode_validCodes_ok() {
        assertEquals(FruitTransaction.OperationType.BALANCE,
                FruitTransaction.OperationType.fromCode("b"));
        assertEquals(FruitTransaction.OperationType.SUPPLY,
                FruitTransaction.OperationType.fromCode("s"));
        assertEquals(FruitTransaction.OperationType.PURCHASE,
                FruitTransaction.OperationType.fromCode("p"));
        assertEquals(FruitTransaction.OperationType.RETURN,
                FruitTransaction.OperationType.fromCode("r"));
    }

    @Test
    void fromCode_invalidCode_notOk() {
        String invalidCode = "x";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FruitTransaction.OperationType.fromCode(invalidCode);
        });

        assertTrue(exception.getMessage().contains("Invalid operation code"));
    }
}
