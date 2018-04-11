package Warehouse.PickList;

import Warehouse.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomPickList3 implements PickList {

    private List<Product> productList = new ArrayList<>();

    public CustomPickList3() {
        generateProductsToList();
    }

    private void generateProductsToList() {
        productList.add(new Product(632));
        productList.add(new Product(633));
        productList.add(new Product(635));
        productList.add(new Product(1433));
        productList.add(new Product(1434));
        productList.add(new Product(490));
        productList.add(new Product(491));
        productList.add(new Product(75));
        productList.add(new Product(76));
        productList.add(new Product(540));
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


