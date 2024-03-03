import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static Task[] myTask = new Task[100];
    public static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task> ();
    private static int taskCount = 0;
    public static void addTask(Task m){
        myTask[taskCount] = m;
        taskCount++;
    }

    public static void main(String[] args) {
        ArrayList keyWords = new ArrayList();
        keyWords.add("Bye");
        keyWords.add("bye");
        keyWords.add("List");
        keyWords.add("list");
        keyWords.add("Mark");
        keyWords.add("mark");
        keyWords.add("Unmark");
        keyWords.add("unmark");
        keyWords.add("Delete");
        keyWords.add("delete");

        ArrayList<Integer> key = new ArrayList<Integer>(100);
        for (int i = 1; i <= 100; i++)
        {
            key.add(i);
        }

        int error = 0;
        String line;
        boolean isSame = false;
        Scanner in = new Scanner(System.in);
        String name = "Euan";
        System.out.println("Hello! I'm " + name+"\n"+"What can I do for you?");
            while(true)
            {
                line = in.nextLine();
                String newline = line.trim();
                isSame = false;
                ////// EXCEPTION HANDLING //////
                    try {
                        if(line.isEmpty()) {
                            error = 1;
                            throw new EuanExceptions();
                        }
                        else if ( !keyWords.contains( line ) ){
                            if ( line.length() <= 4){
                                error = 2;
                                throw new EuanExceptions();
                            }
                        }
                    }
                    catch (EuanExceptions e) {
                        if (error == 1){
                            System.out.println("Congratulations! You have created in an empty task. There is nothing for you to do.");
                            System.out.println("If this is a typo. Please try again.");
                            continue;
                        }
                        else if (error == 2){
                            System.out.println("Sorry, I do not understand.");
                            continue;
                        }
                    }
                ////// END OF EXCEPTION HANDLING //////
                if (line.equalsIgnoreCase("bye")) // 1. Exit application
                {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                else if(line.equalsIgnoreCase("list")) // 2. Print Task & Write to File
                {
                    File f = new File("data/Euan.txt");
                    System.out.println("full path: " + f.getAbsolutePath());
                    System.out.println("file exists?: " + f.exists());
                    try {
                        BufferedWriter bf = new BufferedWriter(new FileWriter(f));
                        for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet()) {
                            System.out.println(entry.getKey() + "." + entry.getValue().toString());
                                bf.write(entry.getKey() + "." + entry.getValue().toString());
                                bf.newLine();
                        }
                        bf.close();
                    }
                    catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
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
                else if( line.length() >= 6 && line.substring(0,6).equalsIgnoreCase("delete") ){ // 5. delete task
                    String[] words = line.split(" ");
                    System.out.println("Noted. I've removed this task");
                    for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet()) {
                        if (entry.getKey().equals(Integer.valueOf(words[1]))) {
                            System.out.println(entry.getValue());
                        }
                    }
                    taskMap.remove(Integer.valueOf(words[1]));
                    taskCount--;
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    /// Update Keys so that the list is in running order ///
                    if (taskMap.size() == 1)
                    {
                        taskMap.put(1, taskMap.get(2));
                        taskMap.remove(2);
                    }
                    else { // Assuming order is not important, last in list will take the rank of the deleted key
                        taskMap.put(Integer.parseInt(words[1]), taskMap.get(taskMap.size()+1));
                        taskMap.remove(taskMap.size());
                    }
                    /////////////////// End of Update Keys ////////////////
                }
                else {
                     // 6. check for duplicate task
                    for (HashMap.Entry<Integer,Task> entry : taskMap.entrySet() ){
                        String str1 = entry.getValue().description;
                        isSame = str1.equals(line);
                        if( isSame ) ;
                        {
                            break;
                        }
                    }
                    if (!isSame) { // 7. if false create new entry
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
                            String taskDescription = line.substring(dividerFirstSpace);
                            Todo t = new Todo(taskDescription, isSame); // Create new object of Todo class
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


