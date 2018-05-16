package WarehouseSimulation.GraphicalObjects.Interaction;

public enum ButtonType {

    ADD("Add"), LAUNCH("Launch"), RESET("Reset"), RELAUNCH("Re-Launch"),
    SPEEDX1("x1"), SPEEDX2("x2"), SPEEDX5("x5"), RANDOMIZE("Randomize");

    private String text;

    ButtonType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
