package bataille.ui;

import java.util.List;
import java.util.Scanner;

import bataille.task.Task;

public class Ui {

    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

	/**
	 * Start scanning.
	 */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the welcome message and command instructions.
     */
    public void printWelcome() {
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
        System.out.println("'find' - Find taboos from the ledger");
        System.out.println("'profane X' - Violate taboo X");
        System.out.println("'restore X' - Restore taboo X");
        System.out.println("'delete X' - Erase taboo X from existence");
        System.out.println("'todo <taboo>' - Add a simple taboo");
        System.out.println("'deadline <taboo> /by <time>' - Add a taboo with deadline");
        System.out.println("'event <taboo> /from <time> /to <time>' - Add a taboo with duration");
        System.out.println("'bye' - Leave this sacred space");
        System.out.println("<time> pattern: yyyy-MM-dd HHmm or MMM dd yyyy, h:mma");
    }

    /**
     * Displays the goodbye message when the user exits the application.
     */
    public void printGoodbye() {
        System.out.println("We part at the threshold of the impossible.");
        System.out.println("Remember: to live is to die continuously.");
        System.out.println("Goodbye.");
    }

    /**
     * Prints an error message to the console.
     *
     * @param message The error message to display.
     */
    public void printError(String message) {
        System.out.println(message);
    }

    /**
     * Prints a loading error message when tasks cannot be loaded from storage.
     */
    public void printLoadingError() {
        System.err.println(" The void resisted reading. Starting with an empty ledger.");
    }

    /**
     * Displays the entire task list to the user.
     *
     * @param taskList The list of tasks to display.
     */
    public void showTaskList(List<Task> taskList) {
        System.out.println(" Sacred Taboos in your ledger: ");
        if (taskList.isEmpty()) {
            System.out.println(" The void stares back at you. No truths dared spoken yet.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + "." + taskList.get(i).toString());
            }
        }
    }

    /**
     * Displays a confirmation message after a task has been added.
     *
     * @param task The task that was added.
     * @param totalCount The new total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int totalCount) {
        System.out.println(" Got it. I've added this taboo:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + totalCount + " taboos in your ledger.");
    }

    /**
     * Displays a confirmation message after a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param totalCount The new total number of tasks remaining.
     */
    public void showTaskDeleted(Task task, int totalCount) {
        System.out.println(" The void consumes it. Noted. I've removed this taboo:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + totalCount + " taboos in the list.");
    }

    /**
     * Displays a confirmation message after a task's completion status has been changed.
     *
     * @param task The task whose status was changed.
     * @param isDone true if the task was marked as done, false if marked as not done.
     */
    public void showTaskMarked(Task task, boolean isDone) {
        String action = isDone ? "profaned" : "restored to sacred";
        String reflection = isDone
                ? "Another ritual completed. Another boundary crossed."
                : "The task returns to its potential state. Unfinished, like all existence.";
        System.out.println(" The limit has been crossed! This truth is now " + action + ": ");
        System.out.println("   " + task.toString());
        System.out.println(" " + reflection);
    }

    /**
     * Displays the results of a search operation.
     * Shows all tasks that match the search criteria, or a message if none found.
     *
     * @param matches The list of tasks that matched the keyword.
     */
    public void showFound(List<Task> matches) {
        if (matches.isEmpty()) {
            System.out.println(" The void yields nothing. No matching taboos found.");
        } else {
            System.out.println(" Here are the matching taboos in your ledger:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matches.get(i).toString());
            }
        }
    }

    /**
     * Closes the scanner resource.
     * Should be called when the application is shutting down.
     */
    public void close() {
        scanner.close();
    }
}