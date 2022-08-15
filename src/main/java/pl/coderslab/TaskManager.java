package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    //developed on main branch
    public static void main(String[] args) {
//        String[][] tasksList = new String[0][0];
//        tasksList = writeTasksToArray("target/tasks.csv");
//        printTasks(tasksList);
        mainRun();


    }

    public static void mainRun() {
        String[][] tasksList = new String[0][0];
        tasksList = writeTasksToArray("target/tasks.csv");
        printTasks(tasksList);

        Scanner scan = new Scanner(System.in);
        String line = "";
        String[] options = {"add", "remove", "list", "select"};
        printOptions(options);
        while (!(line = scan.nextLine()).equals("quit")) {
            switch (line) {
                case "add":
                    System.out.println("invoke addTask();");
                    break;
                case "remove":
                    System.out.println("invoke removeTask();");
                    break;
                case "list":
                    System.out.println("invoke listTask();");
                    break;
                case "select":
                    System.out.println("invoke selectTask();");
                    break;
                default:
                    useCorrectPrtMsg(options);
                    break;
            }
        }
    }

    public static void printOptions(String[] arr) {
        System.out.println(ConsoleColors.BLUE + "Please, select an option: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(ConsoleColors.CYAN_BRIGHT + " -- " + arr[i] + ConsoleColors.RESET);
        }
    }

    public static void useCorrectPrtMsg(String[] arr) {
        System.out.println(ConsoleColors.PURPLE_BOLD + "Please, use correct command: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(ConsoleColors.CYAN_BRIGHT + " -- " + arr[i] + ConsoleColors.RESET);
        }
    }

    public static void printTasks(String [][] tasksList) {
        for (int i = 0; i < tasksList.length; i++) {
            System.out.println("Arrays.toString(tasksList[i]) = " + Arrays.toString(tasksList[i]));
        }
        System.out.println("tasksList.length = " + tasksList.length);
    }

    public static String[][] writeTasksToArray(String fileName) {
        File file = new File(fileName);
        String line;
        Scanner scan = new Scanner(System.in);
        String[][] tasksList = new String[0][3];
        String[] tokens = new String[3];
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                tokens = line.split(", ");
                tasksList = Arrays.copyOf(tasksList, tasksList.length + 1);
                tasksList[tasksList.length - 1] = tokens;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File your're looking for does not exist." +
                    "\nSee localized message of the exception -> e.getLocalizedMessage() = " + e.getLocalizedMessage());
        }
        return tasksList;
    }
}