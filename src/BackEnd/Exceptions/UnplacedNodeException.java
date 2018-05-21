package BackEnd.Exceptions;

public class UnplacedNodeException extends RuntimeException {
    private String errorMsg;

    public UnplacedNodeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public UnplacedNodeException() {
        this.errorMsg = "You tried to get time from a node that is not in a time layer";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
