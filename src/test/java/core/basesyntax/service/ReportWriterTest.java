package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportWriterTest {

    private static final String INVALID_OUTPUT_PATH = "/invalid/path/output.txt";
    private static final String TEST_CONTENT = "Test report content";

    private Path testOutput;

    @BeforeEach
    void setup() throws IOException {
        if (testOutput == null) {
            testOutput = Files.createTempFile("testOutputReport", ".txt");
        }
    }

    @AfterEach
    void cleanup() throws IOException {
        if (testOutput != null) {
            Files.deleteIfExists(testOutput);
            testOutput = null;
        }
    }

    @Test
    void writeReport_writesContentToFile() throws IOException {
        ReportWriter writer = new ReportWriter();

        writer.writeReport(TEST_CONTENT, testOutput.toString());

        String fileContent = Files.readString(testOutput);
        assertTrue(fileContent.contains(TEST_CONTENT));
    }

    @Test
    void writeReport_throwsRuntimeException_whenCannotWrite() {
        ReportWriter writer = new ReportWriter();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                writer.writeReport(TEST_CONTENT, INVALID_OUTPUT_PATH));

        assertTrue(exception.getMessage().contains("Error writing report"));
    }
}
