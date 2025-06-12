package core.basesyntax.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportContentGeneratorTest {

    private ReportContentGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new ReportContentGenerator();
    }

    @AfterEach
    void tearDown() {
        Storage.inventory.clear();
    }

    @Test
    void generateReportContent_returnsCorrectFormat_withOneFruit() {
        Storage.inventory.put("kiwi", 50);

        String expected = "fruit,quantity" + System.lineSeparator()
                + "kiwi,50" + System.lineSeparator();

        assertEquals(expected, generator.generateReportContent());
    }

    @Test
    void generateReportContent_returnsHeaderOnly_whenStorageEmpty() {
        String expected = "fruit,quantity" + System.lineSeparator();

        assertEquals(expected, generator.generateReportContent());
    }

    @Test
    void generateReportContent_returnsCorrectFormat_withMultipleFruits() {
        Storage.inventory.put("kiwi", 50);
        Storage.inventory.put("apple", 20);
        Storage.inventory.put("banana", 30);

        String result = generator.generateReportContent();

        String expectedHeader = "fruit,quantity" + System.lineSeparator();
        assertEquals(true, result.startsWith(expectedHeader));

        assertTrue(result.contains("kiwi,50"));
        assertTrue(result.contains("apple,20"));
        assertTrue(result.contains("banana,30"));
    }
}
