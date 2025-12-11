package com.example.todo.model;

public class Todo {
    private int id;
    private String text;
    private boolean done;

    public Todo() {}

    public Todo(int id, String text) {
        this.id = id;
        this.text = text;
        this.done = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s", id, (done ? "✓" : " "), text);
    }
}
