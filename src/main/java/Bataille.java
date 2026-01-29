import java.util.Scanner;

public class Bataille {
    private static final int MAX_ITEMS = 100;
    private static String[] storedItems = new String[MAX_ITEMS];
    private static int count = 0;

    public static void printInput(String[] input) {
        System.out.println("\n____________________________________________________________");
        if (count == 0) {
            System.out.println("No items stored yet.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(" " + (i + 1) + ". " + storedItems[i]);
            }
            System.out.println("____________________________________________________________");
        }
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
            String input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("\n____________________________________________________________");
                System.out.println("We part at the threshold of the impossible.");
                System.out.println("Remember: to live is to die continuously.");
                System.out.println("Goodbye.");
                System.out.println("____________________________________________________________");
                active = false;
            } else if (input.equalsIgnoreCase("list")) {
                printInput(storedItems);
            } else {
                storedItems[count] = input;
                count ++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        in.close();
    }
}