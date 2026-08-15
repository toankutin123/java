package com.example;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final List<Student> students = new ArrayList<>();

    public StudentDAO() {
        students.add(new Student("SV001", "An", 8.5));
        students.add(new Student("SV002", "Bình", 7.0));
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Sinh viên không được null");
        }
        students.add(student);
    }

    public void updateStudent(String id, String name, double grade) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setGrade(grade);
                return;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy sinh viên cần sửa");
    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }
}
