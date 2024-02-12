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
}
