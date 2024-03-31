package tasks;

import exceptions.EuanExceptions;
import parser.DatesTimes;

import java.util.HashMap;
/**
 * Deadline class is a specific class that inherits from the Task class
 * It inherits two main attributes: (1) general description of the task (2) whether the task is mark as done or not
 * Deadline class has a time frame element indicated by the string 'by'. This indicates the deadline of this specific tasks in this class
 * createDeadline is a method to create deadline task with 3 attributes, description, isDone and by
 * checkDeadline checks the deadline for all tasks in this class
 */
public class Deadline extends Task {

    static EuanExceptions myEuanExceptions = new EuanExceptions();
    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }


    /**
     * Overwrites the method markAsDone() from Task class. From "[X]" to "[D][X]" .
     */
    @Override
    public String markAsDone(){
        return "[D][X]";
    }
    /**
     * Overwrites the method markAsNotDone() from Task class. From "[ ]" to "[D][ ]" .
     */
    public String markAsNotDone(){
        return "[D][ ]";
    }
    /**
     * Concatenates all the attributes of Deadline task.
     */
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
    /**
     * Creates and returns Deadline task.
     */
    public static String createDeadline(String line, boolean isSame){

        try{
            if ( !line.contains("/by")){
                throw new EuanExceptions();
            }
            else {
                int dividerFirstSpace = line.indexOf(' ');
                int dividerBy = line.indexOf("/by ");
                String taskDescription = line.substring(dividerFirstSpace, dividerBy);
                String byDescription = line.substring(dividerBy).replace("/by ", "");
                if(!byDescription.matches("\\d{4}-\\d{2}-\\d{2}")){
                    System.out.println("Please enter the deadline in yyyy-mm-dd format");
                    return "";
                }
                System.out.println(DatesTimes.getInitialReminder(byDescription));
                Deadline d = new Deadline( taskDescription, isSame, DatesTimes.getDate(byDescription) ); // Create new object of Deadline class
                TaskList.addTask(d); // Add object to Task[]
                TaskList.taskMap.put(TaskList.getTaskCount(), d); // store deadline object in map

                System.out.println(d.booleanToString(isSame));
                return d.booleanToString(isSame);
            }
        } catch (EuanExceptions e) {
            myEuanExceptions.missingByClause();
        }

        return "Failed to add deadline task";
    }
        /**
     * Serves as a reminder for the deadline task and prints the time left till deadline date.
     */
    public static void checkDeadline(){
        System.out.println("-------------------------------------- Start of Reminder ------------------------------------- ");
        System.out.println("\n");
        for (HashMap.Entry<Integer, tasks.Task> entry : TaskList.taskMap.entrySet()) {

            String description = String.valueOf(entry.getValue());

            if (description.contains("by")) {
                int dividerBy = description.indexOf("by: ");
                String tempCheckDate = description.substring(dividerBy).replace("by: ", "");
                String checkDate = tempCheckDate.replace(")", "");
                System.out.println("Task #" + entry.getKey() + ": " + DatesTimes.getRegularReminder(checkDate)); // Date format here is in MMM DD YYYY
            }

        }
        System.out.println("\n");
        System.out.println("--------------------------------------- End of Reminder -------------------------------------- ");
    }


}
