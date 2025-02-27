package com.test.java.todo.controller;

import com.test.java.todo.dto.TodoDto;
import com.test.java.todo.entity.Todo;
import com.test.java.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private final TodoService todoService ;

    public TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    //Add TODO REST API
    @PostMapping("")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //List all Todos
    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getAllTodosItems(){
        List<TodoDto> todoDto = todoService.getAllTodos();
        return new ResponseEntity<>(todoDto,HttpStatus.CREATED);
    }

    @GetMapping("{todoId}")
    public ResponseEntity<TodoDto> getTodoItem(@PathVariable("todoId") Long todoId){
        TodoDto todo = todoService.getTodoItem(todoId);
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }

    @PutMapping("{todoId}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long todoId,@RequestBody TodoDto todoDto){

        TodoDto todo = todoService.editTodoItem(todoDto, todoId);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("{todoId}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok("Todo deleted successfully!.");
    }

    //Build complete todo
    @PatchMapping("{todoId}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long todoId){

        TodoDto todo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    @PatchMapping("{todoId}/incomplete")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long todoId){


        TodoDto todo = todoService.incompleteTodo(todoId);
        return ResponseEntity.ok(todo);
    }
}
