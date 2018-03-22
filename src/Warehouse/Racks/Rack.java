package Warehouse.Racks;

import Exceptions.FullRackException;
import Warehouse.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public int getMaxAmtProductsInRack() {
        return this.maxAmtProductsInRack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxAmtProductsInRack) + Objects.hash(productList);
    }

    @Override
    public boolean equals(Object that) {
        if(this == that) return true;
        if(that == null || this.getClass() != that.getClass()) return false;

        Rack thatRack = (Rack) that;

        return  thatRack.getProductList().equals(this.productList) && thatRack.maxAmtProductsInRack == this.getMaxAmtProductsInRack();
    }

}