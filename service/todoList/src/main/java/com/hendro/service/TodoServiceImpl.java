package com.hendro.service;

import com.hendro.exception.TodoAlreadyExistsException;
import com.hendro.exception.TodoNotFoundException;
import com.hendro.model.Todo;
import com.hendro.repository.TodoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        // Pastikan bahwa ID tidak diset ketika membuat entitas baru
        if (todo.getId() != null) {
            // Jika ID diberikan, cek apakah sudah ada Todo dengan ID tersebut
            if (todoRepository.existsById(todo.getId())) {
                throw new TodoAlreadyExistsException("Todo already exists with ID: " + todo.getId());
            }
        }
        // Simpan Todo baru
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(int id) {
        // Menggunakan orElseThrow untuk menangani jika Todo tidak ditemukan
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Requested Todo does not exist with ID: " + id));
    }

    @Override
    public Todo editTodo(Todo updatedTodo, int id) {
        // Ambil Todo lama
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("No Such Todo exists with ID: " + id));

        // Update hanya properti yang perlu, jangan ubah ID
        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setCompleted(updatedTodo.isCompleted());

        // Simpan Todo yang diperbarui
        return todoRepository.save(existingTodo);
    }

    @Override
    public void deleteTodo(int id) {
        // Cek apakah Todo dengan ID tersebut ada
        Todo requestedTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Requested Todo to be deleted does not exist with ID: " + id));

        // Hapus Todo
        todoRepository.deleteById(id);
    }
}
