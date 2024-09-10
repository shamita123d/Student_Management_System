package com.cse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setLayout(new GridLayout(4, 1));

        JButton manageUsersButton = new JButton("Manage Users");
        JButton manageStudentsButton = new JButton("Manage Students");
        JButton logoutButton = new JButton("Logout");
        JButton aboutButton = new JButton("About");

        add(new JLabel("Admin Dashboard", SwingConstants.CENTER));
        add(manageUsersButton);
        add(manageStudentsButton);
        add(logoutButton);
        add(aboutButton);

        manageUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to manage users
                JOptionPane.showMessageDialog(null, "Manage Users feature is not implemented yet.");
            }
        });

        manageStudentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to manage students
                JOptionPane.showMessageDialog(null, "Manage Students feature is not implemented yet.");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new About();
            }
        });

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}
