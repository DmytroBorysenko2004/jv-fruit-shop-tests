package core.basesyntax.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.BalanceHandler;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.PurchaseHandler;
import core.basesyntax.strategy.ReturnHandler;
import core.basesyntax.strategy.SupplyHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FruitShopServiceTest {

    private FruitShopService service;
    private Map<FruitTransaction.OperationType, OperationStrategy> handlers;

    @BeforeEach
    void setUp() {
        Storage.inventory.clear();
        handlers = new HashMap<>();
        handlers.put(FruitTransaction.OperationType.BALANCE, new BalanceHandler());
        handlers.put(FruitTransaction.OperationType.SUPPLY, new SupplyHandler());
        handlers.put(FruitTransaction.OperationType.PURCHASE, new PurchaseHandler());
        handlers.put(FruitTransaction.OperationType.RETURN, new ReturnHandler());

        service = new FruitShopService(handlers);
    }

    @Test
    void processFile_executesAllOperationsSuccessfully() {
        List<FruitTransaction> transactions = List.of(
                new FruitTransaction("b", "apple", 20),
                new FruitTransaction("s", "apple", 10),
                new FruitTransaction("p", "apple", 15),
                new FruitTransaction("r", "apple", 5)
        );

        service.processFile(transactions);
        assertEquals(20, Storage.inventory.get("apple"));
    }

    @Test
    void processFile_throwsException_whenOperationStrategyIsNull() {
        FruitTransaction tx = new FruitTransaction("b", "apple", 10);
        handlers.remove(FruitTransaction.OperationType.BALANCE);

        List<FruitTransaction> transactions = List.of(tx);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                service.processFile(transactions));
        assertTrue(exception.getMessage().contains("Invalid or unknown operation"));
    }

    @Test
    void dataConverter_convertDataToTransactions_emptyList() {
        List<String> input = List.of();
        List<FruitTransaction> result = DataConverter.convertDataToTransactions(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void fruitTransaction_toString_containsFields() {
        FruitTransaction transaction = new FruitTransaction("b", "apple", 10);
        String str = transaction.toString();
        assertTrue(str.contains("b"));
        assertTrue(str.contains("apple"));
        assertTrue(str.contains("10"));
    }
}
