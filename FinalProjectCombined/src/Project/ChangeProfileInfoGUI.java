package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Project.Backend.AuthenticationService;
import Project.CreateAccountGUI;

class ChangeProfileInfoGUI extends JFrame {
    private JTextField newUsernameField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    
    AuthenticationService authService = new AuthenticationService();

    public ChangeProfileInfoGUI(String username) {
        setTitle("Edit Profile");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel changeProfilePanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel changeProfileLabel = new JLabel("Edit Profile");
        changeProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        changeProfilePanel.add(changeProfileLabel);

        JPanel inputPanel2 = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel2.add(new JLabel("New Password:"));
        newPasswordField = new JPasswordField();
        inputPanel2.add(newPasswordField);
        inputPanel2.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        inputPanel2.add(confirmPasswordField);
        changeProfilePanel.add(inputPanel2);
        
        
        

        JButton saveChangesButton = new JButton("Save Changes");
        saveChangesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	char[] newPasswordChars = newPasswordField.getPassword();
            	String newPassword = new String(newPasswordChars);
            	char[] confirmPasswordChars = confirmPasswordField.getPassword();
            	String confirmPassword = new String(confirmPasswordChars);

            	
            	if (newPassword.equals(confirmPassword)) {
                	try {
    					changeInfo(username, newPassword);
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
               		JOptionPane.showMessageDialog(null, "Changes saved successfully!");
               	}
               	else {
               		JOptionPane.showMessageDialog(null, "Passwords do not match!");
               	}
              
                dispose();
                try {
					new ProfilePageGUI(username);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        });
        changeProfilePanel.add(saveChangesButton);

        add(changeProfilePanel, BorderLayout.CENTER);

        setVisible(true);
    }
    
    
    public static void changeInfo(String username, String newPassword) throws IOException {
    	
    	File file = new File("accountDatabase.csv");
		Scanner reader = new Scanner(file);
		StringBuilder content = new StringBuilder();
		
		
		boolean isFound = false;
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(",");
			
			if (items[0].equals(username)) {
				isFound = true;
			}
			else {
	            content.append(line).append("\n");
	        }
		}
		if (isFound == true) {		
			AuthenticationService authenticationService = new AuthenticationService();
			authenticationService.registerOrUpdateUser(username, newPassword);
        } 
		else {
            System.out.println("Target number not found in the file.");
        }
    }
    
}