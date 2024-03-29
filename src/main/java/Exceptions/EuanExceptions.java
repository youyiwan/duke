package Exceptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
public class EuanExceptions extends Exception{

    int error = 0;


    public void emptyStringError()
    {
        System.out.println ("Congratulations! 😂 You have created in an empty task. There is nothing for you to do.");
        System.out.println("If this is a typo. Please try again.");
    }


    public void isRubbish()
    {
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

//    public static String dateFormat() {
//        return "Please enter the deadline in yyyy-mm-dd format";
//    }

    public void timeFormat() {
        System.out.println("Please enter the time in HH:MM format");
    }

}
