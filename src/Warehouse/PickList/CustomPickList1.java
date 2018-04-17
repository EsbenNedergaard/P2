package Warehouse.PickList;

import Warehouse.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomPickList1 implements PickList {

    private List<Product> productList = new ArrayList<>();

    public CustomPickList1() {
        generateProductsToList();
    }

    private void generateProductsToList() {
        productList.add(new Product(1));
        productList.add(new Product(2));
        productList.add(new Product(3));
        productList.add(new Product(10));
        productList.add(new Product(11));
        productList.add(new Product(12));
        productList.add(new Product(201));
        productList.add(new Product(202));
        productList.add(new Product(456));
        productList.add(new Product(458));
    }

    @Override
    public List<Product> getPickList() {
        return this.productList;
    }

    @Override
    public int getAmountOfProducts() {
        return this.productList.size();
    }
}
