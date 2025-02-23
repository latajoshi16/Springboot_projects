package com.test.java.sms.service;

import com.test.java.sms.dto.StudentDTO;
import com.test.java.sms.entity.Student;
import com.test.java.sms.repository.StudentRepository;

import java.util.List;

public interface StudentService{

    List<StudentDTO> getAllStudents();
    void createStudent(StudentDTO student);
    StudentDTO getStudentById(Long studentId);
    void updateStudent(StudentDTO student);

    void deleteStudent(Long studentId);
}
