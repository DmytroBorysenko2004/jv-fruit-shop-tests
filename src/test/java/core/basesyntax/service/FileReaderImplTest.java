package core.basesyntax.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class FileReaderImplTest {

    private Path tempFile;

    @AfterEach
    void tearDown() throws IOException {
        if (tempFile != null) {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    void processFile_readsLinesCorrectly() throws IOException {
        tempFile = Files.createTempFile("testfile", ".txt");
        Files.writeString(tempFile, "First line of the file\nSecond line");

        FileReaderImpl reader = new FileReaderImpl();
        List<String> lines = reader.processFile(tempFile.toString());

        assertFalse(lines.isEmpty());
        assertEquals("First line of the file", lines.get(0));
    }

    @Test
    void processFile_nonexistentFile_throwsRuntimeException() {
        FileReaderImpl reader = new FileReaderImpl();

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                reader.processFile("nonexistent/path/file.csv"));

        assertTrue(exception.getMessage().contains("Error reading file"));
    }
}
