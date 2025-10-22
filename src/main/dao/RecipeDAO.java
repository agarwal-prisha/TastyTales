package main.dao;

import main.model.Recipe;
import java.io.*;
import java.util.*;

public class RecipeDAO {

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        String csvFile = "src/main/recipes_10000_v2.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip header
            int idCounter = 1;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy, -1); // include empty values

                // columns: cuisine,diet,name,cooking_time,ingredients,allergens,recipes
                if (data.length >= 7) {
                    String cuisine = data[0].trim();
                    String diet = data[1].trim();
                    String name = data[2].trim();
                    String cookingTime = data[3].trim();
                    String ingredients = data[4].trim();
                    String allergens = data[5].trim();
                    String recipeText = data[6].trim();

                    recipes.add(new Recipe(
                            idCounter++,
                            name,
                            cuisine,
                            diet,
                            cookingTime,
                            ingredients,
                            allergens,
                            recipeText
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipes;
    }
}

