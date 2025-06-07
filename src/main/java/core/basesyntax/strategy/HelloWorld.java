package core.basesyntax.strategy;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.FileReader;
import core.basesyntax.service.FileReaderImpl;
import core.basesyntax.service.FruitShopService;
import core.basesyntax.service.ReportContentGenerator;
import core.basesyntax.service.ReportWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorld {

    private static final String DEFAULT_INPUT_REPORT = "src/main/java/resources/reportToRead.csv";
    private static final String OUTPUT_REPORT = "src/main/java/resources/finalReport.csv";

    public static void main(String[] args) throws IOException {
        String inputFileName = args.length > 0 ? args[0] : DEFAULT_INPUT_REPORT;

        FileReader fileReader = (FileReader) new FileReaderImpl();
        List<String> inputReport = fileReader.processFile(inputFileName);

        DataConverter dataConverter = new DataConverter();
        final List<FruitTransaction> transactions =
                dataConverter.convertDataToTransactions(inputReport);

        Map<FruitTransaction.OperationType, OperationStrategy> operationHandlers = new HashMap<>();
        operationHandlers.put(FruitTransaction.OperationType.BALANCE, new BalanceHandler());
        operationHandlers.put(FruitTransaction.OperationType.PURCHASE, new PurchaseHandler());
        operationHandlers.put(FruitTransaction.OperationType.RETURN, new ReturnHandler());
        operationHandlers.put(FruitTransaction.OperationType.SUPPLY, new SupplyHandler());

        FruitShopService shopService = new FruitShopService(operationHandlers);
        shopService.processFile(transactions);

        ReportContentGenerator reportContentGenerator = new ReportContentGenerator();
        String resultingReport = reportContentGenerator.generateReportContent();

        ReportWriter reportWriter = new ReportWriter();
        reportWriter.writeReport(resultingReport, OUTPUT_REPORT);
    }
}
