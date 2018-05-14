package Warehouse;


public class Shelf {
    private Product product;

    public Shelf(Product product) {
        this.product = product;
    }

    public Shelf() {
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public boolean containsProduct() {
        return this.product != null;
    }
}
