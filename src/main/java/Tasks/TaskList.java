package Tasks;

import java.util.HashMap;

public class TaskList{

    public static Tasks.Task[] myTask;
    public static HashMap<Integer, Tasks.Task> taskMap;

    public static int taskCount = 0;
    public static void createNewTaskList()
    {
        myTask = new Tasks.Task[100];
        taskMap = new HashMap<Integer, Tasks.Task> ();
    }


    public static int getTaskCount()
    {
        return taskCount;
    }

    public static Task addTask(Task m){
        myTask[taskCount] = m;
        taskCount++;
        return myTask[taskCount];
    }

    public void updateTaskCount(int x)
    {
        taskCount=taskCount+x;
    }

    public void markTask(String line){
        String[] words = line.split(" ");
        boolean isDone = true;

        // check if key exist if not prompt user to check the list again
        if(taskMap.containsKey(Integer.valueOf(words[1])))
        {
            for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet()){
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

    public void deleteTask(String line){
        String[] words = line.split(" ");
        System.out.println("Noted. I've removed this task");
        for (HashMap.Entry<Integer, Task> entry : taskMap.entrySet()) {
            if (entry.getKey().equals(Integer.valueOf(words[1]))) {
                System.out.println(entry.getValue());
            }
        }
        TaskList.taskMap.remove(Integer.valueOf(words[1]));
        updateTaskCount(-1);
        System.out.println("Now you have " + getTaskCount() + " tasks in the list.");
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
    }

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

}
