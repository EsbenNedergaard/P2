package BackEnd.Exceptions;

public class IsNotValidNodeTypeException extends RuntimeException {
    private String errorMsg;

    public IsNotValidNodeTypeException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public IsNotValidNodeTypeException() {
        this.errorMsg = "Not a valid NodeType";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
