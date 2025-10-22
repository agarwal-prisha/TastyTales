package main.model;

public class Rating {
    private int userId;
    private int recipeId;
    private int rating;
    public Rating(int userId, int recipeId, int rating) {
        this.userId = userId; this.recipeId = recipeId; this.rating = rating;
    }
    public int getUserId() { return userId; }
    public int getRecipeId() { return recipeId; }
    public int getRating() { return rating; }
}
