package Tasks;

import Parser.DatesTimes;

public class Event extends Task {


    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from=from;
        this.to = to;
    }

    @Override
    public String markAsDone(){
        return "[E][X]";
    }

    public String markAsNotDone(){
        return "[E][ ]";
    }

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
            return markAsDone() + super.toString() + " (from: " + from + " to: " + to + ")";
        }
        else return markAsNotDone() + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static void createEvent(String line, boolean isSame){
        int dividerFirstSpace = line.indexOf(' ');
        int dividerFrom = line.indexOf("/from ");
        int dividerTo = line.indexOf("/to ");
        String taskDescription = line.substring(dividerFirstSpace, dividerFrom);
        String from = line.substring(dividerFrom, dividerTo).replace("/from ", "");;
        String to = line.substring(dividerTo).replace("/to ", "");
        Event e = new Event( taskDescription, isSame, DatesTimes.getDateTime(from), DatesTimes.getDateTime(to) ); // Create new object of Deadline class
        TaskList.addTask(e); // Add object to Task[]
        TaskList.taskMap.put(TaskList.getTaskCount(), e); // store deadline object in map
        System.out.println(e.booleanToString(isSame));
    }
}
