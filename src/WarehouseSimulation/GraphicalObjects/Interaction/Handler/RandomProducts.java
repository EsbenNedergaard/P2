package WarehouseSimulation.GraphicalObjects.Interaction.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomProducts {

    private Random rand;

    public RandomProducts() {
        rand = new Random();
    }

    public List<String> nextProductIDList(int maxIDs, int maxProducts) {
        List<String> randomProductIDList = new ArrayList<>();
        int amount = getRandomAmount(maxIDs);
        for (int i = 0; i < amount; i++)
            randomProductIDList.add(getRandomString(maxProducts));
        return randomProductIDList;
    }

    private int getRandomAmount(int max) {
        return rand.nextInt(max) + 1;
    }

    private String getRandomString(int max) {
        int randomNumber = rand.nextInt(max) + 1;
        return parseToString(randomNumber);
    }

    private String parseToString(int number) {
        return String.valueOf(number);
    }


}
