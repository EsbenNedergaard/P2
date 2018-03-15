package some;

import GraphicalUserInterface.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class someTest {



    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();

        productList.add(new Product("Tobias", 1, 1, 1));
        productList.add(new Product("Tobias", 2, 2, 2));
        productList.add(new Product("Tobias", 3, 3, 4));
        productList.add(new Product("Tobias", 4, 1, 3));

        for(Product product : productList) {
            System.out.println(product.getTranslateY());
        }

        System.out.println();

        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getTranslateY() - o2.getTranslateY());
            }
        });

        for(Product product : productList) {
            System.out.println(product.getTranslateY());
        }


    }

}
