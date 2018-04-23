package GraphicalWarehouse.GraphicalObjects.TableViewData;

import javafx.beans.property.SimpleStringProperty;

/* THIS CLASS IS A DATA MODEL FOR THE GRAPHICAL OBJECT TABLE VIEW.
 * THIS CLASS ONLY FORMS THE DATA STRUCTURE FOR PUTTING DATA INTO
 * A TABLE VIEW*/

public class ProductIDSet {

    private final SimpleStringProperty productIDSet;
    private final SimpleStringProperty number;

    public ProductIDSet(String productIDSet, int number) {
        this.productIDSet = new SimpleStringProperty(productIDSet);
        this.number = new SimpleStringProperty("" + number);
    }

    public String getProductIDSet() {
        return productIDSet.get();
    }

    public SimpleStringProperty productIDSetProperty() {
        return productIDSet;
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }
}
