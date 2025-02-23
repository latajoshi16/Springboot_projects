package com.test.java.sms.controller;

import com.test.java.sms.dto.StudentDTO;
import com.test.java.sms.entity.Student;
import com.test.java.sms.mapper.StudentMapper;
import com.test.java.sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    StudentService studentService ;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //handler method to handle list student request
    @GetMapping("/students")
    public String listStudents(Model model){
        List<StudentDTO> studentDTOList = studentService.getAllStudents();
        model.addAttribute("students",studentDTOList);

        return "students";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model){
        StudentDTO studentDTO = new StudentDTO();
        model.addAttribute("student",studentDTO);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDTO studentDTO
            , BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("student",studentDTO);
            return "create_student";
        }
        studentService.createStudent(studentDTO);

        return "redirect:/students";
    }

    //handler method to handle edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId, Model model){
        StudentDTO student = studentService.getStudentById(studentId);
        model.addAttribute("student",student);

        return "edit_student";
    }

    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId
            , @Valid @ModelAttribute("student") StudentDTO studentDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("student",studentDTO);
            return "edit_student";
        }
        studentDTO.setId(studentId);
        studentService.updateStudent(studentDTO);

        return "redirect:/students";
   }

    //handler method to handle edit student request
    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);

        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model){
        StudentDTO student = studentService.getStudentById(studentId);
        model.addAttribute("students",student);
        return "view_student";
    }



}
