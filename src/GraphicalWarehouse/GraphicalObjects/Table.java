package GraphicalWarehouse.GraphicalObjects;

import GraphicalWarehouse.GraphicalObjects.TableViewData.ProductIDSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {

    private ObservableList<ProductIDSet> data;
    private TableView<ProductIDSet> table = new TableView<>();

    public Table() {
        this.data = null;
        createTable();
    }

    public Table(ObservableList<ProductIDSet> data) {
        this.data = FXCollections.observableArrayList(data);
        createTable();
    }

    // Creates all elements, columns and rows in the table view
    private void createTable() {
        // It is not legal to edit the table after content is added
        table.setEditable(true);

        // Set Table Columns
        TableColumn productIDColumn = new TableColumn("Product ID");
        TableColumn routeNumberColumn = new TableColumn("#");

        // Style for columns
        productIDColumn.setMinWidth(400);
        routeNumberColumn.setMaxWidth(40);

        productIDColumn.setCellValueFactory(
                new PropertyValueFactory<ProductIDSet, String>("productIDSet"));

        routeNumberColumn.setCellValueFactory(
                new PropertyValueFactory<ProductIDSet, String>("number"));

        if(data != null)
            table.setItems(data);

        table.getColumns().addAll(routeNumberColumn, productIDColumn);
    }

    public TableView<ProductIDSet> getTable() {
        return table;
    }

    public void add(ProductIDSet productIDSet) {
        if(data == null)
            data = FXCollections.observableArrayList();

        data.addAll(productIDSet);
        table.setItems(data);
    }

    public void clear() {
        if(data != null) {
            data.clear();
            table.setItems(data);
        }
    }
}
