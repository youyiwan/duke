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
                isSame = false;
                ////// EXCEPTION HANDLING //////
                    try {
                        myEuanExceptions.createKeywords();
                        if(line.isEmpty()) {
                            error = 1;
                            throw new EuanExceptions();
                        }
                        else if (!myEuanExceptions.containKeyWords(line)){
                            if ( line.length() <= 4){
                                error = 2;
                                throw new EuanExceptions();
                            }
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

                ////// Start of Chatbot interaction //////
                if (line.equalsIgnoreCase("bye")) // 1. Exit application
                {
                    myEuanUI.farewell();
                    break;
                }
                else if(line.equalsIgnoreCase("list")) // 2. Print Task & Write to File
                {
                      myStorage.printlist();
                }
                else if( line.length() >= 4 &&  line.substring(0,4).equalsIgnoreCase("find") ){    // 3. mark task as done
                    myTaskList.findTask(line);
                }
                else if( line.length() >= 4 &&  line.substring(0,4).equalsIgnoreCase("mark") ){    // 3. mark task as done
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
                        System.out.println("Got it. I've added this task:");
                        if (line.substring(0,dividerFirstSpace).equalsIgnoreCase("deadline"))
                        {
                              Deadline.createDeadline(line, isSame);
                        }
                        else if (line.substring(0,dividerFirstSpace).equalsIgnoreCase("event"))
                        {
                              Event.createEvent(line, isSame);
                        }
                        else {
                              Todo.createTodo(line, isSame);
                        }
                        System.out.println("Now you have " + TaskList.getTaskCount() + " tasks in the list.");
                    }
                }
                ////// End of Chatbot interaction //////
                ///// Start of Reminders //////

                Deadline.checkDeadline();

                ///// End of Reminders //////

            }


        }

}


