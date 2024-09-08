package com.hendro.service;

import com.hendro.model.Todo;

import java.util.List;

public interface TodoService {
    Todo createTodo(Todo todo);
    List<Todo> getAllTodos();
    Todo getTodo(int id);
    Todo editTodo(Todo todo, int id);
    void deleteTodo(int id);
}
