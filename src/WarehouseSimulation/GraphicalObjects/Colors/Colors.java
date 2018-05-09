package WarehouseSimulation.GraphicalObjects.Colors;

public enum Colors {
    BLUE("#037ef3"), RED("#f85a40"), CYAN("#30c39e"), OCEAN("#0a8ea0"),
    ORANGE("#f48924"), YELLOW("#ffc845"), GREY("#52565e"), PURPLE("#7d3f98"),
    PINK("#ff4f81"), LIME("#7fbb00");

    private String color;

    Colors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
