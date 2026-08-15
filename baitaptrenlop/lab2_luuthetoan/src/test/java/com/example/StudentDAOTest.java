package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StudentDAOTest {
    @Test
    public void shouldAddUpdateAndDeleteStudent() {
        StudentDAO dao = new StudentDAO();
        dao.getStudents().clear();

        Student student = new Student("SV001", "An", 8.5);
        dao.addStudent(student);
        assertEquals(1, dao.getStudents().size());

        dao.updateStudent("SV001", "An Nguyen", 9.0);
        assertEquals("An Nguyen", dao.getStudents().get(0).getName());
        assertEquals(9.0, dao.getStudents().get(0).getGrade(), 0.0001);

        dao.deleteStudent("SV001");
        assertTrue(dao.getStudents().isEmpty());
    }
}
