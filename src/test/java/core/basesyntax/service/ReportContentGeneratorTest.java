package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportContentGeneratorTest {

    private ReportContentGenerator generator;

    @BeforeEach
    void setUp() {
        Storage.inventory.clear();
        Storage.inventory.put("kiwi", 50);
        generator = new ReportContentGenerator();
    }

    @Test
    void generateReportContent_returnsCorrectFormat() {
        String expected = "fruit,quantity" + System.lineSeparator()
                + "kiwi,50" + System.lineSeparator();
        assertEquals(expected, generator.generateReportContent());
    }
}
