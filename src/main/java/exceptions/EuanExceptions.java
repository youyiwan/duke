package exceptions;

import ui.EuanUI;

/**
 * This is an exception class to catch any errors.
 * Its purpose is to catch and return helpful messages to assist user in using this app.
 */
public class EuanExceptions extends Exception{

    public static void  checkForExceptions(String line){
        int error = 0;
        int lineLength = line.length();
        if (line.equalsIgnoreCase("bye")) // 1. Exit application
        {
            return;
        }
        try {
            if(line.isEmpty() ) { // User enters empty string
                error = 1;
                throw new EuanExceptions(); // User starts with an unrecognize key word
            }
            else if ( lineLength < 4){
                error = 2;
                throw new EuanExceptions();
            }
            else if (!EuanUI.isKeyWord(line)){
                error = 2;
                throw new EuanExceptions();
            }
        } catch (EuanExceptions e) {
            if (error == 1){
                emptyStringError();
            }
            else if (error == 2){
                isRubbish();
            }
        }
    }


    /**
     * Returns a message to inform user that an empty task is entered.
     */
    public static void emptyStringError(){
        System.out.println ("Congratulations! You have created in an empty task. There is nothing for you to do.");
        System.out.println("If this is a typo. Please try again.");
    }

    /**
     * Returns a message to inform user that what was entered was not comprehended by the app and returns guidelines on how to use this app.
     */
    public static void isRubbish(){
        System.out.println ("Sorry, I do not understand.");
        System.out.println ("Please start the sentence with the following keywords:");
        System.out.println ("#1: 'Todo': refers to a generic task. For example 'todo return book'");
        System.out.println ("#2: 'Deadline': refers to a task that carries a specific deadline in 'YYYY-MM-DD' format. For example 'deadline return book /by 2024-03-25'. \n" +
                            "Please remember to specify '/by' before the date.");
        System.out.println ("#3: 'Event': refers to a task that refers to a meeting/appointment that requires user to specify a timeframe.\n" + "For example 'event project meeting /from 02:00/to 17:00'. " +
                "Please remember to specify '/from' and '/to' before the time.");
        System.out.println ("#4: 'Mark': refers to an action to mark a task as completed. Please specify a task number after this keyword. For example mark 1.");
        System.out.println ("#5: 'Unmark': refers to an action to unmark a task as incomplete. Please specify a task number after this keyword. For example unmark 1.");
        System.out.println ("#6: 'Delete': refers to an action to delete a task. Please specify a task number after this keyword. For example delete 1.");
        System.out.println ("#7: 'Find': There are two types of 'find' capabilities.\n" + "You may find task containing a common word. For example 'find book'. Or \n" +
                "You many find task by date by specifying a common deadline. For example 'find Mar 30 2024'" );
        System.out.println ("#8: 'Bye': To exit.");
    }


    public void timeFormat() {
        System.out.println("Please enter the time in HH:MM format");
    }

    public void taskInvalid() {
        System.out.println("Task number specified is invalid, please enter 'list' to view current task(s)");
    }

    public void missingByClause(){
        System.out.println("For deadline task, it is mandatory to specify /by before the date. For example '/by 2025-03-25'");
    }

    public void missingFromToClause(){
        System.out.println("For event task, it is mandatory to specify /from and /to before the time. For example 'event project meeting /from 02:00/to 17:00'");
    }

}
