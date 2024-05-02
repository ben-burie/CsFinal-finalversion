package Project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Project.Backend.AuthenticationService;

class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    
    AuthenticationService authService = new AuthenticationService();

    public LoginGUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel loginPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JLabel loginLabel = new JLabel("Login Interface");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(loginLabel);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        loginPanel.add(inputPanel);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                try {
					if (performLogin(username, password) == true) {
					    JOptionPane.showMessageDialog(null, "Login successful!");
					    dispose();
					    new ProfilePageGUI(username);
					} else {
					    JOptionPane.showMessageDialog(null, "Invalid username or password!");
					}
				} catch (HeadlessException | FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        });
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    
    public boolean performLogin(String username, String password) {
        if (authService.login(username, password)) {
            return true;
        } else {
            return false;
        }
    }
    
}