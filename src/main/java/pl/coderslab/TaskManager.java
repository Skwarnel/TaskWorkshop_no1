package pl.coderslab;

import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        mainRun();
    }

    public static void mainRun() {
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
}
