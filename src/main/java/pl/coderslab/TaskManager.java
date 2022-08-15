package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    //developed on main branch
    public static void main(String[] args) {
        mainRun();
    }

    public static void mainRun() {
        String[][] tasksList = new String[0][0];
        tasksList = writeCsvTasksToArray();

        Scanner scan = new Scanner(System.in);
        String line = "";

        String[] options = {"add", "remove", "list", "select"};
        printOptions(options);
        while (!(line = scan.nextLine()).equals("quit")) {
            switch (line) {
                case "add":
                    tasksList = addTask(tasksList);
                    printOptions(options);
                    break;
                case "remove":
                    tasksList = removeTask(tasksList);
                    printOptions(options);
                    break;
                case "list":
                    printTasksList(tasksList);
                    printOptions(options);
                    break;
                case "quit":
                    exitMethod();
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
        System.out.println(ConsoleColors.PURPLE_BOLD + "Something is not going well. Please, do not isolate yourself in the big depression, just use correct command and enjoy: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(ConsoleColors.CYAN_BRIGHT + " -- " + arr[i] + ConsoleColors.RESET);
        }
    }

    public static void printTasksList(String[][] tasksList) {
        for (int i = 0; i < tasksList.length; i++) {
            System.out.println("Task no: " + (i + 1) + "   --->   " + Arrays.toString(tasksList[i]));
        }
        System.out.println("Tasks quantity = " + tasksList.length);
    }

    public static String[][] writeCsvTasksToArray() {
        String fileName = "target/tasks.csv";
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
            System.out.println(ConsoleColors.RED_BOLD + "File with database of your tasks does not exist.\n" +
                    "Please don't panic and just check the path: " + "'' " + fileName + " ''" +
                    "\nIf anything to you seems be entirely out of your expectations, just contact with the developer of this software." +
                    "\nSee localized message of the exception -> e.getLocalizedMessage() = " + e.getLocalizedMessage()
                    + ConsoleColors.RESET);
        }
        return tasksList;
    }

    public static String[][] addTask(String[][] listTasks) {
        Scanner scan = new Scanner(System.in);
        String[] token = new String[3];
        listTasks = Arrays.copyOf(listTasks, listTasks.length + 1);
        System.out.println("listTasks.length = " + listTasks.length);

        System.out.println(ConsoleColors.BLUE_BRIGHT + "Please add task description:" + ConsoleColors.RESET);
        token[0] = scan.nextLine();

        System.out.println(ConsoleColors.GREEN + "Please add task due date:" + ConsoleColors.RESET);
        token[1] = scan.nextLine();

        System.out.println(ConsoleColors.CYAN + "Is your task urgent? Please select 'true' or 'false':" + ConsoleColors.RESET);
        token[2] = scan.nextLine();

        listTasks[listTasks.length - 1] = token;
        System.out.println(ConsoleColors.YELLOW_BOLD + "Task added with success." + ConsoleColors.RESET);
        return listTasks;
    }

    public static String[][] removeTask(String[][] listTasks) {
        Scanner scan = new Scanner(System.in);
        int taskToRmNum;
        printTasksList(listTasks);
        System.out.println(ConsoleColors.RED_BRIGHT + "Please, select number of the task you wanna to permanently remove: " + ConsoleColors.RESET);
        while (!(scan.hasNextInt())) {
            System.out.println(ConsoleColors.RED_BRIGHT + "Please, select number of the task you wanna to permanently remove: " + ConsoleColors.RESET);
            scan.nextInt();
        }
        taskToRmNum = scan.nextInt();
        if ((taskToRmNum < 1) || taskToRmNum > listTasks.length) {
            System.out.println("There is no such a task in database.");
        } else {


            listTasks[taskToRmNum + 1] = listTasks[listTasks.length - 1];

            listTasks = Arrays.copyOf(listTasks, listTasks.length - 1);

            System.out.println("listTasks.length = " + listTasks.length);

            System.out.println(ConsoleColors.PURPLE + "Task removed with full success." + ConsoleColors.RESET);

        }
        return listTasks;
    }

    public static void exitMethod() {

    }

}