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

    private Path testOutput;

    @BeforeEach
    void setup() throws IOException {
        testOutput = Files.createTempFile("testOutputReport", ".txt");
    }

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(testOutput);
    }

    @Test
    void writeReport_writesContentToFile() throws IOException {
        ReportWriter writer = new ReportWriter();
        String content = "Test report content";

        writer.writeReport(content, testOutput.toString());

        String fileContent = Files.readString(testOutput);
        assertTrue(fileContent.contains(content));
    }

    @Test
    void writeReport_throwsRuntimeException_whenCannotWrite() {
        ReportWriter writer;
        writer = new ReportWriter();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            writer.writeReport("content", "/invalid/path/output.txt");
        });
        assertTrue(exception.getMessage().contains("Error writing report"));
    }
}
