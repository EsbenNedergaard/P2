package BackEnd.Exceptions;

public class NodeLayerDoesNotExistException extends RuntimeException {
    private String errorMsg;

    public NodeLayerDoesNotExistException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public NodeLayerDoesNotExistException() {
        this.errorMsg = "There is no NodeLayer for the given time";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
