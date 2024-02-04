import java.util.Map;
import java.util.HashMap;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone)  {     // Constructor
        this.description = description;
        this.isDone = isDone;
    }

    public String markAsDone(){
        return "[X]";
    }

    public String markAsNotDone(){
        return "[ ]";
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return getDescription();
    }

    public boolean toBoolean() {
        return isDone;
    }

    public String booleanToString(boolean isDone) {
        return (isDone ? "[X]" : "[ ]");
    }


}

