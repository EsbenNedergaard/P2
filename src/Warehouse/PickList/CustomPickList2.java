package Warehouse.PickList;

import Warehouse.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomPickList2 implements PickList {

    private List<Product> productList = new ArrayList<>();

    public CustomPickList2() {
        generateProductsToList();
    }

    private void generateProductsToList() {
        productList.add(new Product(432));
        productList.add(new Product(433));
        productList.add(new Product(435));
        productList.add(new Product(2100));
        productList.add(new Product(2101));
        productList.add(new Product(2150));
        productList.add(new Product(745));
        productList.add(new Product(746));
        productList.add(new Product(981));
        productList.add(new Product(999));
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

