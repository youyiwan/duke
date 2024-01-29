import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String name = "Euan";

        System.out.println("Hello! I'm " + name+"\n"+"What can I do for you?");

        while(true)
        {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else System.out.println(line);
        }



    }

}


