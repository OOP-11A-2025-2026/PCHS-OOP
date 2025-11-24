package TaskPackage;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(int id);
    void updateTask(int id, Task task);
    boolean deleteTask(int id);
}
