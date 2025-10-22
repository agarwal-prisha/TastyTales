package main.model;

public class Preference {
    private int userId;
    private String diet;
    private String allergens;
    private String cuisines;

    public Preference(int userId, String diet, String allergens, String cuisines) {
        this.userId = userId;
        this.diet = diet;
        this.allergens = allergens;
        this.cuisines = cuisines;
    }
    public int getUserId() { return userId; }
    public String getDiet() { return diet; }
    public String getAllergens() { return allergens; }
    public String getCuisines() { return cuisines; }
}
