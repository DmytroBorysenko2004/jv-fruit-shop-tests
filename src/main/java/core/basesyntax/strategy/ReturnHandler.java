package core.basesyntax.strategy;

import core.basesyntax.db.Storage;

public class ReturnHandler implements OperationStrategy {
    @Override
    public void execute(String fruit, int quantity) {
        if (!isValid(fruit, quantity)) {
            throw new RuntimeException("Invalid input: " + quantity);
        }
        Storage.inventory.put(fruit, Storage.inventory.getOrDefault(fruit, 0) + quantity);
    }

    @Override
    public boolean isValid(String fruit, int quantity) {
        return quantity > 0;
    }
}
