package BackEnd.Exceptions;

public class RouteNotPossibleException extends RuntimeException {
    private String errorMsg;

    public RouteNotPossibleException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public RouteNotPossibleException() {
        this.errorMsg = "The route was not possible to create";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
