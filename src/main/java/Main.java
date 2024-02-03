import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static List[] mylist = new List[1000];
    private static int listCount = 0;
    public static void addlist(List m){
        mylist[listCount] = m;
        listCount++;
    }
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
            else if(line.equalsIgnoreCase("list"))
            {
                for (int i=0; i<mylist.length; i++) {
                    if(mylist[i] ==null) break;
                    System.out.println((i+1)+". "+mylist[i].toString());
                }
            }
            else {
                List l = new List(line); // Create new object of list class
                addlist(l); // Add objec to list[]
                System.out.println("added: "+ line);
            }
        }

    }

}


