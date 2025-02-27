package com.test.java.todo.service;

import com.test.java.todo.dto.TodoDto;
import com.test.java.todo.entity.Todo;
import com.test.java.todo.exception.ResourceNotFoundException;
import com.test.java.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository,ModelMapper modelMapper){
        this.todoRepository=todoRepository;
        this.modelMapper=modelMapper;
    }


    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        TodoDto savedTodoDto = modelMapper.map(todoRepository.save(todo),TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<TodoDto> todoDtos = todoRepository.findAll().stream().map((todo)->modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
        return todoDtos;
    }



    @Override
    public TodoDto getTodoItem(Long todoId) {
        TodoDto todo = modelMapper.map(todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo not found for: " + todoId)), TodoDto.class);
        return todo;
    }

    @Override
    public TodoDto editTodoItem(TodoDto todoDto, Long todoId) {

        Todo todos = modelMapper.map(todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo not found for: " + todoId)), Todo.class);
        todos.setId(todoId);
        todos.setTitle(todoDto.getTitle());
        todos.setDescription(todoDto.getDescription());
        todos.setCompleted(todoDto.isCompleted());
        TodoDto savedTodoDto = modelMapper.map(todoRepository.save(todos),TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo not found for : " + todoId));
        todoRepository.delete(todo);

    }


}
