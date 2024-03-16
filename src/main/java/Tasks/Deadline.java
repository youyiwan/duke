package Tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }



    @Override
    public String markAsDone(){
        return "[D][X]";
    }

    public String markAsNotDone(){
        return "[D][ ]";
    }

    public String toString() {
        if (isDone)
        {
            return markAsDone() + description + " (by: " + by + ")";
        }
        else return markAsNotDone() + description + " (by: " + by + ")";
    }

    public String getBy(){
        return by;
    }

    public String booleanToString(boolean isDone) {

        if (isDone)
        {
            return markAsDone() + super.toString() + " (by: " + by + ")";
        }
        else return markAsNotDone() + super.toString() + " (by: " + by + ")";
    }

    public static void createDeadline(String line, boolean isSame){
        int dividerFirstSpace = line.indexOf(' ');
        int dividerBy = line.indexOf("/by ");
        String taskDescription = line.substring(dividerFirstSpace, dividerBy);
        String byDescription = line.substring(dividerBy).replace("/by ", "");
        Deadline d = new Deadline( taskDescription, isSame, byDescription ); // Create new object of Deadline class
        TaskList.addTask(d); // Add object to Task[]
        TaskList.taskMap.put(TaskList.getTaskCount(), d); // store deadline object in map

        System.out.println(d.booleanToString(isSame));
    }
}
