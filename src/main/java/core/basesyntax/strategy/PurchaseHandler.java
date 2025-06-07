package core.basesyntax.strategy;

import core.basesyntax.db.Storage;

public class PurchaseHandler implements OperationStrategy {
    @Override
    public void execute(String fruit, int quantity) {
        if (!isValid(fruit, quantity)) {
            throw new RuntimeException("Invalid input: " + quantity);
        }

        int current = Storage.inventory.getOrDefault(fruit, 0);
        if (current < quantity) {
            throw new RuntimeException("Not enough product in storage for: " + fruit);
        }

        Storage.inventory.put(fruit, current - quantity);
    }

    @Override
    public boolean isValid(String fruit, int quantity) {
        return quantity > 0;
    }
}
