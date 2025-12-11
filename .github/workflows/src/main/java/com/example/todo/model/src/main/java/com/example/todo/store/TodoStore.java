package com.example.todo.store;

import com.example.todo.model.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoStore {
    private static final String FILE_PATH = "todos.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Todo> todos = new ArrayList<>();

    public TodoStore() {
        load();
    }

    private void load() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                todos = mapper.readValue(file, new TypeReference<List<Todo>>() {});
            } catch (IOException e) {
                System.out.println("Error loading todos: " + e.getMessage());
            }
        }
    }

    private void save() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), todos);
        } catch (IOException e) {
            System.out.println("Error saving todos: " + e.getMessage());
        }
    }

    public List<Todo> getAll() {
        return todos;
    }

    public Todo add(String text) {
        int id = todos.size() == 0 ? 1 : todos.get(todos.size() - 1).getId() + 1;
        Todo todo = new Todo(id, text);
        todos.add(todo);
        save();
        return todo;
    }

    public boolean markDone(int id) {
        for (Todo t : todos) {
            if (t.getId() == id) {
                t.setDone(true);
                save();
                return true;
            }
        }
        return false;
    }

    public boolean remove(int id) {
        boolean removed = todos.removeIf(t -> t.getId() == id);
        if (removed) save();
        return removed;
    }

    public boolean edit(int id, String newText) {
        for (Todo t : todos) {
            if (t.getId() == id) {
                t.setText(newText);
                save();
                return true;
            }
        }
        return false;
    }

    public void clear() {
        todos.clear();
        save();
    }
}
