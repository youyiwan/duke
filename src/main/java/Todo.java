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
}
