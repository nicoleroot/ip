import java.util.Scanner;

public class Bataille {
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
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" " + input);
                System.out.println("____________________________________________________________");
            }
        }
        in.close();
    }
}