import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/*
 * Student Management System
 * Java Swing mini project based on the supplied SCD documentation.
 * Save this file as StudentManagementSystem.java
 */

// Abstraction: Person defines the common structure for people.
abstract class Person {
    private String name;
    private String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Encapsulation: private data is accessed through getters/setters.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String getDetails();
}

// Inheritance: Student inherits common properties from Person.
class Student extends Person {
    private String studentId;
    private String department;
    private String semester;
    private String phone;

    public Student(String studentId, String name, String email,
                   String department, String semester, String phone) {
        super(name, email);
        this.studentId = studentId;
        this.department = department;
        this.semester = semester;
        this.phone = phone;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Polymorphism: Student provides its own implementation.
    @Override
    public String getDetails() {
        return studentId + " - " + getName() + " - " + department;
    }
}

public class StudentManagementSystem extends JFrame {
    private final ArrayList<Student> students = new ArrayList<>();

    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField semesterField;
    private JTextField phoneField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(1050, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        add(createHeader(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(31, 55, 82));
        header.setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));

        JLabel title = new JLabel("STUDENT MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel subtitle = new JLabel("Add  |  View  |  Search  |  Update  |  Delete Student Records");
        subtitle.setForeground(new Color(220, 230, 240));
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setOpaque(false);
        textPanel.add(title);
        textPanel.add(subtitle);
        header.add(textPanel, BorderLayout.WEST);
        return header;
    }

    private JPanel createMainPanel() {
        JPanel main = new JPanel(new BorderLayout(12, 12));
        main.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        main.add(createFormPanel(), BorderLayout.WEST);
        main.add(createTablePanel(), BorderLayout.CENTER);
        return main;
    }

    private JPanel createFormPanel() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setPreferredSize(new Dimension(300, 0));
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Student Information"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        idField = new JTextField(18);
        nameField = new JTextField(18);
        emailField = new JTextField(18);
        departmentField = new JTextField(18);
        semesterField = new JTextField(18);
        phoneField = new JTextField(18);

        addFormRow(form, "Student ID:", idField, 0);
        addFormRow(form, "Full Name:", nameField, 1);
        addFormRow(form, "Email:", emailField, 2);
        addFormRow(form, "Department:", departmentField, 3);
        addFormRow(form, "Semester:", semesterField, 4);
        addFormRow(form, "Phone:", phoneField, 5);

        JButton addButton = createButton("Add Student", new Color(39, 125, 85));
        JButton updateButton = createButton("Update Student", new Color(48, 105, 160));
        JButton deleteButton = createButton("Delete Student", new Color(180, 65, 65));
        JButton clearButton = createButton("Clear Fields", new Color(100, 100, 100));
        JButton searchButton = createButton("Search by ID", new Color(125, 90, 155));
        JButton viewButton = createButton("View All Students", new Color(50, 120, 130));
        JButton exitButton = createButton("Exit", new Color(70, 70, 70));

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchStudent());
        viewButton.addActionListener(e -> refreshTable());
        exitButton.addActionListener(e -> exitApplication());

        JPanel buttons = new JPanel(new GridLayout(7, 1, 0, 7));
        buttons.add(addButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(searchButton);
        buttons.add(viewButton);
        buttons.add(clearButton);
        buttons.add(exitButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(14, 3, 3, 3);
        form.add(buttons, gbc);

        return form;
    }

    private void addFormRow(JPanel panel, String label, JTextField field, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 3, 7, 3);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Registered Students"));

        String[] columns = {"Student ID", "Name", "Email", "Department", "Semester", "Phone"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(25);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && studentTable.getSelectedRow() >= 0) {
                loadSelectedStudent();
            }
        });

        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBorder(BorderFactory.createEmptyBorder(5, 16, 10, 16));
        statusLabel = new JLabel("Ready. Total students: 0");
        statusLabel.setForeground(new Color(45, 75, 105));
        footer.add(statusLabel, BorderLayout.WEST);
        return footer;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private Student findStudent(String id) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    private boolean validateFields() {
        if (idField.getText().trim().isEmpty()
                || nameField.getText().trim().isEmpty()
                || emailField.getText().trim().isEmpty()
                || departmentField.getText().trim().isEmpty()
                || semesterField.getText().trim().isEmpty()
                || phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all student fields.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!emailField.getText().trim().contains("@")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid email address.",
                    "Invalid Email", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void addStudent() {
        if (!validateFields()) return;

        String id = idField.getText().trim();
        if (findStudent(id) != null) {
            JOptionPane.showMessageDialog(this,
                    "This Student ID already exists.",
                    "Duplicate ID", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = readStudentFromFields();
        students.add(student);
        refreshTable();
        clearFields();
        statusLabel.setText("Student added successfully. Total students: " + students.size());
        JOptionPane.showMessageDialog(this, "Student record added successfully.");
    }

    private Student readStudentFromFields() {
        return new Student(
                idField.getText().trim(),
                nameField.getText().trim(),
                emailField.getText().trim(),
                departmentField.getText().trim(),
                semesterField.getText().trim(),
                phoneField.getText().trim()
        );
    }

    private void searchStudent() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a Student ID to search.");
            return;
        }

        Student student = findStudent(id);
        if (student == null) {
            JOptionPane.showMessageDialog(this, "No student found with ID: " + id,
                    "Record Not Found", JOptionPane.INFORMATION_MESSAGE);
            statusLabel.setText("Search completed: no matching student.");
            return;
        }

        showStudentInFields(student);
        selectStudentInTable(student.getStudentId());
        statusLabel.setText("Record found: " + student.getDetails());
    }

    private void updateStudent() {
        if (!validateFields()) return;

        String id = idField.getText().trim();
        Student existing = findStudent(id);
        if (existing == null) {
            JOptionPane.showMessageDialog(this,
                    "Student ID not found. Search for the correct ID first.",
                    "Record Not Found", JOptionPane.ERROR_MESSAGE);
            return;
        }

        existing.setName(nameField.getText().trim());
        existing.setEmail(emailField.getText().trim());
        existing.setDepartment(departmentField.getText().trim());
        existing.setSemester(semesterField.getText().trim());
        existing.setPhone(phoneField.getText().trim());

        refreshTable();
        statusLabel.setText("Student record updated: " + existing.getDetails());
        JOptionPane.showMessageDialog(this, "Student record updated successfully.");
    }

    private void deleteStudent() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter or select a Student ID to delete.");
            return;
        }

        Student student = findStudent(id);
        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student ID not found.");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete " + student.getName() + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            students.remove(student);
            refreshTable();
            clearFields();
            statusLabel.setText("Student deleted. Total students: " + students.size());
            JOptionPane.showMessageDialog(this, "Student record deleted successfully.");
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getStudentId(),
                    student.getName(),
                    student.getEmail(),
                    student.getDepartment(),
                    student.getSemester(),
                    student.getPhone()
            });
        }
        statusLabel.setText("Total registered students: " + students.size());
    }

    private void loadSelectedStudent() {
        int row = studentTable.getSelectedRow();
        if (row < 0) return;

        idField.setText(tableModel.getValueAt(row, 0).toString());
        nameField.setText(tableModel.getValueAt(row, 1).toString());
        emailField.setText(tableModel.getValueAt(row, 2).toString());
        departmentField.setText(tableModel.getValueAt(row, 3).toString());
        semesterField.setText(tableModel.getValueAt(row, 4).toString());
        phoneField.setText(tableModel.getValueAt(row, 5).toString());
    }

    private void showStudentInFields(Student student) {
        idField.setText(student.getStudentId());
        nameField.setText(student.getName());
        emailField.setText(student.getEmail());
        departmentField.setText(student.getDepartment());
        semesterField.setText(student.getSemester());
        phoneField.setText(student.getPhone());
    }

    private void selectStudentInTable(String id) {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 0).toString().equalsIgnoreCase(id)) {
                studentTable.setRowSelectionInterval(row, row);
                break;
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        departmentField.setText("");
        semesterField.setText("");
        phoneField.setText("");
        studentTable.clearSelection();
    }

    private void exitApplication() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you want to exit the Student Management System?",
                "Exit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem app = new StudentManagementSystem();
            app.setVisible(true);
        });
    }
}
