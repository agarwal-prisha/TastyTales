package main.model;

public class Recipe {
    private int id;
    private String name;
    private String cuisine;
    private String diet;
    private String cookingTime;
    private String ingredients;
    private String allergens;
    private String recipeText;

    public Recipe(int id, String name, String cuisine, String diet,
                  String cookingTime, String ingredients, String allergens, String recipeText) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.diet = diet;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.recipeText = recipeText;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCuisine() { return cuisine; }
    public String getDiet() { return diet; }
    public String getCookingTime() { return cookingTime; }
    public String getIngredients() { return ingredients; }
    public String getAllergens() { return allergens; }
    public String getRecipeText() { return recipeText; }
}
