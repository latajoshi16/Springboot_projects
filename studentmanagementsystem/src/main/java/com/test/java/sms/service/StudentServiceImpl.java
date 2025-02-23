package com.test.java.sms.service;

import com.test.java.sms.dto.StudentDTO;
import com.test.java.sms.entity.Student;
import com.test.java.sms.mapper.StudentMapper;
import com.test.java.sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDto = students.stream()
                .map(student -> StudentMapper.mapToStudentDTO(student))
                .collect(Collectors.toList());
        //Old methods for conversion
        /*StudentMapper mapper = new StudentMapper();
        for(Student student: students){
            studentDto.add(mapper.mapToStudentDTO(student));
        }*/
        return studentDto;
    }

    @Override
    public void createStudent(StudentDTO student) {

        studentRepository.save(StudentMapper.mapToStudent(student));
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        StudentDTO studentDTO = StudentMapper.mapToStudentDTO(student);
        return studentDTO;
    }

    @Override
    public void updateStudent(StudentDTO student) {
        studentRepository.save(StudentMapper.mapToStudent(student));
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }


}
