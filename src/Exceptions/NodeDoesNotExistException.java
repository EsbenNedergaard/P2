package Exceptions;

public class NodeDoesNotExistException extends RuntimeException {
    private String errorMsg;

    public NodeDoesNotExistException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public NodeDoesNotExistException() {
        this.errorMsg = "This node this not exist";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
