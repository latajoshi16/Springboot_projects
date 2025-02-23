package com.test.java.sms.mapper;

import com.test.java.sms.dto.StudentDTO;
import com.test.java.sms.entity.Student;

public class StudentMapper {
    public static StudentDTO mapToStudentDTO(Student student){
        StudentDTO studentDTO = new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
        return studentDTO;
    }

    public static Student mapToStudent(StudentDTO studentdto){
        Student student = new Student(
                studentdto.getId(),
                studentdto.getFirstName(),
                studentdto.getLastName(),
                studentdto.getEmail()
        );
        return student;
    }
}
