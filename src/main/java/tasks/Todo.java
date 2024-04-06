package tasks;
/**
 * Todo class is a specific class that inherits from the Task class
 * It inherits two main attributes: (1) general description of the task (2) whether the task is mark as done or not
 * Note that Todo class does not have a time frame element indicating that this particular class of task does not have an urgency.
 */


public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * Overwrites the method markAsDone() from Task class. From "[X]" to "[T][X]" .
     * @return the task subclass todo symbol and mark as done symbol, from "[X]" to "[T][X]".
     */
    @Override
    public String markAsDone(){
        return "[T][X]";
    }
    /**
     * Overwrites the method markAsNotDone() from Task class. From "[ ]" to "[T][ ]" .
     * @return the task subclass todo symbol and not done symbol, from "[ ]" to "[T][ ]".
     */
    public String markAsNotDone(){
        return "[T][ ]";
    }
    /**
     * Concatenates all the attributes of Deadline task.
     * @return the task subclass and mark as done symbol and description
     */
    public String toString() {
        if (isDone)
        {
            return markAsDone()  + description;
        }
        else return markAsNotDone()  + description;
    }
    public String booleanToString(boolean isDone) {

        if (isDone)
        {
            return markAsDone()  + super.toString() ;
        }
        else return markAsNotDone()   + super.toString() ;
    }
    /**
     * Creates and returns todo task.
     * @return the todo object.
     */
    public static String createTodo(String line, boolean isSame){
        int dividerFirstSpace = line.indexOf(' ');
        String taskDescription = line.substring(dividerFirstSpace);
        Todo t = new Todo(taskDescription, isSame); // Create new object of Todo class
        TaskList.addTask(t); // Add object to Task[]
        TaskList.taskMap.put(TaskList.getTaskCount(), t);
        System.out.println(t.booleanToString(isSame));
        return t.booleanToString(isSame);
    }
}
