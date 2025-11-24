package TaskPackage;
import java.util.*;

public class TaskStorage implements TaskRepository {

    private final Map<Integer, Task> tasks;
    private int nextId;


    public TaskStorage() {
        this.tasks = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Cannot add null task");
        }

        if (task.getId() == 0) {
            task.setId(nextId++);
        } else {
            if (tasks.containsKey(task.getId())) {
                throw new IllegalArgumentException("Task with ID " + task.getId() + " already exists");
            }

            if (task.getId() >= nextId) {
                nextId = task.getId() + 1;
            }
        }

        tasks.put(task.getId(), task);
    }


    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTaskById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive, (id: " + id + ")");
        }
        return tasks.get(id);
    }


    @Override
    public void updateTask(int id, Task task) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive, (id: " + id + ")");
        }
        if (task == null) {
            throw new IllegalArgumentException("Cannot update with null task");
        }
        if (!tasks.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Cannot update: task with ID " + id + " does not exist"
            );
        }

        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public boolean deleteTask(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive, (id: " + id + ")");
        }
        return tasks.remove(id) != null;
    }

    public int size() {
        return tasks.size();
    }

    public void clear() {
        tasks.clear();
        nextId = 1;
    }
}