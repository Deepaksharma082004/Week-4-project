import java.util.ArrayList;
import java.util.Scanner;

public class TaskScheduler {
    private ArrayList<String> tasks;
    private ArrayList<String> times;
    private Scanner scanner;

    public TaskScheduler() {
        tasks = new ArrayList<>();
        times = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Mini Task Scheduler!");
        
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    System.out.println("Exiting Task Scheduler. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n===== Task Scheduler Menu =====");
        System.out.println("1. Add a new task");
        System.out.println("2. View all tasks");
        System.out.println("3. Delete a task");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number between 1-4.");
            scanner.next(); // consume the invalid input
            System.out.print("Enter your choice: ");
        }
        return scanner.nextInt();
    }

    private void addTask() {
        scanner.nextLine(); // consume the newline character
        
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        
        System.out.print("Enter task time: ");
        String taskTime = scanner.nextLine();
        
        tasks.add(taskName);
        times.add(taskTime);
        
        System.out.println("Task added successfully!");
    }

    private void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        
        System.out.println("\n===== Your Tasks =====");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i) + " (Time: " + times.get(i) + ")");
        }
    }

    private void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to delete.");
            return;
        }
        
        viewTasks();
        System.out.print("Enter the task number to delete (or 0 to cancel): ");
        
        int taskNumber;
        while (true) {
            if (scanner.hasNextInt()) {
                taskNumber = scanner.nextInt();
                if (taskNumber >= 0 && taskNumber <= tasks.size()) {
                    break;
                }
            }
            System.out.print("Please enter a valid task number (1-" + tasks.size() + ") or 0 to cancel: ");
            scanner.next(); // consume invalid input
        }
        
        if (taskNumber == 0) {
            System.out.println("Task deletion cancelled.");
            return;
        }
        
        // Adjust for 0-based index
        int index = taskNumber - 1;
        String deletedTask = tasks.remove(index);
        times.remove(index);
        System.out.println("Task '" + deletedTask + "' deleted successfully!");
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.start();
    }
}
