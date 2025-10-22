package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import main.dao.PreferenceDAO;
import main.model.Preference;

public class ProfileSetupFrame extends JPanel {
    private int userId;

    public ProfileSetupFrame(JFrame parentFrame, int userId) {
        this.userId = userId;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Profile Setup", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);
        add(Box.createVerticalStrut(10));

        // Allergens
        JLabel allergenLabel = new JLabel("Select Allergens:");
        add(allergenLabel);
        String[] allergens = {"Nuts","Milk","Gluten","Soy","Seafood"};
        ArrayList<JCheckBox> allergenChecks = new ArrayList<>();
        for(String a : allergens) { JCheckBox cb = new JCheckBox(a); allergenChecks.add(cb); add(cb); }

        add(Box.createVerticalStrut(10));

        // Cuisines
        JLabel cuisineLabel = new JLabel("Select Favorite Cuisines:");
        add(cuisineLabel);
        String[] cuisines = {"Indian","Mediterranean","American","Italian","French","Thai","Lebanese",
                "Japanese","Greek","Vietnamese","Spanish","Brazilian","Peruvian","Korean",
                "Mexican","Turkish","Moroccan","Ethiopian","Chinese"};
        ArrayList<JCheckBox> cuisineChecks = new ArrayList<>();
        for(String c : cuisines) { JCheckBox cb = new JCheckBox(c); cuisineChecks.add(cb); add(cb); }

        add(Box.createVerticalStrut(10));

        // Diet
        JLabel dietLabel = new JLabel("Select Diet Type:");
        add(dietLabel);
        JRadioButton veg = new JRadioButton("Vegetarian");
        JRadioButton nonVeg = new JRadioButton("Non-Vegetarian");
        JRadioButton vegan = new JRadioButton("Vegan");
        ButtonGroup dietGroup = new ButtonGroup();
        dietGroup.add(veg); dietGroup.add(nonVeg); dietGroup.add(vegan);
        add(veg); add(nonVeg); add(vegan);

        add(Box.createVerticalStrut(20));

        JButton submit = new JButton("Submit");
        add(submit);

        submit.addActionListener(e -> {
            List<String> selectedAllergens = new ArrayList<>();
            for(JCheckBox cb: allergenChecks) if(cb.isSelected()) selectedAllergens.add(cb.getText());

            List<String> selectedCuisines = new ArrayList<>();
            for(JCheckBox cb: cuisineChecks) if(cb.isSelected()) selectedCuisines.add(cb.getText());

            String diet = "Not selected";
            if(veg.isSelected()) diet="Vegetarian";
            else if(nonVeg.isSelected()) diet="Non-Vegetarian";
            else if(vegan.isSelected()) diet="Vegan";

            String allergenStr = String.join(",", selectedAllergens);
            String cuisineStr = String.join(",", selectedCuisines);

            Preference pref = new Preference(userId, diet, allergenStr, cuisineStr);
            PreferenceDAO prefDAO = new PreferenceDAO();
            boolean success = prefDAO.savePreferences(pref);

            if (success) JOptionPane.showMessageDialog(this, "Profile Saved Successfully!");
            else JOptionPane.showMessageDialog(this, "Error saving preferences.");

            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new DashboardFrame());
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}




