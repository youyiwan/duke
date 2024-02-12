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
                    System.out.println( entry.getKey() + "." + entry.getValue().toString());
                }

            }
            else if( line.length() >= 4 &&  line.substring(0,4).equalsIgnoreCase("mark") ){    // 3. mark task as done
                String[] words = line.split(" ");
                boolean isDone = true;

                // check if key exist if not prompt user to check the list again
                if(taskMap.containsKey(Integer.valueOf(words[1])))
                {
                    for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet()){
                        if(entry.getKey().equals(Integer.valueOf(words[1])))
                        {
                            System.out.println("Nice! I've marked this task as done:");
                            if(entry.getValue().markAsDone().startsWith("[D]")){   // Here you need to check if its deadline, event or todo
                                Deadline d = new Deadline( entry.getValue().getDescription(), isDone, entry.getValue().getBy());
                                taskMap.put(entry.getKey(), d);
                                System.out.println(d);
                            }
                            else if (entry.getValue().markAsDone().startsWith("[E]")) {
                                Event e = new Event( entry.getValue().getDescription(), isDone, entry.getValue().getFrom(), entry.getValue().getTo());
                                taskMap.put(entry.getKey(), e);
                                System.out.println(e);
                            }
                            else {
                                Todo t = new Todo(entry.getValue().description, isDone);
                                taskMap.put( entry.getKey() , t);
                                System.out.println(t);
                            }
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
                        if(entry.getKey().equals(Integer.valueOf(words[1])) )
                        {
                            System.out.println("OK, I've marked this task as not done yet");
                            if(entry.getValue().markAsDone().startsWith("[D]")){   // Here you need to check if its deadline, event or ...
                                Deadline d = new Deadline( entry.getValue().getDescription(), isDone, entry.getValue().getBy());
                                taskMap.put(entry.getKey(), d);
                                System.out.println(d);
                            }
                            else if (entry.getValue().markAsDone().startsWith("[E]")){
                                Event e = new Event( entry.getValue().getDescription(), isDone, entry.getValue().getFrom(), entry.getValue().getTo());
                                taskMap.put(entry.getKey(), e);
                                System.out.println(e);
                            }
                            else {
                                Todo t = new Todo(entry.getValue().description, isDone);
                                taskMap.put( Integer.valueOf(words[1]) , t);
                                System.out.println(t);
                            }
                        }
                    }
                }
                else System.out.println("No such task, please check the list");
            }
            else {
                 // 5. check for duplicate task
                for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet() ){
                    String str1 = entry.getValue().description;
                    isSame = str1.equals(line);
                    if( isSame ) ;
                    {
                        break;
                    }
                }
                if (!isSame) { // 6. if false create new entry
                    int dividerFirstSpace = line.indexOf(' ');
                    System.out.println("Got it. I've added this task:");
                    if (line.substring(0,dividerFirstSpace).equalsIgnoreCase("deadline"))
                    {
                        int dividerBy = line.indexOf("/by ");
                        String taskDescription = line.substring(dividerFirstSpace, dividerBy);
                        String byDescription = line.substring(dividerBy).replace("/by ", "");
                        Deadline d = new Deadline( taskDescription, isSame, byDescription ); // Create new object of Deadline class
                        addTask(d); // Add object to Task[]
                        taskMap.put(taskCount, d); // store deadline object in map

                        System.out.println(d.booleanToString(isSame));
                    }
                    else if (line.substring(0,dividerFirstSpace).equalsIgnoreCase("event"))
                    {
                        int dividerFrom = line.indexOf("/from ");
                        int dividerTo = line.indexOf("/to ");
                        String taskDescription = line.substring(dividerFirstSpace, dividerFrom);
                        String from = line.substring(dividerFrom, dividerTo).replace("/from ", "");;
                        String to = line.substring(dividerTo).replace("/to ", "");
                        Event e = new Event( taskDescription, isSame, from, to ); // Create new object of Deadline class
                        addTask(e); // Add object to Task[]
                        taskMap.put(taskCount, e); // store deadline object in map
                        System.out.println(e.booleanToString(isSame));
                    }
                    else {
                        String newLine = line.substring(dividerFirstSpace);
                        Todo t = new Todo(newLine, isSame); // Create new object of Todo class
                        addTask(t); // Add object to Task[]
                        taskMap.put(taskCount, t);
                        System.out.println(t.booleanToString(isSame));
                    }
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else System.out.println("Task already exist");
            }
        }

    }

}


