package Exceptions;

public class FullRackException extends RuntimeException {
    private String errorMsg;

    public FullRackException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public FullRackException() {
        this.errorMsg = "RackRow is full";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}