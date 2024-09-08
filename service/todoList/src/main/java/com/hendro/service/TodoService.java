package com.hendro.service;

import com.hendro.model.Todo;
import java.util.List;

public interface TodoService {
    Todo createTodo(Todo todo);
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
    Todo updateTodo(Long id, Todo todo);
    void deleteTodoById(Long id);
    void markTodoComplete(Long id);
}
