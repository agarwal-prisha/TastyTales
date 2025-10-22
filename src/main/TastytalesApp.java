package main;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import main.ui.LoginPanel;

public class TastytalesApp {
    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.background", new java.awt.Color(187, 255, 200));
            UIManager.put("Button.arc", 12);
            UIManager.put("TextComponent.arc", 12);
            UIManager.put("Component.focusWidth", 1);
        } catch(Exception e) {
            System.err.println("FlatLaf failed, using default look.");
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("TastyTales 🍽️");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 700);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new java.awt.BorderLayout());

            LoginPanel loginPanel = new LoginPanel(frame);
            frame.add(loginPanel, java.awt.BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
