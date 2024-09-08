package com.hendro.controller;

import com.hendro.model.Todo;
import com.hendro.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest todoRequest) {
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle());

        Todo createdTodo = todoService.createTodo(todo);

        TodoResponse response = new TodoResponse(
                createdTodo.getId(),
                createdTodo.getTitle(),
                createdTodo.isCompleted()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        List<TodoResponse> response = todos.stream()
                .map(todo -> new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        Todo todo = todoService.getTodoById(id);
        TodoResponse response = new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        Todo updatedTodo = new Todo();
        updatedTodo.setTitle(todoRequest.getTitle());
        // You can update completed status if needed

        Todo todo = todoService.updateTodo(id, updatedTodo);
        TodoResponse response = new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}