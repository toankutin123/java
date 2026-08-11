package com.example;

import java.util.ArrayList;
import java.util.List;

public final class ExerciseLogic {
    private ExerciseLogic() {
    }

    public static double sum(double a, double b) {
        return a + b;
    }

    public static double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Không thể chia cho 0");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Toán tử không hợp lệ");
        }
    }

    public static String solveLinearEquation(double a, double b) {
        if (a == 0) {
            if (b == 0) {
                return "Vô số nghiệm";
            }
            return "Vô nghiệm";
        }
        return "x = " + String.format("%.2f", -b / a);
    }

    public static String classifyTriangle(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "Không phải tam giác";
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "Không phải tam giác";
        }
        if (a == b && b == c) {
            return "Tam giác đều";
        }
        if (a == b || a == c || b == c) {
            return "Tam giác cân";
        }
        return "Tam giác thường";
    }

    public static List<Long> generateFibonacci(int n) {
        List<Long> values = new ArrayList<>();
        if (n <= 0) {
            return values;
        }
        long a = 0;
        long b = 1;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                values.add(a);
            } else if (i == 1) {
                values.add(b);
            } else {
                long next = a + b;
                values.add(next);
                a = b;
                b = next;
            }
        }
        return values;
    }

    public static boolean validateLogin(String username, String password, String role) {
        String user = username == null ? "" : username.trim();
        String pass = password == null ? "" : password;
        String selectedRole = role == null ? "" : role.trim();

        if ("admin".equalsIgnoreCase(user) && "admin123".equals(pass) && "Admin".equalsIgnoreCase(selectedRole)) {
            return true;
        }
        return "student".equalsIgnoreCase(user) && "student123".equals(pass) && "Student".equalsIgnoreCase(selectedRole);
    }
}
