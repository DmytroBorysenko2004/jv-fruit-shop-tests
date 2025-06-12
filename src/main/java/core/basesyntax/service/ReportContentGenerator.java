package core.basesyntax.service;

import core.basesyntax.db.Storage;

public class ReportContentGenerator {

    private static final String HEADER = "fruit,quantity" + System.lineSeparator();
    private static final String SEPARATOR = ",";

    public String generateReportContent() {
        StringBuilder reportContent = new StringBuilder();
        reportContent.append(HEADER);

        Storage.inventory.keySet().stream()
                .sorted()
                .forEach(fruit -> reportContent.append(fruit)
                        .append(SEPARATOR)
                        .append(Storage.inventory.get(fruit))
                        .append(System.lineSeparator()));

        return reportContent.toString();
    }
}
