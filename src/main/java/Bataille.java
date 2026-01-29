import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}

public class Bataille {
    private static final int MAX_ITEMS = 100;
    private static Task[] storedTasks = new Task[MAX_ITEMS];
    private static int count = 0;

    public static void printInput() {
        System.out.println("\n____________________________________________________________");
        System.out.println(" Sacred Taboos in your ledger: ");
        if (count == 0) {
            System.out.println(" The void stares back at you. No truths dared spoken yet.");
        } else {
            for (int i = 0; i < count; i++) {
                Task task = storedTasks[i];
                String status = task.getStatusIcon();
                System.out.println(" " + (i + 1) + "." + status + " " + task.getDescription());
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void markTask(int index, boolean done) {
        if (index < 1 || index > count) {
            System.out.println("\n____________________________________________________________");
            System.out.println(" No such taboo exists in the sacred order.");
            System.out.println("____________________________________________________________");
            return;
        }

        Task task = storedTasks[index - 1];
        if (done) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        String action = done ? "profaned" : "restored to sacred";
        String status = "[" + task.getStatusIcon() + "]";
        String philosophical = done ?
                "Another ritual completed. Another boundary crossed." :
                "The task returns to its potential state. Unfinished, like all existence.";

        System.out.println("\n____________________________________________________________");
        System.out.println(" The limit has been crossed! This truth is now " + action + ": ");
        System.out.println("   " + status + " " + task.getDescription());
        System.out.println(" " + philosophical);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        boolean active = true;
        Scanner in = new Scanner(System.in);

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

        System.out.println("\n'list' - View all taboos; 'profane X' - Violate taboo X; 'restore X' - Restore taboo X");
        while (active) {
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("\n____________________________________________________________");
                System.out.println("We part at the threshold of the impossible.");
                System.out.println("Remember: to live is to die continuously.");
                System.out.println("Goodbye.");
                System.out.println("____________________________________________________________");
                active = false;
            } else if (input.equalsIgnoreCase("list")) {
                printInput();
            } else if (input.toLowerCase().startsWith("profane ")) {
                try {
                    int index = Integer.parseInt(input.substring(8).trim());
                    markTask(index, true);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format for profane command.");
                }
            } else if (input.toLowerCase().startsWith("restore ")) {
                try {
                    int index = Integer.parseInt(input.substring(8).trim());
                    markTask(index, false);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format for restore command.");
                }
            } else {
                if (count < MAX_ITEMS) {
                    Task newTask = new Task(input);
                    storedTasks[count] = newTask;
                    count++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Taboo inscribed: " + input);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("The ledger is full. No more truths can be inscribed.");
                }
            }
        }
        in.close();
    }
}