package main.ui;

import javax.swing.*;
import java.awt.*;
import main.dao.UserDAO;

public class RegisterPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JFrame parentFrame;

    public RegisterPanel(JFrame frame) {
        this.parentFrame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Register", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        add(title, gbc);

        gbc.gridwidth=1; gbc.gridy++;
        add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(15);
        gbc.gridx=1;
        add(usernameField, gbc);

        gbc.gridx=0; gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx=1;
        add(passwordField, gbc);

        gbc.gridx=0; gbc.gridy++; gbc.gridwidth=2;
        registerButton = new JButton("Register");
        add(registerButton, gbc);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill both fields.");
                return;
            }
            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.registerUser(username, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "Registration successful. Login now.");
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new LoginPanel(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed (username may exist).");
            }
        });
    }
}
