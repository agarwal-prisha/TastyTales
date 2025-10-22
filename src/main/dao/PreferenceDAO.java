package main.dao;

import main.db.DatabaseConnection;
import main.model.Preference;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PreferenceDAO {

    public boolean savePreferences(Preference pref) {
        // if preferences already exist for user, update; else insert
        String check = "SELECT pref_id FROM preferences WHERE user_id = ?";
        String insert = "INSERT INTO preferences(user_id, diet, allergens, cuisine) VALUES(?, ?, ?, ?)";
        String update = "UPDATE preferences SET diet=?, allergens=?, cuisine=? WHERE user_id=?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(check)) {
                ps.setInt(1, pref.getUserId());
                var rs = ps.executeQuery();
                if (rs.next()) {
                    try (PreparedStatement ups = conn.prepareStatement(update)) {
                        ups.setString(1, pref.getDiet());
                        ups.setString(2, pref.getAllergens());
                        ups.setString(3, pref.getCuisines());
                        ups.setInt(4, pref.getUserId());
                        ups.executeUpdate();
                        return true;
                    }
                } else {
                    try (PreparedStatement ins = conn.prepareStatement(insert)) {
                        ins.setInt(1, pref.getUserId());
                        ins.setString(2, pref.getDiet());
                        ins.setString(3, pref.getAllergens());
                        ins.setString(4, pref.getCuisines());
                        ins.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Preference save error: " + e.getMessage());
            return false;
        }
    }
}

