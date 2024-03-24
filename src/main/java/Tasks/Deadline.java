/**
 * Deadline class is a specific class that inherits from the Task class
 * It inherits two main attributes: (1) general description of the task (2) whether the task is mark as done or not
 * Deadline class has a time frame element indicated by the string 'by'. This indicates the deadline of this specific tasks in this class
 * createDeadline is a method to create deadline task with 3 attributes, description, isDone and by
 * checkDeadline checks the deadline for all tasks in this class
 */

package Tasks;

import Parser.DatesTimes;

import java.util.HashMap;

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
        System.out.println(DatesTimes.getInitialReminder(byDescription));
        Deadline d = new Deadline( taskDescription, isSame, DatesTimes.getDate(byDescription) ); // Create new object of Deadline class
        TaskList.addTask(d); // Add object to Task[]
        TaskList.taskMap.put(TaskList.getTaskCount(), d); // store deadline object in map

        System.out.println(d.booleanToString(isSame));
        return d.booleanToString(isSame);
    }

    public static void checkDeadline(){
        System.out.println("-------------------------------------- Start of Reminder ------------------------------------- ");
        System.out.println(" ");
        for (HashMap.Entry<Integer, Tasks.Task> entry : TaskList.taskMap.entrySet()) {

            String description = String.valueOf(entry.getValue());

            if (description.contains("by")) {
                int dividerBy = description.indexOf("by: ");
                String tempCheckDate = description.substring(dividerBy).replace("by: ", "");
                String checkDate = tempCheckDate.replace(")", "");
                System.out.println("Task #" + entry.getKey() + ": " + DatesTimes.getRegularReminder(checkDate)); // Date format here is in MMM DD YYYY
            }

        }
        System.out.println(" ");
        System.out.println("--------------------------------------- End of Reminder -------------------------------------- ");
    }


}
