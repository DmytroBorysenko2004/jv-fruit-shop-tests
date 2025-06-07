package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.Test;

class OperationTypeTest {

    @Test
    void fromCode_invalidCode_throwsIllegalArgumentException() {
        String invalidCode = "x";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FruitTransaction.OperationType.fromCode(invalidCode);
        });

        assertTrue(exception.getMessage().contains("Invalid operation code"));
    }
}
