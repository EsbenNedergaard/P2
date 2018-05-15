package Warehouse;


import java.util.List;

public class Shelf {
    private Product product;
    private List<Product> productList;

    public Shelf(Product product) {
        this.product = product;
    }

    public Shelf(List<Product> productList){
        this.productList = productList;
    }

    public Shelf() {
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    // works as getter and setter
    public List<Product> getProductList(){
        return this.productList;
    }

    public boolean containsProduct() {
        return this.product != null;
    }
}
