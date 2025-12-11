package com.example.todo;

import com.example.todo.model.Todo;
import com.example.todo.store.TodoStore;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TodoStoreTest {
    private Path tempFile;
    private TodoStore store;

    @BeforeAll
    void setup() throws Exception {
        tempFile = Files.createTempFile("todos-test", ".json");
        // Use system property to point the store to a temp file if needed
        // But our store uses a fixed filename; we'll create the store and then delete file after tests.
        store = new TodoStore();
        store.clear(); // ensure clean start
    }

    @AfterAll
    void cleanup() {
        // Remove test file if created
        File file = new File("todos.json");
        if (file.exists()) file.delete();
    }

    @Test
    void testAddListRemove() {
        store.clear();
        Todo t1 = store.add("task one");
        Todo t2 = store.add("task two");
        List<Todo> list = store.getAll();
        assertEquals(2, list.size());

        boolean done = store.markDone(t1.getId());
        assertTrue(done);
        assertTrue(store.getAll().stream().anyMatch(Todo::isDone));

        boolean removed = store.remove(t2.getId());
        assertTrue(removed);
        assertEquals(1, store.getAll().size());
    }
}
