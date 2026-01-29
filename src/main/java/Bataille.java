import java.util.Scanner;

public class Bataille {
    private static final int MAX_ITEMS = 100;
    private static String[] storedItems = new String[MAX_ITEMS];
    private static boolean[] completed = new boolean[MAX_ITEMS];
    private static int count = 0;

    static {
        for (int i = 0; i < MAX_ITEMS; i ++) {
            completed[i] = false;
        }
    }
    public static void printInput() {
        System.out.println("\n____________________________________________________________");
        System.out.println(" Sacred Taboos in your ledger: ");
        if (count == 0) {
            System.out.println(" The void stares back at you. No truths dared spoken yet.");
        } else {
            for (int i = 0; i < count; i++) {
                String status = completed[i] ? "[X]" : "[ ]";
                System.out.println(" " + (i + 1) + "." + status + storedItems[i]);
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
        completed[index - 1] = done;
        String status = done ? "[X]" : "[ ]";
        String action = done ? "profaned" : "restored to sacred";
        String philosophical = done ?
                "Another ritual completed. Another boundary crossed." :
                "The task returns to its potential state. Unfinished, like all existence.";

        System.out.println("\n____________________________________________________________");
        System.out.println(" The limit has been crossed! This truth is now " + action + ": ");
        System.out.println("   " + status + " " + storedItems[index - 1]);
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
                    storedItems[count] = input;
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