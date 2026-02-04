import java.util.Scanner;

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

    public static void markTask(int index, boolean done) {
        if (index < 1 || index > count) {
            printLine();
            System.out.println(" No such taboo exists in the sacred order.");
            printLine();
            return;
        }

        Task task = storedTasks[index - 1];
        if (done) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        String action = done ? "profaned" : "restored to sacred";
        String philosophical = done ?
                "Another ritual completed. Another boundary crossed." :
                "The task returns to its potential state. Unfinished, like all existence.";

        printLine();
        System.out.println(" The limit has been crossed! This truth is now " + action + ": ");
        System.out.println("   " + task.toString());
        System.out.println(" " + philosophical);
        printLine();
    }

    public static void addTask(Task task) {
        if (count < MAX_ITEMS) {
            storedTasks[count] = task;
            count++;
            printLine();
            System.out.println(" Got it. I've added this taboo:");
            System.out.println("   " + task.toString());
            System.out.println(" Now you have " + count + " taboos in your ledger.");
            printLine();
        } else {
            System.out.println("The ledger is full. No more truths can be inscribed.");
        }
    }

    public static void handleToDo(String input) {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("Taboo cannot be empty. Speak the unspeakable.");
            return;
        }
        addTask(new ToDo(description));
    }


    public static void handleDeadline(String input) {
        if (!input.contains("/by")) {
            System.out.println("A deadline requires both a taboo and a moment of reckoning. Use '/by'.");
            return;
        }
        int index = input.indexOf("/by");
        String description = input.substring(9, index).trim();
        String by = input.substring(index + 4).trim();

        if (description.isEmpty() || by.isEmpty()) {
            System.out.println("Both the taboo and its deadline must be spoken.");
            return;
        }

        addTask(new Deadline(description, by));
    }

    public static void handleEvent(String input) {
        if (!input.contains("/from") || !input.contains("/to")) {
            System.out.println("An event requires a taboo, a beginning, and an end. Use '/from' and '/to'.");
            return;
        }

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(6, fromIndex).trim();
        String from = input.substring(fromIndex + 6, toIndex).trim();
        String to = input.substring(toIndex + 4).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println("The event, its beginning, and its end must all be spoken.");
            return;
        }

        addTask(new Event(description, from, to));
    }

    public static void handleProfane(String input) {
        try {
            int index = Integer.parseInt(input.substring(8).trim());
            markTask(index, true);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for profane command.");
        }
    }

    public static void handleRestore(String input) {
        try {
            int index = Integer.parseInt(input.substring(8).trim());
            markTask(index, false);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for restore command.");
        }
    }

    public static void processCommand(String input) {
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
            System.out.println("I don't understand this ritual. Speak clearly.");
        }
    }

    public static void main(String[] args) {
        boolean active = true;
        Scanner in = new Scanner(System.in);

        opening();

        while (active) {
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                closing();
                active = false;
            } else {
                processCommand(input);
            }
        }
        in.close();
    }
}