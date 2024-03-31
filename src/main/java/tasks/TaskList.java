package tasks;

import java.util.HashMap;
import java.util.Objects;
/**
 * This class is the used to maintain and keep track of the entire task in the list.
 */
public class TaskList{

    public static tasks.Task[] myTask;
    public static HashMap<Integer, tasks.Task> taskMap;

    public static int taskCount = 0;
    /**
     * This method creates a new task list in the form of a hash map structure and is limited to only 100 tasks.
     */
    public static void createNewTaskList()
    {
        myTask = new tasks.Task[100];
        taskMap = new HashMap<Integer, tasks.Task> ();
    }

    /**
     * This method returns the total number of tasks in the list
     */
    public static int getTaskCount()
    {
        return taskCount;
    }
    /**
     * This method adds the task and increments the taskcount.
     */
    public static Task addTask(Task m){
        myTask[taskCount] = m;
        taskCount++;
        return myTask[taskCount];
    }

    public void updateTaskCount(int x)
    {
        taskCount=taskCount+x;
    }
    /**
     * This method loops through the task list and marks the task as done if found.
     */
    public void markTask(String line){
        String[] words = line.split(" ");
        boolean isDone = true;

        // check if key exist if not prompt user to check the list again
        if(taskMap.containsKey(Integer.valueOf(words[1])))
        {
            for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet()){
                if(entry.getKey().equals(Integer.valueOf(words[1])))
                {
                    System.out.println("Good job! I've marked this task as done ✅ :");
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
    /**
     * This method loops through the task list and unmarks the task as done if found.
     */
    public void unmarkTask(String line) {
        String[] words = line.split(" ");
        boolean isDone = false;
        // check if key exist if not prompt user to check the task again
        if(TaskList.taskMap.containsKey(Integer.valueOf(words[1]))){
            for (HashMap.Entry<Integer, Task> entry : TaskList.taskMap.entrySet()){
                if(entry.getKey().equals(Integer.valueOf(words[1])) )
                {
                    System.out.println("OK, I've marked this task as not done yet");
                    if(entry.getValue().markAsDone().startsWith("[D]")){   // Here you need to check if its deadline, event or ...
                        Deadline d = new Deadline( entry.getValue().getDescription(), isDone, entry.getValue().getBy());
                        TaskList.taskMap.put(entry.getKey(), d);
                        System.out.println(d);
                    }
                    else if (entry.getValue().markAsDone().startsWith("[E]")){
                        Event e = new Event( entry.getValue().getDescription(), isDone, entry.getValue().getFrom(), entry.getValue().getTo());
                        TaskList.taskMap.put(entry.getKey(), e);
                        System.out.println(e);
                    }
                    else {
                        Todo t = new Todo(entry.getValue().description, isDone);
                        TaskList.taskMap.put( Integer.valueOf(words[1]) , t);
                        System.out.println(t);
                    }
                }
            }
        }
        else System.out.println("No such task, please check the list");

    }
    /**
     * This method loops through the task list and deletes the task as done if found.
     */
    public void deleteTask(String line){
        String[] words = line.split(" ");
        System.out.println("Noted. I've removed this task");
        for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet()) {
            if (entry.getKey().equals(Integer.valueOf(words[1]))) {
                System.out.println(entry.getValue());
            }
        }
        int deleteKey = Integer.parseInt(words[1]);
        int initialTaskSize = taskMap.size();

        if (deleteKey == initialTaskSize){   // delete last task in the list
            taskMap.remove(initialTaskSize);
            updateTaskCount(-1); // decrement task count
        }
        else if (deleteKey == 1 && initialTaskSize == 2) // Scenario where you delete a task that is not the last on the list resulting in only 1 task left
        {
            taskMap.put(1, taskMap.get(initialTaskSize));
//            System.out.println(taskMap.get(taskMap.size()));
            for (int i=2; i<=100; i++){
                taskMap.remove(i);
            }
            updateTaskCount(-1); // decrement task count

        }
        else { // Assuming order is not important, last in list will take the rank of the deleted key
            taskMap.remove(deleteKey);
            taskMap.put(Integer.parseInt(words[1]), taskMap.get(taskMap.size()+1)); // take last task and put  into deleted entry
            taskMap.remove(initialTaskSize);
            updateTaskCount(-1); // decrement task count
        }
//        TaskList.taskMap.remove(Integer.valueOf(words[1])); // remove task
//        updateTaskCount(-1); // decrement task count
        System.out.println("Now you have " + getTaskCount() + " tasks in the list.");
        /// Update Keys so that the list is in running order ///




    }
    /**
     * This method loops through the task list and checks for duplicates.
     */
    public boolean checkDuplicates(String line, boolean isSame){
        int dividerFirstSpace = line.indexOf(' ');
        for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet() ){
            String str1 = entry.getValue().description;
            isSame = str1.equals(line.substring(dividerFirstSpace));
            if (isSame){
                break;
            }
        }
        return isSame;
    }
    /**
     * This method loops through the task and find tasks that are associated with description is entered by the user.
     */
    public void findTask(String line){
        String[] words = line.split(" ");
        for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet()) {

            String description = String.valueOf(entry.getValue());

            if (description.contains(words[1])) {
                System.out.println(entry.getKey() + "." + entry.getValue().toString());
            }
        }
    }
    /**
     * This method loops through the task and find a deadline task and prints a deadline task if the deadline date matches the date entered by user.
     */
    public void findDeadline(String line){
        String findDate = line.substring(5);
        for (HashMap.Entry<Integer, tasks.Task> entry : TaskList.taskMap.entrySet()) {

            String description = String.valueOf(entry.getValue());

            if (description.contains("by")) {
                int dividerBy = description.indexOf("by: ");
                String tempCheckDate = description.substring(dividerBy).replace("by: ", "");
                String checkDate = tempCheckDate.replace(")", "");
                if(Objects.equals(findDate, checkDate  )){
                    System.out.println(entry.getKey() + "." + entry.getValue().toString());
                }
            }

        }
    }

}
