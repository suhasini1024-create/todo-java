package com.example.todo;

import com.example.todo.model.Todo;
import com.example.todo.store.TodoStore;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final TodoStore store = new TodoStore();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome — Todo Manager (type 'help' for commands)");

        // Support running a single command via args (optional)
        if (args.length > 0) {
            runCommand(String.join(" ", args));
            return;
        }

        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            if ("exit".equalsIgnoreCase(line) || "quit".equalsIgnoreCase(line)) {
                System.out.println("Bye!");
                break;
            }
            runCommand(line);
        }
    }

    private static void runCommand(String line) {
        String[] parts = line.split(" ", 2);
        String cmd = parts[0].toLowerCase();

        try {
            switch (cmd) {
                case "help":
                    printHelp();
                    break;
                case "list":
                    list();
                    break;
                case "add":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        System.out.println("Usage: add <task description>");
                    } else {
                        Todo t = store.add(parts[1].trim());
                        System.out.println("Added: " + t);
                    }
                    break;
                case "done":
                    if (parts.length < 2) { System.out.println("Usage: done <id>"); break; }
                    try {
                        int id = Integer.parseInt(parts[1].trim());
                        System.out.println(store.markDone(id) ? "Marked done." : "Not found.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id.");
                    }
                    break;
                case "rm":
                    if (parts.length < 2) { System.out.println("Usage: rm <id>"); break; }
                    try {
                        int id = Integer.parseInt(parts[1].trim());
                        System.out.println(store.remove(id) ? "Removed." : "Not found.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id.");
                    }
                    break;
                case "edit":
                    if (parts.length < 2) { System.out.println("Usage: edit <id> <text>"); break; }
                    String[] p = parts[1].trim().split(" ", 2);
                    if (p.length < 2) { System.out.println("Usage: edit <id> <text>"); break; }
                    try {
                        int id = Integer.parseInt(p[0].trim());
                        System.out.println(store.edit(id, p[1].trim()) ? "Edited." : "Not found.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id.");
                    }
                    break;
                case "clear":
                    store.clear();
                    System.out.println("All todos cleared.");
                    break;
                case "find":
                    if (parts.length < 2) { System.out.println("Usage: find <text>"); break; }
                    find(parts[1].trim());
                    break;
                default:
                    System.out.println("Unknown command. Type 'help'.");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void list() {
        List<Todo> all = store.getAll();
        if (all.isEmpty()) {
            System.out.println("(no todos)");
            return;
        }
        all.forEach(t -> System.out.println(t));
    }

    private static void find(String text) {
        List<Todo> all = store.getAll();
        all.stream()
           .filter(t -> t.getText().toLowerCase().contains(text.toLowerCase()))
           .forEach(t -> System.out.println(t));
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  list            - show todos");
        System.out.println("  add <text>      - add a todo");
        System.out.println("  done <id>       - mark as done");
        System.out.println("  rm <id>         - remove todo");
        System.out.println("  edit <id> <text>- edit todo");
        System.out.println("  find <text>     - search todos");
        System.out.println("  clear           - remove all todos");
        System.out.println("  help            - show this help");
        System.out.println("  exit / quit     - exit app");
    }
}
