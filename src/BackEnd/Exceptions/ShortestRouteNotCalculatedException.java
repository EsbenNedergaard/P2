package BackEnd.Exceptions;

public class ShortestRouteNotCalculatedException extends RuntimeException {
    private String errorMsg;

    public ShortestRouteNotCalculatedException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
