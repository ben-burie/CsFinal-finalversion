package Project;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class BankPortalGUI extends JFrame {
    private JLabel titleLabel;
    private JButton loginButton;
    private JButton createAccountButton;

    public BankPortalGUI() {

    	//Creating the GUI frame
        setTitle("Bank Portal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //adding title label
        titleLabel = new JLabel("<html>Bank Portal<br><br></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        add(titleLabel, BorderLayout.NORTH);

        //adding the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");
        buttonPanel.add(loginButton);
        buttonPanel.add(createAccountButton);
        add(buttonPanel, BorderLayout.CENTER);

        //login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginGUI(); //switches to login screen
            }
        });

        //create account button
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CreateAccountGUI(); //switches to create account screen
            }
        });
        
        setVisible(true);
    }

    //tells program to run this screen first
    public static void main(String[] args) { //main method
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankPortalGUI();
            }
        });
    }
}