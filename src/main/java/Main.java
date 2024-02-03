import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
public class Main {
    public static List[] mylist = new List[1000];
    public static HashMap<Integer, List> listMap = new HashMap<Integer, List> ();
    private static int listCount = 0;
    public static void addlist(List m){
        mylist[listCount] = m;
        listCount++;
    }
    public static void main(String[] args) {
        String line;
        boolean isSame = false;
        Scanner in = new Scanner(System.in);
        String name = "Euan";
        System.out.println("Hello! I'm " + name+"\n"+"What can I do for you?");
        while(true)
        {
            line = in.nextLine();
            isSame = false;
            if (line.equalsIgnoreCase("bye")) // Exit application
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(line.equalsIgnoreCase("list")) // Print list
            {
                for (HashMap.Entry<Integer,List> entry : listMap.entrySet()){
                    System.out.println( entry.getKey() + "." + entry.getValue().mark.toString() + " " + entry.getValue().description.toString() );
                }

            }
            else if( line.length() >= 4 &&  line.substring(0,4).equalsIgnoreCase("mark") ){    // mark as done
                String[] words = line.split(" ");
                String done = "[X]";

                // check if key exist if not prompt user to check the list again
                if(listMap.containsKey(Integer.valueOf(words[1])))
                {
                    for (HashMap.Entry<Integer,List> entry : listMap.entrySet()){
                        if(entry.getKey() == Integer.valueOf(words[1]) )
                        {
                            List l = new List(entry.getValue().description.toString(), done);
                            listMap.put( Integer.valueOf(words[1]) , l);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(entry.getValue().mark.toString() + " " + entry.getValue().description.toString() );

                        }
                    }
                }
                else System.out.println("No such task, please check the list");
            }
            else if( line.length() >= 6 && line.substring(0,6).equalsIgnoreCase("unmark") ){ // unmark list
                String[] words = line.split(" ");
                String notDone = "[ ]";

                // check if key exist if not prompt user to check the list again
                if(listMap.containsKey(Integer.valueOf(words[1]))){
                    for (HashMap.Entry<Integer,List> entry : listMap.entrySet()){
                        if(entry.getKey() == Integer.valueOf(words[1]) )
                        {
                            List l = new List(entry.getValue().description.toString(), notDone);
                            listMap.put( Integer.valueOf(words[1]) , l);
                            System.out.println("OK, I've marked this task as not done yet");
                            System.out.println(entry.getValue().mark.toString() + " " + entry.getValue().description.toString() );

                        }
                    }
                }
                else System.out.println("No such task, please check the list");

            }
            else {
                 // check for duplicate
                for (HashMap.Entry<Integer,List> entry : listMap.entrySet() ){
                    String str1 = entry.getValue().description.toString();
                    isSame = str1.equals(line);
                    if( isSame == true) ;
                    {
                        break;
                    }
                }
                if (isSame == false) { // if false create new entry
                    List l = new List(line, "[ ]"); // Create new object of list class
                    addlist(l); // Add object to list[]
                    listMap.put(listCount, l);
                    System.out.println("added: "+ line);
                } else System.out.println("Task already exist");
            }
        }

    }

}


