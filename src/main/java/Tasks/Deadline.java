/**
 * Deadline class is a specific class that inherits from the Task class
 * It inherits two main attributes: (1) general description of the task (2) whether the task is mark as done or not
 * Deadline class has a time frame element indicated by the string 'by'. This indicates the deadline of this specific tasks in this class
 */

package Tasks;

import Parser.DatesTimes;

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
            return markAsDone() + super.toString() + "(by: " + by + ")";
        }
        else return markAsNotDone() + super.toString() + "(by: " + by + ")";
    }

    public static String createDeadline(String line, boolean isSame){
        int dividerFirstSpace = line.indexOf(' ');
        int dividerBy = line.indexOf("/by ");
        String taskDescription = line.substring(dividerFirstSpace, dividerBy);
        String byDescription = line.substring(dividerBy).replace("/by ", "");
        Deadline d = new Deadline( taskDescription, isSame, DatesTimes.getDate(byDescription) ); // Create new object of Deadline class
        TaskList.addTask(d); // Add object to Task[]
        TaskList.taskMap.put(TaskList.getTaskCount(), d); // store deadline object in map

        System.out.println(d.booleanToString(isSame));
        return d.booleanToString(isSame);
    }


}
