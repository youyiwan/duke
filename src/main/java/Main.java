import java.util.HashMap;

import Parser.DatesTimes;
import Tasks.*;
import UI.EuanUI;
import Exceptions.EuanExceptions;
import Storage.Storage;
public class Main {
    static TaskList myTaskList = new TaskList();
    static EuanUI myEuanUI = new EuanUI();
    static EuanExceptions myEuanExceptions = new EuanExceptions();
    static Storage myStorage = new Storage();

    public static void main(String[] args) {

        myTaskList.createNewTaskList();

        int error = 0;
        String line;
        boolean isSame = false;

        myEuanUI.greetings();

            while(true)
            {

                line = myEuanUI.getInput();
                int lineLength = line.length();
                isSame = false;
                /////// START OF PRINT & EXIT BLOCK ///////
                if(line.equalsIgnoreCase("list")) // 2. Print Task & Write to File
                {
                    while (true){
                        myStorage.printlist();
                        line = myEuanUI.getInput();
                        if (!line.equalsIgnoreCase("list")){
                            break;
                        }
                    }
                    if (line.equalsIgnoreCase("bye")){
                        EuanUI.farewell();
                        break;
                    }
                }
                else if (line.equalsIgnoreCase("bye")) // 1. Exit application
                {
                    EuanUI.farewell();
                    break;
                }
                /////// END PRINT & EXIT BLOCK ///////

                ////// START OF EXCEPTION HANDLING //////
                try {
                    EuanUI.createKeywords();
                    if(line.isEmpty() ) { // User enters empty string
                        error = 1;
                        throw new EuanExceptions(); // User starts with an unrecognize key word
                    }
                    else if ( lineLength < 4 ){
                        error = 2;
                        throw new EuanExceptions();
                    }
                    else if (!EuanUI.isKeyWord(line)){
                            error = 2;
                            throw new EuanExceptions();
                    }
                }
                catch (EuanExceptions e) {
                    if (error == 1){
                        myEuanExceptions.emptyStringError();
                        continue;
                    }
                    else if (error == 2){
                        myEuanExceptions.isRubbish();
                        continue;
                    }
                }
                ////// END OF EXCEPTION HANDLING //////

                ////// START OF CHAT BOT INTERACTION //////
                if( line.length() >= 4 &&  line.substring(0,4).equalsIgnoreCase("find") ){    // 3a. mark task as done
                    myTaskList.findTask(line);
                }
                else if( line.length() >= 4 &&  line.substring(0,4).equalsIgnoreCase("mark") ){    // 3b. mark task as done
                    myTaskList.markTask(line);
                }
                else if( line.length() >= 6 && line.substring(0,6).equalsIgnoreCase("unmark") ){ // 4. unmark task
                      myTaskList.unmarkTask(line);
                }
                else if( line.length() >= 6 && line.substring(0,6).equalsIgnoreCase("delete") ){ // 5. delete task
                      myTaskList.deleteTask(line);
                }
                else { // 6. Check for duplicate task
                    if ( myTaskList.checkDuplicates(line, isSame) ) {
                        System.out.println("Task already exist");
                    }
                    else { // 7. if false create new entry
                        int dividerFirstSpace = line.indexOf(' ');
                        if (line.substring(0,dividerFirstSpace).equalsIgnoreCase("deadline"))
                        {
                                String result = Deadline.createDeadline(line, isSame);
                                if ( result.isEmpty()){
                                    System.out.println("No task added");
                                }
                                else {
                                    System.out.println("Got it. I've added this task:");
                                }
                        }
                        else if (line.substring(0,dividerFirstSpace).equalsIgnoreCase("event"))
                        {
                              String result = Event.createEvent(line, isSame);
                                if ( result.isEmpty()){
                                    System.out.println("No task added");
                                }
                                else {
                                    System.out.println("Got it. I've added this task:");
                                }
                        }
                        else {
                              Todo.createTodo(line, isSame);
                                System.out.println("Got it. I've added this task:");
                        }
                        System.out.println("Now you have " + TaskList.getTaskCount() + " tasks in the list.");
                    }
                }
                ////// End OF CHAT BOT INTERACTION //////
                ///// Start of Reminders //////
                Deadline.checkDeadline();
                ///// End of Reminders //////

            }


        }

}


