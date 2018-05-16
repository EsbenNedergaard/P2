package WarehouseSimulation.GraphicalObjects.Colors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PickerColors {

    private List<Colors> colorsList;
    private int indexOfUnusedColor;

    public PickerColors() {
        this.colorsList = new ArrayList<>();
        this.indexOfUnusedColor = 0;
        addColorsToList();
    }

    private void addColorsToList() {
        Collections.addAll(colorsList, Colors.values());
    }

    public String getUnusedColor() {
        String unusedColor;
        if (!nextColorIsAvailable())
            resetIndexOfUnusedColor();
        unusedColor = colorsList.get(indexOfUnusedColor).getColor();
        indexOfUnusedColor++;

        return unusedColor;
    }

    private boolean nextColorIsAvailable() {
        return indexOfUnusedColor < colorsList.size();
    }

    public void resetIndexOfUnusedColor() {
        indexOfUnusedColor = 0;
    }

}
