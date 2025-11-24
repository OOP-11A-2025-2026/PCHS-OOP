import TaskPackage.ConsoleUI;
import TaskPackage.TaskStorage;

public class Main {
    public static void main(String[] args) {
        TaskStorage storage = new TaskStorage();
        ConsoleUI ui = new ConsoleUI(storage);
        ui.start();
    }
}
