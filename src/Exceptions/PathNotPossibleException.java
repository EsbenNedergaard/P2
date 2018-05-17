package Exceptions;

public class PathNotPossibleException extends RuntimeException {
    private String errorMsg;

    public PathNotPossibleException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public PathNotPossibleException() {
        this.errorMsg = "The path was not possible to create";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
