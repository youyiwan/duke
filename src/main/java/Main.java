import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
public class Main {
    public static Task[] myTask = new Task[100];
    public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task> ();
    private static int taskCount = 0;
    public static void addTask(Task m){
        myTask[taskCount] = m;
        taskCount++;
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
            if (line.equalsIgnoreCase("bye")) // 1. Exit application
            {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(line.equalsIgnoreCase("list")) // 2. Print Task
            {
                for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet()){
                    System.out.println( entry.getKey() + "." + entry.getValue().booleanToString(entry.getValue().isDone)+ " " + entry.getValue().description.toString() );
                }

            }
            else if( line.length() >= 4 &&  line.substring(0,4).equalsIgnoreCase("mark") ){    // 3. mark task as done
                String[] words = line.split(" ");
                boolean isDone = true;

                // check if key exist if not prompt user to check the list again
                if(taskMap.containsKey(Integer.valueOf(words[1])))
                {
                    for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet()){
                        if(entry.getKey() == Integer.valueOf(words[1]) )
                        {
                            Task t = new Task(entry.getValue().description.toString(), isDone);
                            taskMap.put( Integer.valueOf(words[1]) , t);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(entry.getValue().markAsDone() + " " + entry.getValue().description.toString() );

                        }
                    }
                }
                else System.out.println("No such task, please check the list");
            }
            else if( line.length() >= 6 && line.substring(0,6).equalsIgnoreCase("unmark") ){ // 4. unmark task
                String[] words = line.split(" ");
                boolean isDone = false;

                // check if key exist if not prompt user to check the task again
                if(taskMap.containsKey(Integer.valueOf(words[1]))){
                    for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet()){
                        if(entry.getKey() == Integer.valueOf(words[1]) )
                        {
                            Task t = new Task(entry.getValue().description.toString(), isDone);
                            taskMap.put( Integer.valueOf(words[1]) , t);
                            System.out.println("OK, I've marked this task as not done yet");
                            System.out.println(entry.getValue().markAsNotDone() + " " + entry.getValue().description.toString() );

                        }
                    }
                }
                else System.out.println("No such task, please check the list");

            }
            else {
                 // 5. check for duplicate task
                for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet() ){
                    String str1 = entry.getValue().description.toString();
                    isSame = str1.equals(line);
                    if( isSame == true) ;
                    {
                        break;
                    }
                }
                if (isSame == false) { // 6. if false create new entry
                    Task t = new Task(line, isSame); // Create new object of Task class
                    addTask(t); // Add object to Task[]
                    taskMap.put(taskCount, t);
                    System.out.println("added: "+ line);
                } else System.out.println("Task already exist");
            }
        }

    }

}


