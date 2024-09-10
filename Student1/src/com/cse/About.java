
package com.cse;
import javax.swing.*;

public class About extends JFrame {
    public About() {
        setTitle("About");
        JLabel aboutLabel = new JLabel("Student Management System v1.0. Designed by Shamita Deogade", SwingConstants.CENTER);
        add(aboutLabel);
        setSize(400, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
