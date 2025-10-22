package main.dao;

import main.db.DatabaseConnection;
import main.model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RatingDAO {
    public boolean saveRating(Rating r) {
        String query = "INSERT INTO ratings(user_id, recipe_id, rating) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, r.getUserId());
            ps.setInt(2, r.getRecipeId());
            ps.setInt(3, r.getRating());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error saving rating: " + e.getMessage());
            return false;
        }
    }
}

