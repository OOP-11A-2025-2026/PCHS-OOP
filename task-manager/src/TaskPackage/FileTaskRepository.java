package TaskPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    private final String fileName;

    public FileTaskRepository(String fileName) {
        this.fileName = fileName;
        loadTasksFromFile(); 
    }
    private void loadTasksFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = lineToTask(line);
                if (task != null) {
                    tasks.add(task);
                    if (task.getId() >= nextId) nextId = task.getId() + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Грешка при четене на файл: " + e.getMessage());
        }
    }

    private Task lineToTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 6) return null;

        try {
            int id = Integer.parseInt(parts[0]);
            String title = parts[1];
            String description = parts[2];
            Category category = Category.valueOf(parts[3]);
            Status status = Status.valueOf(parts[4]);
            String owner = parts[5];

            return new Task(id, title, description, category, status, owner);
        } catch (Exception e) {
            System.out.println("Error: " + line);
            return null;
        }
    }
    @Override
    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.add(task);
        saveTaskToFile(task);
    }

    private void saveTaskToFile(Task task) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(taskToLine(task) + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String taskToLine(Task task) {
        return task.getId() + "|" +
               task.getTitle() + "|" +
               task.getDescription() + "|" +
               task.getCategory() + "|" +
               task.getStatus() + "|" +
               task.getOwner();
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void updateTask(int id, Task updatedTask) {
        Task existing = getTaskById(id);
        if(existing != null){
            existing.setTitle(updatedTask.getTitle());
            existing.setDescription(updatedTask.getDescription());
            existing.setCategory(updatedTask.getCategory());
            existing.setStatus(updatedTask.getStatus());
            existing.setOwner(updatedTask.getOwner());
        }
    }

    @Override
    public boolean deleteTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }
}
