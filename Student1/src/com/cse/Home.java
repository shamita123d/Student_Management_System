package com.cse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    public Home() {
        setTitle("Student Management System - Home");
        setLayout(new BorderLayout());

        // Create the heading label
        JLabel headingLabel = new JLabel("Welcome to the Student Management System!", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headingLabel, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5)); // 6 rows, 1 column, with gaps

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        JButton registerButton = new JButton("Register");
        JButton adminButton = new JButton("Admin Login");
        JButton aboutButton = new JButton("About");

        // Adjust button sizes
        loginButton.setPreferredSize(new Dimension(150, 30));
        signupButton.setPreferredSize(new Dimension(150, 30));
        registerButton.setPreferredSize(new Dimension(150, 30));
        adminButton.setPreferredSize(new Dimension(150, 30));
        aboutButton.setPreferredSize(new Dimension(150, 30));

        // Add buttons to panel
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(adminButton);
        buttonPanel.add(aboutButton);

        // Add panel to frame
        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Signup();
                dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentRegistration();
                dispose();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminLogin();
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new About();
            }
        });

        // Set frame properties
        setSize(300, 400); // Adjust size as needed
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Home();
            }
        });
    }
}

