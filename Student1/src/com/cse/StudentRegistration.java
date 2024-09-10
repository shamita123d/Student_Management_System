package com.cse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class StudentRegistration extends JFrame {
    private JTextField nameField, ageField, courseField, emailField;
    private DefaultTableModel tableModel;
    private JTable table;

    public StudentRegistration() {
        setTitle("Student Management");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(200, 200, 204)); 
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(20);
        nameField.setToolTipText("Enter Student's Name");
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Age:"), gbc);
        ageField = new JTextField(20);
        ageField.setToolTipText("Enter Student's Age");
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Course:"), gbc);
        courseField = new JTextField(20);
        courseField.setToolTipText("Enter Student's Course");
        gbc.gridx = 1;
        formPanel.add(courseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        emailField.setToolTipText("Enter Student's Email");
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Add Student");
        addButton.setBackground(new Color(34, 139, 34)); // Forest Green
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(addButton, gbc);

        gbc.gridy = 5;
        JButton showButton = new JButton("Show Records");
        showButton.setBackground(new Color(30, 144, 255)); // Dodger Blue
        showButton.setForeground(Color.WHITE);
        showButton.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(showButton, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Age", "Course", "Email"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(40); // Adjust row height
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setBackground(new Color(255, 255, 255)); // White background

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);

        table.setGridColor(new Color(211, 211, 211)); // Light Gray grid color
        table.setIntercellSpacing(new Dimension(5, 5)); // Adjust cell padding

        // Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cell.setBackground(row % 2 == 0 ? new Color(245, 245, 245) : Color.WHITE);
                return cell;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255)); // Light Blue background
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton updateButton = new JButton("Update Student");
        updateButton.setBackground(new Color(255, 165, 0)); // Orange
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setBackground(new Color(255, 69, 58)); // Red
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudentTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String course = courseField.getText();
        String email = emailField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "shami@123#");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO students (name, age, course, email) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, course);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage());
        }
    }

    private void updateStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int studentId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();
            String email = emailField.getText();

            if (name.isEmpty() || course.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields before updating.");
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "shami@123#");
                 PreparedStatement pstmt = conn.prepareStatement("UPDATE students SET name=?, age=?, course=?, email=? WHERE student_id=?")) {

                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, course);
                pstmt.setString(4, email);
                pstmt.setInt(5, studentId);
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student updated successfully!");
                    clearForm();
                    loadStudentTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: No student found with Student ID: " + studentId);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating student: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to update.");
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int studentId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "shami@123#");
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students WHERE student_id=?")) {
                pstmt.setInt(1, studentId);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                loadStudentTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
        }
    }

    private void loadStudentTable() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "shami@123#");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt("student_id")));
                row.add(rs.getString("name"));
                row.add(String.valueOf(rs.getInt("age")));
                row.add(rs.getString("course"));
                row.add(rs.getString("email"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage());
        }
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        new StudentRegistration();
    }
}
