package main.ui;

import javax.swing.*;
import java.awt.*;
import main.dao.UserDAO;
import main.model.User;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private JFrame parentFrame;

    public LoginPanel(JFrame frame) {
        this.parentFrame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
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

        gbc.gridx=0; gbc.gridy++;
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        add(loginButton, gbc);
        gbc.gridx=1;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill both fields.");
                return;
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Welcome " + username + "!");
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new ProfileSetupFrame(parentFrame, user.getId()));
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        });

        registerButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new RegisterPanel(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}
