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
            return markAsDone() + description + " (from: " + from + "to: " + to + ")";
        }
        else return markAsNotDone() + description + " (from: " + from + "to: " + to + ")";
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
            return markAsDone() + super.toString() + " (from: " + from + "to: " + to + ")";
        }
        else return markAsNotDone() + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
