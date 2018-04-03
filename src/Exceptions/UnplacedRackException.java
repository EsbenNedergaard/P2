package Exceptions;

public class UnplacedRackException extends RuntimeException {
    private String errorMsg;

    public UnplacedRackException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public UnplacedRackException() {
        this.errorMsg = "You tried to get an unplaced rack";
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}
