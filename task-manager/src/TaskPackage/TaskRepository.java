package TaskPackage;

import java.util.List;

public interface TaskRepository {
    void addTask(Task t);
    List<Task> getAllTasks();
    Task getTaskById(int id);
    void updateTask(int id, Task t);
    boolean deleteTask(int id);
}
