package Exceptions.Warehouse;

public class ProductNotInShelfException extends RuntimeException {
    private String errorMsg;

    public ProductNotInShelfException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ProductNotInShelfException() {
        this.errorMsg = "Product not in rack";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
