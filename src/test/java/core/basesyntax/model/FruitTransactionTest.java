package core.basesyntax.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class FruitTransactionTest {

    @Test
    void equals_sameValues_shouldReturnTrue() {
        FruitTransaction t1 = new FruitTransaction("b", "apple", 100);
        FruitTransaction t2 = new FruitTransaction("b", "apple", 100);

        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void equals_differentQuantity_shouldReturnFalse() {
        FruitTransaction t1 = new FruitTransaction("b", "apple", 100);
        FruitTransaction t2 = new FruitTransaction("b", "apple", 90);

        assertNotEquals(t1, t2);
    }

    @Test
    void equals_differentFruit_shouldReturnFalse() {
        FruitTransaction t1 = new FruitTransaction("b", "apple", 100);
        FruitTransaction t2 = new FruitTransaction("b", "banana", 100);

        assertNotEquals(t1, t2);
    }

    @Test
    void equals_nullOrOtherClass_shouldReturnFalse() {
        FruitTransaction t1 = new FruitTransaction("b", "apple", 100);

        assertNotEquals(t1, null);
        assertNotEquals(t1, "some string");
    }

    @Test
    void toString_shouldContainAllFields() {
        FruitTransaction t = new FruitTransaction("s", "banana", 50);
        String output = t.toString();

        assertTrue(output.contains("operation='s'"));
        assertTrue(output.contains("fruit='banana'"));
        assertTrue(output.contains("quantity=50"));
    }
}
