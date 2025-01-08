import java.io.*;
import java.util.*;

public class ToDoList {
    private List<Task> tasks;
    private static final String FILE_NAME = "tasks.txt";

    public ToDoList() {
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(String description) {
        int taskId = tasks.size() + 1;
        Task task = new Task(taskId, description);
        tasks.add(task);
        saveTasks();
    }

    public boolean markTaskCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.markCompleted();
                saveTasks();
                return true;
            }
        }
        return false;
    }

    public void removeTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
        renumberTasks();
        saveTasks();
    }

    private void renumberTasks() {
        int id = 1;
        for (Task task : tasks) {
            task = new Task(id++, task.getDescription());
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.println(task.getId() + "," + task.getDescription() + "," + task.isCompleted());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private void loadTasks() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    Task task = new Task(Integer.parseInt(data[0]), data[1]);
                    if (Boolean.parseBoolean(data[2])) {
                        task.markCompleted();
                    }
                    tasks.add(task);
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks.");
            }
        }
    }
}