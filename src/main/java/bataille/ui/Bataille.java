package bataille.ui;

import java.util.Scanner;

import bataille.task.Task;
import bataille.task.Deadline;
import bataille.task.Event;
import bataille.task.ToDo;
import bataille.exception.BatailleException;

public class Bataille {
    private static final int MAX_ITEMS = 100;
    private static Task[] storedTasks = new Task[MAX_ITEMS];
    private static int count = 0;

    public static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public static void opening() {
        System.out.println(" ____________________________________");
        System.out.println("|     I AM THE ACCURSED SHARE        |");
        System.out.println("|     I AM WHAT SOCIETY EXPELS       |");
        System.out.println("|     I AM BLOOD ON WHITE SILK       |");
        System.out.println("|     I AM THE EXCESS THAT DESTROYS  |");
        System.out.println("|     I AM GEORGES BATAILLE          |");
        System.out.println("|____________________________________|");

        System.out.println("\nI speak from the place where categories collapse.");
        System.out.println("The sacred is not what you worship, but what you violate.");
        System.out.println("Speak to me of your darkest truths...");

        System.out.println("\nCommands:");
        System.out.println("'list' - View all taboos");
        System.out.println("'profane X' - Violate taboo X");
        System.out.println("'restore X' - Restore taboo X");
        System.out.println("'todo <taboo>' - Add a simple taboo");
        System.out.println("'deadline <taboo> /by <time>' - Add a taboo with deadline");
        System.out.println("'event <taboo> /from <time> /to <time>' - Add a taboo with duration");
        System.out.println("'bye' - Leave this sacred space");
    }

    public static void closing() {
        printLine();
        System.out.println("We part at the threshold of the impossible.");
        System.out.println("Remember: to live is to die continuously.");
        System.out.println("Goodbye.");
        printLine();
    }

    public static void printInput() {
        printLine();
        System.out.println(" Sacred Taboos in your ledger: ");
        if (count == 0) {
            System.out.println(" The void stares back at you. No truths dared spoken yet.");
        } else {
            for (int i = 0; i < count; i++) {
                Task task = storedTasks[i];
                System.out.println(" " + (i + 1) + "." + task.toString());
            }
        }
        printLine();
    }

    public static void markTask(int index, boolean isDone) throws BatailleException {
        if (index < 1 || index > count) {
            throw new BatailleException("No such taboo exists in the sacred order. The number must be between 1 and " + count + ".");
        }

        Task task = storedTasks[index - 1];
        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        String action = isDone ? "profaned" : "restored to sacred";
        String philosophicalReflection = isDone ?
                "Another ritual completed. Another boundary crossed." :
                "The task returns to its potential state. Unfinished, like all existence.";

        printLine();
        System.out.println(" The limit has been crossed! This truth is now " + action + ": ");
        System.out.println("   " + task.toString());
        System.out.println(" " + philosophicalReflection);
        printLine();
    }

    public static void addTask(Task task) throws BatailleException {
        if (count >= MAX_ITEMS) {
            throw new BatailleException("The ledger is full. No more truths can be inscribed. " +
                    "Maximum " + MAX_ITEMS + " taboos allowed.");
        }
        storedTasks[count] = task;
        count++;
        printLine();
        System.out.println(" Got it. I've added this taboo:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + count + " taboos in your ledger.");
        printLine();
    }

    public static void handleToDo(String input) throws BatailleException {
        int todoLength = 5;
        if (input.length() <= todoLength) {
            throw new BatailleException("Taboo cannot be empty. Speak the unspeakable.\n" +
                      "Usage: todo <description>");
        }
        String description = input.substring(todoLength).trim();
        if (description.isEmpty()) {
            throw new BatailleException("Taboo cannot be empty. Speak the unspeakable.\n" +
                      "Usage: todo <description>");
        }
        addTask(new ToDo(description));
    }

    public static void handleDeadline(String input) throws BatailleException {
        int deadlineLength = 9;
        int byLength = 4;
        if (!input.contains("/by")) {
            throw new BatailleException("A deadline requires both a taboo and a moment of reckoning.\n" +
                      "Usage: deadline <description> /by <time>");
        }
        int index = input.indexOf("/by");
        if (input.length() <= deadlineLength) {
            throw new BatailleException("The description cannot be empty.\n" +
                      "Usage: deadline <description> /by <time>");
        }
        String description = input.substring(deadlineLength, index).trim();
        String by = input.substring(index + byLength).trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new BatailleException("Both the taboo and its deadline must be spoken.\n" +
                    "Usage: deadline <description> /by <time>");
        }

        addTask(new Deadline(description, by));
    }

    public static void handleEvent(String input) throws BatailleException {
        int eventLength = 6;
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new BatailleException("An event requires a taboo, a beginning, and an end.\n" +
                      "Usage: event <description> /from <start> /to <end>");
        }

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        int fromLength = 6;
        int byLength = 4;
        String description = input.substring(eventLength, fromIndex).trim();
        String from = input.substring(fromIndex + fromLength, toIndex).trim();
        String to = input.substring(toIndex + byLength).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new BatailleException("The event, its beginning, and its end must all be spoken.\n" +
                      "Usage: event <description> /from <start> /to <end>");
        }

        addTask(new Event(description, from, to));
    }

    public static void handleProfane(String input) throws BatailleException {
        int profaneLength = 8;
        if (input.length() <= profaneLength) {
            throw new BatailleException("You must specify which taboo to profane.\n" +
                      "Usage: profane <number>");
        }
        try {
            int index = Integer.parseInt(input.substring(profaneLength).trim());
            markTask(index, true);
        } catch (NumberFormatException e) {
            throw new BatailleException("Invalid number format for profane command.\n" +
                      "Usage: profane <number>");
        }
    }

    public static void handleRestore(String input) throws BatailleException {
        int restoreLength = 8;
        if (input.length() <= restoreLength) {
            throw new BatailleException("You must specify which taboo to restore.\n" +
                     "Usage: restore <number>");
        }
        try {
            int index = Integer.parseInt(input.substring(restoreLength).trim());
            markTask(index, false);
        } catch (NumberFormatException e) {
            throw new BatailleException("Invalid number format for restore command.\n" +
                      "Usage: restore <number>");
        }
    }

    public static void handleError(String input) throws BatailleException {
        throw new BatailleException("I don't understand this ritual. Speak clearly.");
    }

    public static void processCommand(String input) throws BatailleException {
        if (input.equalsIgnoreCase("list")) {
            printInput();
        } else if (input.toLowerCase().startsWith("profane ")) {
            handleProfane(input);
        } else if (input.toLowerCase().startsWith("restore ")) {
            handleRestore(input);
        } else if (input.toLowerCase().startsWith("todo ")) {
            handleToDo(input);
        } else if (input.toLowerCase().startsWith("deadline ")) {
            handleDeadline(input);
        } else if (input.toLowerCase().startsWith("event ")) {
            handleEvent(input);
        } else {
            handleError(input);
        }
    }

    public static void main(String[] args) {
        boolean isActive = true;
        Scanner in = new Scanner(System.in);

        opening();

        while (isActive) {
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                closing();
                isActive = false;
            } else {
                try {
                    processCommand(input);
                } catch (BatailleException e) {
                    printLine();
                    System.out.println(e.getMessage());
                    printLine();
                }
            }
        }
        in.close();
    }
}