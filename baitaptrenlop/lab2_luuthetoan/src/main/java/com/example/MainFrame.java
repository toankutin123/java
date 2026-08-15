package com.example;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class MainFrame extends JFrame {
    private final JTextField nameField = new JTextField();
    private final JTextField firstNumberField = new JTextField();
    private final JTextField secondNumberField = new JTextField();
    private final JTextField aField = new JTextField();
    private final JTextField bField = new JTextField();
    private final JTextField triangleAField = new JTextField();
    private final JTextField triangleBField = new JTextField();
    private final JTextField triangleCField = new JTextField();
    private final JTextField fibonacciField = new JTextField();
    private final JTextArea fibonacciArea = new JTextArea();
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Admin", "Student"});
    private final JCheckBox rememberCheckBox = new JCheckBox("Ghi nhớ đăng nhập");
    private final JTextField displayField = new JTextField("0");
    private final JTextArea calculatorHistory = new JTextArea();
    private final JTextField studentIdField = new JTextField();
    private final JTextField studentNameField = new JTextField();
    private final JTextField studentGradeField = new JTextField();
    private final StudentDAO studentDAO = new StudentDAO();
    private final StudentTableModel studentTableModel = new StudentTableModel(studentDAO.getStudents());
    private final JTable studentTable = new JTable(studentTableModel);

    private double calculatorValue = 0;
    private String calculatorOperator = "";
    private boolean calculatorStartNewInput = true;

    public MainFrame() {
        setTitle("Bài tập Java Swing theo Maven");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 620);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Bài 1", buildHelloPanel());
        tabs.addTab("Bài 2", buildSumPanel());
        tabs.addTab("Bài 3", buildEquationPanel());
        tabs.addTab("Bài 4", buildTrianglePanel());
        tabs.addTab("Bài 5", buildFibonacciPanel());
        tabs.addTab("Bài 6", buildLoginPanel());
        tabs.addTab("Bài 7", buildCalculatorPanel());
        tabs.addTab("Bài 8", buildStudentPanel());

        add(tabs, BorderLayout.CENTER);
    }

    private JPanel buildHelloPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.add(new JLabel("Bài 1: Chào người dùng"));
        panel.add(new JLabel("Nhập tên:"));
        panel.add(nameField);
        JButton button = new JButton("Xin chào");
        button.addActionListener(e -> JOptionPane.showMessageDialog(this, "Xin chào, " + nameField.getText().trim()));
        panel.add(button);
        return panel;
    }

    private JPanel buildSumPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.add(new JLabel("Bài 2: Tính tổng hai số"));
        panel.add(new JLabel("Số thứ nhất:"));
        panel.add(firstNumberField);
        panel.add(new JLabel("Số thứ hai:"));
        panel.add(secondNumberField);
        JButton button = new JButton("Tính tổng");
        button.addActionListener(e -> {
            try {
                double a = Double.parseDouble(firstNumberField.getText().trim());
                double b = Double.parseDouble(secondNumberField.getText().trim());
                JOptionPane.showMessageDialog(this, "Tổng = " + ExerciseLogic.sum(a, b));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số");
            }
        });
        panel.add(button);
        return panel;
    }

    private JPanel buildEquationPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.add(new JLabel("Bài 3: Giải phương trình bậc nhất"));
        panel.add(new JLabel("a:"));
        panel.add(aField);
        panel.add(new JLabel("b:"));
        panel.add(bField);
        JButton button = new JButton("Giải");
        button.addActionListener(e -> {
            try {
                double a = Double.parseDouble(aField.getText().trim());
                double b = Double.parseDouble(bField.getText().trim());
                JOptionPane.showMessageDialog(this, ExerciseLogic.solveLinearEquation(a, b));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số");
            }
        });
        panel.add(button);
        return panel;
    }

    private JPanel buildTrianglePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.add(new JLabel("Bài 4: Kiểm tra và phân loại tam giác"));
        panel.add(new JLabel("Cạnh 1:"));
        panel.add(triangleAField);
        panel.add(new JLabel("Cạnh 2:"));
        panel.add(triangleBField);
        panel.add(new JLabel("Cạnh 3:"));
        panel.add(triangleCField);
        JButton button = new JButton("Kiểm tra");
        button.addActionListener(e -> {
            try {
                double a = Double.parseDouble(triangleAField.getText().trim());
                double b = Double.parseDouble(triangleBField.getText().trim());
                double c = Double.parseDouble(triangleCField.getText().trim());
                JOptionPane.showMessageDialog(this, ExerciseLogic.classifyTriangle(a, b, c));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số");
            }
        });
        panel.add(button);
        return panel;
    }

    private JPanel buildFibonacciPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        JPanel top = new JPanel(new GridLayout(0, 1, 8, 8));
        top.add(new JLabel("Bài 5: Hiển thị n số Fibonacci"));
        top.add(new JLabel("Nhập n:"));
        top.add(fibonacciField);
        JButton button = new JButton("Hiển thị");
        button.addActionListener(e -> {
            try {
                int n = Integer.parseInt(fibonacciField.getText().trim());
                List<Long> values = ExerciseLogic.generateFibonacci(n);
                fibonacciArea.setText(values.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên");
            }
        });
        top.add(button);
        panel.add(top, BorderLayout.NORTH);
        fibonacciArea.setRows(10);
        fibonacciArea.setLineWrap(true);
        fibonacciArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(fibonacciArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.add(new JLabel("Bài 6: Form đăng nhập cơ bản"));
        panel.add(new JLabel("Tên đăng nhập:"));
        panel.add(usernameField);
        panel.add(new JLabel("Mật khẩu:"));
        panel.add(passwordField);
        panel.add(new JLabel("Vai trò:"));
        panel.add(roleComboBox);
        panel.add(rememberCheckBox);
        JButton button = new JButton("Đăng nhập");
        button.addActionListener(e -> {
            boolean valid = ExerciseLogic.validateLogin(usernameField.getText(), new String(passwordField.getPassword()), roleComboBox.getSelectedItem().toString());
            if (valid) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu");
            }
        });
        panel.add(button);
        return panel;
    }

    private JPanel buildCalculatorPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        panel.add(displayField, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(4, 4, 8, 8));
        String[] labels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        for (String label : labels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> handleCalculatorInput(label));
            buttons.add(button);
        }
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> resetCalculator());
        buttons.add(clearButton);
        panel.add(buttons, BorderLayout.CENTER);

        calculatorHistory.setRows(8);
        calculatorHistory.setEditable(false);
        panel.add(new JScrollPane(calculatorHistory), BorderLayout.SOUTH);
        return panel;
    }

    private void handleCalculatorInput(String value) {
        if (value.matches("[0-9.]")) {
            if (calculatorStartNewInput) {
                displayField.setText(value);
                calculatorStartNewInput = false;
            } else {
                displayField.setText(displayField.getText() + value);
            }
            return;
        }

        if ("+".equals(value) || "-".equals(value) || "*".equals(value) || "/".equals(value)) {
            try {
                calculatorValue = Double.parseDouble(displayField.getText());
                calculatorOperator = value;
                calculatorStartNewInput = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ");
            }
            return;
        }

        if ("=".equals(value)) {
            try {
                double current = Double.parseDouble(displayField.getText());
                double result = ExerciseLogic.calculate(calculatorValue, current, calculatorOperator);
                displayField.setText(String.valueOf(result));
                calculatorHistory.append(String.format("%.2f %s %.2f = %.2f%n", calculatorValue, calculatorOperator, current, result));
                calculatorStartNewInput = true;
                calculatorValue = result;
            } catch (ArithmeticException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
                resetCalculator();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập phép toán hợp lệ");
            }
            return;
        }

        if ("Clear".equals(value)) {
            resetCalculator();
        }
    }

    private void resetCalculator() {
        calculatorValue = 0;
        calculatorOperator = "";
        calculatorStartNewInput = true;
        displayField.setText("0");
    }

    private JPanel buildStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.add(new JLabel("Mã sinh viên:"));
        formPanel.add(studentIdField);
        formPanel.add(new JLabel("Tên:"));
        formPanel.add(studentNameField);
        formPanel.add(new JLabel("Điểm:"));
        formPanel.add(studentGradeField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Thêm");
        addButton.addActionListener(e -> addStudent());
        JButton updateButton = new JButton("Sửa");
        updateButton.addActionListener(e -> updateStudent());
        JButton deleteButton = new JButton("Xóa");
        deleteButton.addActionListener(e -> deleteStudent());
        JButton clearButton = new JButton("Xóa form");
        clearButton.addActionListener(e -> clearStudentForm());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        JPanel leftPanel = new JPanel(new BorderLayout(8, 8));
        leftPanel.add(formPanel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.CENTER);

        studentTable.setSelectionBackground(new java.awt.Color(200, 230, 255));
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && studentTable.getSelectedRow() >= 0) {
                Student student = studentDAO.getStudents().get(studentTable.getSelectedRow());
                studentIdField.setText(student.getId());
                studentNameField.setText(student.getName());
                studentGradeField.setText(String.valueOf(student.getGrade()));
            }
        });

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);

        studentTableModel.fireTableDataChanged();
        return panel;
    }

    private void addStudent() {
        try {
            String id = studentIdField.getText().trim();
            String name = studentNameField.getText().trim();
            double grade = Double.parseDouble(studentGradeField.getText().trim());

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã và tên sinh viên không được để trống");
                return;
            }

            studentDAO.addStudent(new Student(id, name, grade));
            studentTableModel.fireTableDataChanged();
            clearStudentForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Điểm phải là số hợp lệ");
        }
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chọn một sinh viên để sửa");
            return;
        }
        try {
            String id = studentIdField.getText().trim();
            String name = studentNameField.getText().trim();
            double grade = Double.parseDouble(studentGradeField.getText().trim());

            studentDAO.updateStudent(id, name, grade);
            studentTableModel.fireTableRowsUpdated(selectedRow, selectedRow);
            clearStudentForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Điểm phải là số hợp lệ");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Chọn một sinh viên để xóa");
            return;
        }
        String id = studentDAO.getStudents().get(selectedRow).getId();
        studentDAO.deleteStudent(id);
        studentTableModel.fireTableDataChanged();
        clearStudentForm();
    }

    private void clearStudentForm() {
        studentIdField.setText("");
        studentNameField.setText("");
        studentGradeField.setText("");
        studentTable.clearSelection();
    }

    private static class StudentTableModel extends AbstractTableModel {
        private final List<Student> students;
        private final String[] columns = {"Mã SV", "Tên", "Điểm"};

        private StudentTableModel(List<Student> students) {
            this.students = students;
        }

        @Override
        public int getRowCount() {
            return students.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Student student = students.get(rowIndex);
            if (columnIndex == 0) {
                return student.getId();
            }
            if (columnIndex == 1) {
                return student.getName();
            }
            if (columnIndex == 2) {
                return student.getGrade();
            }
            return null;
        }
    }
}
