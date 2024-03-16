package UI;
import java.util.Scanner;
public class EuanUI {

    public String myInput;
    public String getInput(){
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        myInput = line;
        return myInput;
    }

    public void greetings(){
        String name = "Euan";
        System.out.println("Hello! I'm " + name+"\n"+"What can I do for you?");
    }

    public void farewell(){
        System.out.println("Bye. Hope to see you again soon!");
    }

}
