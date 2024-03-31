package tasks;

import exceptions.EuanExceptions;
import parser.DatesTimes;
/**
 * Event class is a specific class that inherits from the Task class
 * It inherits two main attributes: (1) general description of the task (2) whether the task is mark as done or not
 * Event class has a time frame element indicated by the string 'to' and 'from'. This indicates the duration of this specific tasks in this class
 * createEvent is a method to create event task with 4 attributes, description, isDone, to and from
 */

public class Event extends Task {

    static EuanExceptions myEuanExceptions = new EuanExceptions();

    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from=from;
        this.to = to;
    }
    /**
     * Overwrites the method markAsDone() from Task class. From "[X]" to "[E][X]" .
     */
    @Override
    public String markAsDone(){
        return "[E][X]";
    }
    /**
     * Overwrites the method markAsNotDone() from Task class. From "[ ]" to "[E][ ]" .
     */
    public String markAsNotDone(){
        return "[E][ ]";
    }
    /**
     * Concatenates all the attributes of Deadline task.
     */
    public String toString() {
        if (isDone)
        {
            return markAsDone() + description + " (from: " + from + " to: " + to + ")";
        }
        else return markAsNotDone() + description + " (from: " + from + " to: " + to + ")";
    }

    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }


    public String booleanToString(boolean isDone) {

        if (isDone)
        {
            return markAsDone() + super.toString() + "(from: " + from + " to: " + to + ")";
        }
        else return markAsNotDone() + super.toString() + "(from: " + from + " to: " + to + ")";
    }
    /**
     * Creates and returns event task.
     */
    public static String createEvent(String line, boolean isSame){

        try {
            if ( !line.contains("/from") || !line.contains("/to")){
                throw new EuanExceptions();
            }
            else {
                int dividerFirstSpace = line.indexOf(' ');
                int dividerFrom = line.indexOf("/from ");
                int dividerTo = line.indexOf("/to ");
                String taskDescription = line.substring(dividerFirstSpace, dividerFrom);
                String from = line.substring(dividerFrom, dividerTo).replace("/from ", "");;
                String to = line.substring(dividerTo).replace("/to ", "");
                if(!from.matches("\\d{2}:\\d{2}")){
                    System.out.println("Please enter the '/from' time in HH:mm format");
                    return "";
                }
                else {
                    if(!to.matches("\\d{2}:\\d{2}")){
                        System.out.println("Please enter the '/to' time in HH:mm format");
                        return "";
                    }
                }
                Event e = new Event( taskDescription, isSame, DatesTimes.getDateTime(from), DatesTimes.getDateTime(to) ); // Create new object of Deadline class
                TaskList.addTask(e); // Add object to Task[]
                TaskList.taskMap.put(TaskList.getTaskCount(), e); // store deadline object in map
                System.out.println(e.booleanToString(isSame));
                return e.booleanToString(isSame);
            }
        } catch (EuanExceptions e){
            myEuanExceptions.missingFromToClause();
        }

        return "Failed to add event task";

    }
}
