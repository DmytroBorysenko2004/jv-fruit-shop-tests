package core.basesyntax.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.model.FruitTransaction;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataConverterTest {

    private final DataConverter dataConverter = new DataConverter();

    @Test
    void convertDataToTransactions_validData_ok() {
        List<String> input = List.of("b,apple,100", "s,banana,50");
        List<FruitTransaction> result = dataConverter.convertDataToTransactions(input);

        List<FruitTransaction> expected = List.of(
                new FruitTransaction("b", "apple", 100),
                new FruitTransaction("s", "banana", 50)
        );

        assertEquals(expected, result);
    }

    @Test
    void convertDataToTransactions_invalidFormat_throwsException() {
        List<String> input = List.of("invalid,line");
        assertThrows(IllegalArgumentException.class, () ->
                dataConverter.convertDataToTransactions(input));
    }

    @Test
    void convertDataToTransactions_invalidQuantity_throwsException() {
        List<String> input = List.of("b,apple,not_a_number");
        assertThrows(IllegalArgumentException.class, () ->
                dataConverter.convertDataToTransactions(input));
    }

    @Test
    void convertDataToTransactions_emptyList_returnsEmptyList() {
        List<String> input = List.of();
        List<FruitTransaction> result = dataConverter.convertDataToTransactions(input);

        assertEquals(0, result.size(), "Expected empty list when input is empty");
    }
}
