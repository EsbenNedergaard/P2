package Warehouse;

import Exceptions.IllegalProductPositionException;
import Exceptions.IllegalRackDimensionException;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static Warehouse.GUIWarehouse.TILE_SIZE;

public class RackSome extends Rectangle {

    private boolean isVertical;
    private String name;
    private int xCoordinate, yCoordinate;
    private List<Product> productList = new ArrayList<>();

    public RackSome(String name, int width, int height, int x, int y) {
        // TODO: A rack can only hold one row or one column of products
        // TODO: so, if width > 1 when height = 1 or if height > 1 then width = 1

        checkRackDimensions(width * TILE_SIZE, height * TILE_SIZE);

        this.name = name;
        this.xCoordinate = x;
        this.yCoordinate = y;

        setWidth(width * TILE_SIZE);
        setHeight(height * TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);
        setFill(Color.valueOf("yellow"));

    }

    private void checkRackDimensions(int width, int height) {
        if (width >= TILE_SIZE && height == TILE_SIZE) {
            this.isVertical = false;
        }
        else if (width == TILE_SIZE && height >= TILE_SIZE) {
            this.isVertical = true;
        }
        else {
            throw new IllegalRackDimensionException();
        }
    }

    public void addProduct(Product product) {
        // Elements of list decides which coordinates the product should have

        int productXCoordinate = (int) product.getTranslateX() / TILE_SIZE;
        int productYCoordinate = (int) product.getTranslateY() / TILE_SIZE;

        if (this.isVertical) {
            // If x and y is not set from Product constructor
            sortListByProductYCoordinate();

            if (productXCoordinate == -1 &&
                productYCoordinate == -1) {

                boolean emptySpotFound = false;
                // The yCoordinate for where we are looking a empty spot
                int emptySpotY = 0;

                while(!emptySpotFound) {

                    if(productList.size() > 0) {
                        for(Product item : productList) {
                            // Look if the selected yCoordinate in the rack contains a product
                            if (item.getTranslateY() / TILE_SIZE - this.yCoordinate == emptySpotY)
                                emptySpotY++;
                            else
                                emptySpotFound = true;
                        }

                    } else {
                        // The list was empty
                        emptySpotFound = true;
                    }
                }

                product.setTranslateX(TILE_SIZE * this.xCoordinate);
                product.setTranslateY((emptySpotY * TILE_SIZE) + (TILE_SIZE * this.yCoordinate));

                // Adding the product to the productList
                productList.add(product);

            } else {
                // Try to put a product into a certain place in the rack
                // Only if spot is empty
                if(inRack(productXCoordinate, productYCoordinate)) {

                    for(Product item : productList) {
                        if(item.getTranslateX() / TILE_SIZE == productXCoordinate &&
                           item.getTranslateY() / TILE_SIZE == productYCoordinate) {
                            // These coordinates contains a product
                            throw new IllegalProductPositionException();
                        }
                    }

                    // The spot was empty, so add the product
                    productList.add(product);

                } else {
                    // Product was out of rack bound
                    throw new IllegalProductPositionException();
                }
            }
        }

    }

    public Product getProduct(int id) {
        for(Product item : productList) {
            if(item.getById() == id)
                return item;
        }

        // TODO: skal caste en exception om at produktet ikke findes!!!!!
        return new Product("NULL", 0, 0, 0);
    }

    // Return the hole product list
    public List<Product> getProductList() {
        return this.productList;
    }


    private void sortListByProductYCoordinate() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getTranslateY() - o2.getTranslateY());
            }
        });
    }

    private boolean inRack(int x, int y) {
        boolean xInRack = x >= this.xCoordinate && x < this.xCoordinate + this.getWidth() / TILE_SIZE;
        boolean yInRack = y >= this.yCoordinate && y < this.yCoordinate + this.getHeight() / TILE_SIZE;

        if(xInRack && yInRack)
            return true;
        return false;
    }

    public boolean containsProduct(int x, int y) {
        for(Product product : productList) {
            if(product.getTranslateX() / TILE_SIZE == x &&
               product.getTranslateY() / TILE_SIZE == y)
                return true;
        }
        return false;
    }

}
