package Warehouse.Racks;

import Exceptions.FullRackException;
import Exceptions.UnplacedRackException;
import Geometry.Point2D;
import Warehouse.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rack {
    private int maxAmtProductsInRack;
    private List<Product> productList = new ArrayList<>();
    private Point2D rackPosition;
    private boolean isTopRackRow;

    Rack(int maxAmtProductsInRack, Point2D rackPosition) {
        this.maxAmtProductsInRack = maxAmtProductsInRack;
        this.rackPosition = rackPosition;
        //this.isTopRackRow = isTopRackRow;
    }

    public void addProduct(Product product) {
        if (checkIfFull()) {
            throw new FullRackException("This rack is already full");
        }
        product.setRack(this);
        this.productList.add(product);
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


    public int getXCoordinate() {
        if(rackPosition.equals(new Point2D(-1, -1)))
            throw new UnplacedRackException();

        return this.rackPosition.getX();
    }

    public int getYCoordinate() {
        if(rackPosition.equals(new Point2D(-1, -1)))
            throw new UnplacedRackException();

        return this.rackPosition.getY();
    }

    public Point2D getRackPosition() {
        if(rackPosition.equals(new Point2D(-1, -1)))
            throw new UnplacedRackException();

        return rackPosition;
    }



    @Override
    public int hashCode() {
        return Objects.hash(rackPosition);
    }

    @Override
    public boolean equals(Object that) {
        if(this == that) return true;
        if(that == null || this.getClass() != that.getClass()) return false;

        Rack thatRack = (Rack) that;
        return this.getRackPosition().equals(thatRack.getRackPosition());
    }

}