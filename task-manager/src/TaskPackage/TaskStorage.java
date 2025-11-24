package TaskPackage;

import java.io.FileWriter;
import java.io.IOException;

public class TaskStorage {

    public static void saveTask(Task task) {
        try (FileWriter writer = new FileWriter("tasks.txt", true)) {
            writer.write(task.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
