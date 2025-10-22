package main.dao;

import main.model.Recipe;
import main.db.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class RecipeDAO {

    // ✅ Get recipes filtered by user preferences (supports multiple allergens)
    public List<Recipe> getRecipesByUser(int userId) {
        List<Recipe> recipes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection()) {

            // 1️⃣ Get user's preferences
            String prefQuery = "SELECT cuisine, diet, allergens FROM preferences WHERE user_id = ?";
            PreparedStatement prefStmt = conn.prepareStatement(prefQuery);
            prefStmt.setInt(1, userId);
            ResultSet prefRs = prefStmt.executeQuery();

            if (!prefRs.next()) {
                System.out.println("⚠️ No preferences found for user " + userId);
                return recipes;
            }

            String cuisine = prefRs.getString("cuisine");
            String diet = prefRs.getString("diet");
            String allergens = prefRs.getString("allergens");

            // 2️⃣ Build base query
            StringBuilder query = new StringBuilder("SELECT * FROM recipes WHERE 1=1");
            List<Object> params = new ArrayList<>();

            if (cuisine != null && !cuisine.isEmpty()) {
                query.append(" AND cuisine = ?");
                params.add(cuisine);
            }

            if (diet != null && !diet.isEmpty()) {
                query.append(" AND diet = ?");
                params.add(diet);
            }

            // 3️⃣ Handle multiple allergens
            if (allergens != null && !allergens.isEmpty()) {
                String[] allergenList = allergens.split(",");
                for (String allergen : allergenList) {
                    allergen = allergen.trim();
                    if (!allergen.isEmpty()) {
                        query.append(" AND (allergens NOT LIKE ? OR allergens IS NULL)");
                        params.add("%" + allergen + "%");
                    }
                }
            }

            System.out.println("🔍 Final SQL Query: " + query);

            // 4️⃣ Execute
            PreparedStatement stmt = conn.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            // 5️⃣ Map results
            while (rs.next()) {
                Recipe recipe = new Recipe(
                        rs.getInt("recipe_id"),
                        rs.getString("name"),
                        rs.getString("cuisine"),
                        rs.getString("diet"),
                        rs.getString("cooking_time"),
                        rs.getString("ingredients"),
                        rs.getString("allergens"),
                        rs.getString("recipe")
                );
                recipes.add(recipe);
            }

            if (recipes.isEmpty()) {
                System.out.println("❌ No recipes found matching preferences.");
            } else {
                System.out.println("✅ Found " + recipes.size() + " matching recipes.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipes;
    }
}
