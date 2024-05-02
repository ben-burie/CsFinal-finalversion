package Project;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

class CreateAccountGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    
    AuthenticationService authService = new AuthenticationService();

    public CreateAccountGUI() {
        setTitle("Create Account");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel createPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel createLabel = new JLabel("Create Account");
        createLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createPanel.add(createLabel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        inputPanel.add(confirmPasswordField);
        createPanel.add(inputPanel);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                char[] confirmPasswordChars = confirmPasswordField.getPassword();
                String password = new String(passwordChars);
                String confirmPassword = new String(confirmPasswordChars);
                
                boolean isUnique = true;
                
                try {
					if (checkUniqueUsername(username) == false) {
						isUnique = false;
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } 
                else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                } 
                else if (isUnique == false) {
                	JOptionPane.showMessageDialog(null, "Username already in use!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Account created successfully!");
                    dispose();
                    new LoginGUI();
                }
                
               createAccount(username, password);
                
            }
        });
        createPanel.add(createButton);

        add(createPanel, BorderLayout.CENTER);
        
        setVisible(true);
        
       
    }
    
    public void createAccount(String username, String password) {
        authService.registerOrUpdateUser(username, password);
    }
    
    public boolean checkUniqueUsername(String username) throws FileNotFoundException {
    	File file = new File("AccountDatabase.csv");
    	Scanner reader = new Scanner(file);
    	
    	boolean isUnique = true;
    	
    	while (reader.hasNextLine()) {
    		String line = reader.nextLine();
    		String[] items = line.split(",");
    		
    		System.out.println(items[0]);
    		
    		if (items[0].equals(username)) {
    			isUnique = false;
    		}
    	}
    	
    	return isUnique;
    }
    
}