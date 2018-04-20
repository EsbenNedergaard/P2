package GraphicalWarehouse.GraphicalObjects;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/* THIS IS A CUSTOM TABLE VIEW CLASS,
   WHICH REPRESENTS A TABLE VIEW */
public class TableGraphics extends GridPane {

    private int rowCount = 0;

    public TableGraphics() {
        // Add style class to this table
        getStyleClass().add("t-table");
        // This represents the table header
        newRow();
        Label pos = new Label("#");
        Label id = new Label("Product IDs");

        pos.getStyleClass().add("t-table-th");  add(pos, 1, rowCount);
        id.getStyleClass().add("t-table-th-2"); add(id, 2, rowCount);
    }

    public void add(String str) {
        newRow();
        int val = rowCount - 1;
        Label pos = new Label("" + val);
        Label id = new Label(str);

        pos.getStyleClass().add("t-table-td");  add(pos, 1, rowCount);
        id.getStyleClass().add("t-table-td"); add(id, 2, rowCount);
    }

    // Call this function every time a new row should be added
    private void newRow() {
        rowCount++;
    }
}
