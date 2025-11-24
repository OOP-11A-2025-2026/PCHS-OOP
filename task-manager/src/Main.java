import TaskPackage.ConsoleUI;
import TaskPackage.FileTaskRepository;

public class Main {
    public static void main(String[] args) {
        FileTaskRepository repository = new FileTaskRepository("tasks.txt");
        ConsoleUI ui = new ConsoleUI(repository);
        ui.start();
    }
}
