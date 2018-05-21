package WarehouseSimulation.Exception;

public class IllegalMoveSpeedArgument extends RuntimeException {
    private String errorMsg;

    public IllegalMoveSpeedArgument(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public IllegalMoveSpeedArgument() {
        this.errorMsg = "Move speed can't be less than 0";
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}