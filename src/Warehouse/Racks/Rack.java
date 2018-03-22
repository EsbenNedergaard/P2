package Warehouse.Racks;

import Exceptions.FullRackException;
import Warehouse.Product;
import java.util.ArrayList;
import java.util.List;

public class Rack {
    private int maxAmtProductsInRack;
    private List<Product> productList = new ArrayList<>();

    Rack(int maxAmtProductsInRack) {
        this.maxAmtProductsInRack = maxAmtProductsInRack;
    }

    public void addProduct(Product e) {
        if (checkIfFull()) {
            throw new FullRackException("This rack is already full");
        }
        this.productList.add(e);
    }

    public boolean doesItContainProduct(Product product) {
        for (Product productElement : productList) {
            if(productElement.equals(product))
                return true;
        }
        return false;
    }

    public boolean checkIfFull(){
        return productList.size() >= maxAmtProductsInRack;
    }

    public List<Product> getProductList(){
        return this.productList;
    }

    public Product getProduct(int ListIndex) {
        return productList.get(ListIndex);
    }
}