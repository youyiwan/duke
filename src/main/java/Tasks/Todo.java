package Tasks;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String markAsDone(){
        return "[T][X]";
    }

    public String markAsNotDone(){
        return "[T][ ]";
    }

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
            return markAsDone() + " " + super.toString() ;
        }
        else return markAsNotDone()  + " " + super.toString() ;
    }

    public static void createTodo(String line, boolean isSame){
        int dividerFirstSpace = line.indexOf(' ');
        String taskDescription = line.substring(dividerFirstSpace);
        Todo t = new Todo(taskDescription, isSame); // Create new object of Todo class
        TaskList.addTask(t); // Add object to Task[]
        TaskList.taskMap.put(TaskList.getTaskCount(), t);
        System.out.println(t.booleanToString(isSame));
    }
}
