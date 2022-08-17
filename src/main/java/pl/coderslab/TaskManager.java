package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        mainRun();
    }

    public static void mainRun() {
        String[][] tasksList;
        String fileName = "target/tasks.csv";
        tasksList = writeCsvTasksToArray(fileName);
        Scanner scan = new Scanner(System.in);
        String line = "";

        String[] options = {"add", "remove", "list", "save"};
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
                case "save":
                    saveToFile(tasksList, fileName);
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
            System.out.println(ConsoleColors.CYAN_BRIGHT + " --> " + arr[i] + ConsoleColors.RESET);
        }
    }

    public static void useCorrectPrtMsg(String[] arr) {
        System.out.println(ConsoleColors.PURPLE_BOLD + "Something is not going well. Please, do not isolate yourself in the big depression, " +
                "just use correct command and enjoy: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(ConsoleColors.CYAN_BRIGHT + " -- " + arr[i] + ConsoleColors.RESET);
        }
    }

    public static String[][] writeCsvTasksToArray(String fileName) {
        File file = new File(fileName);
        String line;
        String[][] tasksList = new String[0][0];
        String[] tokens;
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
        String msg = ConsoleColors.RED_BRIGHT + "Please, select number of the task you wanna to permanently remove: \n" +
                ConsoleColors.GREEN + "\nTo cancel please type '0'" + ConsoleColors.RESET;

        System.out.println(msg);
        while (!scan.hasNextInt()) {
            System.out.println(msg);
            scan.next();
        }

        taskToRmNum = scan.nextInt();
        if ((taskToRmNum >= 1) && (taskToRmNum <= (listTasks.length))) {
            listTasks[taskToRmNum - 1] = listTasks[listTasks.length - 1];
            listTasks = Arrays.copyOf(listTasks, listTasks.length - 1);
            System.out.println("listTasks.length = " + listTasks.length);
            System.out.println(ConsoleColors.PURPLE + "Task removed with full success." + ConsoleColors.RESET);

        } else if (taskToRmNum == 0) {
            System.out.println("You have cancelled the operation.");
            return listTasks;

        } else {
            System.out.println("There is no such a task in database.");

        }
        return listTasks;
    }

    public static void printTasksList(String[][] tasksList) {
        String line = "";
        for (int i = 0; i < tasksList.length; i++) {
            line = Arrays.toString(tasksList[i]);
            System.out.println("Task no: " + (i + 1) + "   --->   " + line.substring(1, line.length()-1));
        }
        System.out.println(ConsoleColors.BLACK_BACKGROUND +
                "Currently you have = " + tasksList.length + " tasks to accomplish."
                + ConsoleColors.RESET);
    }

    public static void saveToFile(String[][] tasksList, String fileName) {
        String[] tokens;
        String line = "";
        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            for (int i = 0; i < tasksList.length; i++) {
                tokens = tasksList[i];
                line = Arrays.toString(tokens);
                line = line.substring(1, line.length() - 1);
                fileWriter.append(line + "\n");
            }
        } catch (IOException ex) {
            System.out.println("It is not possible to write " + fileName + " file.");
        }
        System.out.println(ConsoleColors.GREEN_BRIGHT + "See you again! Have a good day :)" + ConsoleColors.RESET +
                ConsoleColors.RED + "\nDo not forget to QUIT :)" + ConsoleColors.RESET);
    }
}