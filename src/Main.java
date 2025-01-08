import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Remove Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    toDoList.addTask(description);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.print("Enter task ID to mark as completed: ");
                    int completeId = scanner.nextInt();
                    if (toDoList.markTaskCompleted(completeId)) {
                        System.out.println("Task marked as completed!");
                    } else {
                        System.out.println("Invalid task ID.");
                    }
                    break;
                case 3:
                    System.out.print("Enter task ID to remove: ");
                    int removeId = scanner.nextInt();
                    toDoList.removeTask(removeId);
                    System.out.println("Task removed successfully!");
                    break;
                case 4:
                    toDoList.listTasks();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}