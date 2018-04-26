package WarehouseSimulation.GraphicalObjects.Interaction.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class Table<E> {

    private ObservableList<E> data;
    private TableView<E> table = new TableView<>();

    public Table() {
        this.data = null;
        createTable();
    }

    private void createTable() {
        List<TableColumn> columnList = List.of(
                createColumn("#", 40,  "number"),
                createColumn("Product ID", 320, "productIDSet"),
                createColumn("View", 80,  "highlightButton")
        );

        setColumnsSortable(columnList, false);
        addColumnsToTable(columnList);
        table.setEditable(false);

    }

    private TableColumn createColumn(String name, double width, String factoryValue) {
        TableColumn column = new TableColumn();
        setColumnName(column, name);
        setColumnWidth(column, width);
        setPropertyValueFactory(column, factoryValue);

        return column;
    }

    private void setColumnName(TableColumn column, String name) {
        column.setText(name);
    }

    private void setColumnWidth(TableColumn column, double width) {
        column.setMinWidth(width);
        column.setMaxWidth(width);
    }

    private void setPropertyValueFactory(TableColumn column, String factoryValue) {
        column.setCellValueFactory(new PropertyValueFactory<E, String>(factoryValue));
    }

    private void setColumnsSortable(List<TableColumn> columnList, boolean sortable) {
        for(TableColumn column : columnList)
            column.setSortable(sortable);
    }

    private void addColumnsToTable(List<TableColumn> columnList) {
        for(TableColumn column : columnList) {
            table.getColumns().addAll(column);
        }
    }

    public void add(E productIDSet) {
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

    public TableView<E> getTable() {
        return table;
    }
}
