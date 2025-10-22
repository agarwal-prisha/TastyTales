package main.ui;

import javax.swing.*;
import java.awt.*;
import main.dao.RecipeDAO;
import main.model.Recipe;
import java.util.List;

public class DashboardFrame extends JPanel {
    private int userId; // Store logged-in user ID

    public DashboardFrame(int userId) {
        this.userId = userId;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("🍽️ TastyTales Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        loadRecipes();
    }

    private void loadRecipes() {
        RecipeDAO recipeDAO = new RecipeDAO();
        List<Recipe> recipes = recipeDAO.getRecipesByUser(userId);

        JPanel recipesPanel = new JPanel();
        recipesPanel.setLayout(new GridLayout(0, 2, 10, 10)); // 2 per row
        recipesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (recipes.isEmpty()) {
            JLabel noResults = new JLabel("❌ No recipes found matching your preferences.", SwingConstants.CENTER);
            noResults.setFont(new Font("Arial", Font.ITALIC, 16));
            add(noResults, BorderLayout.CENTER);
        } else {
            for (Recipe r : recipes) {
                addRecipe(recipesPanel, r);
            }

            JScrollPane scrollPane = new JScrollPane(recipesPanel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            add(scrollPane, BorderLayout.CENTER);
        }
    }

    private void addRecipe(JPanel panel, Recipe r) {
        JPanel recipePanel = new JPanel(new BorderLayout());
        recipePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        recipePanel.setPreferredSize(new Dimension(250, 150));

        JLabel nameLabel = new JLabel(r.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recipePanel.add(nameLabel, BorderLayout.NORTH);

        JTextArea info = new JTextArea(
                "Cuisine: " + r.getCuisine() + "\n" +
                        "Diet: " + r.getDiet() + "\n" +
                        "Cooking Time: " + r.getCookingTime() + "\n\n" +
                        "Allergens: " + (r.getAllergens() == null || r.getAllergens().isEmpty() ? "None" : r.getAllergens())
        );
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setFont(new Font("Arial", Font.PLAIN, 13));

        recipePanel.add(info, BorderLayout.CENTER);
        panel.add(recipePanel);
    }
}
