/**
 * Task class is an abstract class
 * It has two main attributes: (1) general description of the task (2) whether the task is mark as done or not
 */

package tasks;

public abstract class Task {
    public String description;
    public boolean isDone;

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

    public String getBy() {
        return "to be override in Deadline class";
    }
    public String getFrom(){
        return "to be override in Event class";
    }
    public String getTo(){
        return "to be override in Event class";
    }
    @Override
    public String toString() {
        return getDescription();
    }

    public boolean toBoolean() {
        return isDone;
    }

    public String booleanToString(boolean isDone) {

        return (isDone ? markAsDone() +  getDescription()
                : markAsNotDone() +  getDescription());
    }


}

