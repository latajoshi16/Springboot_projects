package com.test.java.todo.service;

import com.test.java.todo.dto.TodoDto;
import com.test.java.todo.entity.Todo;

import java.util.List;


public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    List<TodoDto> getAllTodos();

    TodoDto getTodoItem(Long todoId);

    TodoDto editTodoItem(TodoDto todoDto, Long todoId);

    void deleteTodo(Long todoId);


}
