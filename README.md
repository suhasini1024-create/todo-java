# Java CLI Todo Manager

A simple and beginner-friendly **Todo Manager built using Java (Maven)**.  
This project helps you understand Java fundamentals, OOP concepts, file storage, testing, and GitHub workflows — step by step.

---

## 🚀 Features
- Add new todo items  
- List all todos  
- Mark tasks as done  
- Remove tasks  
- Edit existing tasks  
- Clear all todos  
- Automatic file persistence (`todos.json`)  
- Unit tests using JUnit  
- GitHub Actions CI support  

---

## 📦 Getting Started

### 1️⃣ Clone this repository
```bash
git clone https://github.com/suhasini1024/java-todo-cli.git
cd java-todo-cli
```

### 2️⃣ Run tests
```bash
mvn test
```

### 3️⃣ Run the application
```bash
mvn compile exec:java
```

### (Optional) Build a runnable JAR
```bash
mvn package
java -jar target/todo-java-0.1.0.jar
```

---

## 📝 Commands

| Command | Description |
|--------|-------------|
| `add <text>` | Add a new todo |
| `list` | Show all todos |
| `done <id>` | Mark todo as done |
| `rm <id>` | Remove todo |
| `edit <id> <text>` | Edit an existing todo |
| `clear` | Remove all todos |
| `help` | Show help menu |
| `exit` | Quit the app |

---

## 🎯 Project Purpose
This project is designed to help you:
- Learn Java clean coding practices  
- Understand OOP concepts (classes, objects, methods)  
- Work with file handling and JSON storage  
- Practice writing tests  
- Use Git & GitHub like a real developer  
- Build confidence through a complete, working Java project  

---

## 🤝 Contributing
1. Fork this repo  
2. Create a branch (`feature/new-feature`)  
3. Commit changes  
4. Open a Pull Request  

---

## 📜 License
MIT — feel free to use and modify the project.

---

## ⭐ Show Your Support
If this project helps you learn Java, star ⭐ the repository!
<!-- trigger CI: small edit -->
