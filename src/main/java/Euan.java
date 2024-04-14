import exceptions.EuanExceptions;
import parser.DatesTimes;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;
import ui.EuanUI;

public class Euan {


    static TaskList myTaskList = new TaskList();
    static EuanUI myEuanUI = new EuanUI();
    static EuanExceptions myEuanExceptions = new EuanExceptions();
    static Storage myStorage = new Storage();


    public static void main(String[] args) {
        String logo = " _________                                    \n"
                + "| _______|___    ___     _______    ____________\n"
                + "| |_____  | |    | |    / -----\\\\   | |      | | \n"
                + "|  ____|  | |    | |   / /      \\\\  | |      | | \n"
                + "| |_______| |____| |__/ /________\\\\ | |      | | \n"
                + "|_________|________|___\\\\________/\\\\|_|      |_| \n";
        System.out.println(logo);

        myTaskList.createNewTaskList();
        EuanUI.createKeywords();

        int error = 0;
        String line;
        boolean isSame = false;

        myEuanUI.greetings();

        while (true) {

            line = myEuanUI.getInput();
            isSame = false;
            EuanExceptions.checkForExceptions(line);

            if (line.equalsIgnoreCase("bye")) {// 1. Exit application
                EuanUI.farewell();
                break;
            }
            //////// Start of Print & Save BLOCK /////////
            while (line.contains("list") || line.contains("save")) {
                String singleCommandline = line.substring(0, 4).toLowerCase();
                switch (singleCommandline) {
                    case ("list"):
                        myStorage.printlist();
                        break;
                    case ("save"):
                        myStorage.save();
                    default:
                        break;
                }
                line = myEuanUI.getInput();
                EuanExceptions.checkForExceptions(line);
                singleCommandline = line.toLowerCase();
            }
            //////// End of Print & Save BLOCK /////////

            ////// START OF CHAT BOT INTERACTION //////
            if (line.length() >= 4 && line.substring(0, 4).equalsIgnoreCase("find")) {    // 3a. mark task as done
                String getDigits = line.substring(9);
                String getMth = line.substring(5, 8).toLowerCase();
                DatesTimes.createMonth();
                ;
                if (DatesTimes.monthList.contains(getMth) && getDigits.matches("\\d{2} \\d{4}")) { // Find by deadline
                    myTaskList.findDeadline(line);
                } else myTaskList.findTask(line); // Find by task
            } else if (line.length() >= 4 && line.substring(0, 4).equalsIgnoreCase("mark")) {    // 3b. mark task as done
                myTaskList.markTask(line);
            } else if (line.length() >= 6 && line.substring(0, 6).equalsIgnoreCase("unmark")) { // 4. unmark task
                myTaskList.unmarkTask(line);
            } else if (line.length() >= 6 && line.substring(0, 6).equalsIgnoreCase("delete")) { // 5. delete task
                myTaskList.deleteTask(line);
            } else if (line.contains("todo") || line.contains("event") || line.contains("deadline")) { // 6. Check for duplicate task
                if (myTaskList.checkDuplicates(line, isSame)) {
                    System.out.println("Task already exist");
                } else { // 7. if false create new entry
                    int dividerFirstSpace = line.indexOf(' ');
                    if (line.substring(0, dividerFirstSpace).equalsIgnoreCase("deadline")) {
                        String result = Deadline.createDeadline(line, isSame);
                        if (result.isEmpty()) {
                            System.out.println("No task added");
                        } else if (result.equals("Failed to add deadline task")) {
                            System.out.println(result);
                        } else {
                            System.out.println("Got it. I've added this task:");
                        }
                    } else if (line.substring(0, dividerFirstSpace).equalsIgnoreCase("event")) {
                        String result = Event.createEvent(line, isSame);
                        if (result.isEmpty()) {
                            System.out.println("No task added");
                        } else if (result.equals("Failed to add event task")) {
                            System.out.println(result);
                        } else {
                            System.out.println("Got it. I've added this task:");
                        }
                    } else {
                        Todo.createTodo(line, isSame);
                        System.out.println("Got it. I've added this task:");
                    }
                    System.out.println("Now you have " + TaskList.getTaskCount() + " tasks in the list.");
                }
            }
            if (line.equalsIgnoreCase("bye")) { // 1. Exit application
                EuanUI.farewell();
                break;
            }
            ////// End OF CHAT BOT INTERACTION //////
            ///// Start of Reminders //////
            System.out.println("\n");
            Deadline.checkDeadline();
            ///// End of Reminders //////

        }


    }


}


