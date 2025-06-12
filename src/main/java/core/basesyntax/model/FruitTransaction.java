package core.basesyntax.model;

public class FruitTransaction {
    private String operation;
    private String fruit;
    private int quantity;

    public FruitTransaction(String operation, String fruit, int quantity) {
        this.operation = operation;
        this.fruit = fruit;
        this.quantity = quantity;
    }

    public String getOperation() {
        return operation;
    }

    public String getFruit() {
        return fruit;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FruitTransaction that = (FruitTransaction) o;

        if (quantity != that.quantity) {
            return false;
        }
        if (!operation.equals(that.operation)) {
            return false;
        }
        return fruit.equals(that.fruit);
    }

    @Override
    public int hashCode() {
        int result = operation.hashCode();
        result = 31 * result + fruit.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "FruitTransaction{"
                + "operation='" + operation
                + '\'' + ", fruit='" + fruit
                + '\'' + ", quantity=" + quantity
                + '}';
    }

    public enum OperationType {
        BALANCE("b"),
        SUPPLY("s"),
        PURCHASE("p"),
        RETURN("r");

        private final String code;

        OperationType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static OperationType fromCode(String code) {
            for (OperationType operation : values()) {
                if (operation.getCode().equals(code)) {
                    return operation;
                }
            }
            throw new IllegalArgumentException("Invalid operation code: " + code);
        }
    }
}
