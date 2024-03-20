package Exceptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;
public class EuanExceptions extends Exception{

    ArrayList<String> keyWords;
    int error = 0;



    public void createKeywords()
    {
        keyWords = new ArrayList<>();
        keyWords.add("Bye");
        keyWords.add("bye");
        keyWords.add("List");
        keyWords.add("list");
        keyWords.add("Mark");
        keyWords.add("mark");
        keyWords.add("mark");
        keyWords.add("Unmark");
        keyWords.add("unmark");
        keyWords.add("Delete");
        keyWords.add("delete");
    }

    public void catchExecptions(String line){
        try {
            createKeywords();
            if(line.isEmpty()) {
                error = 1;
                throw new EuanExceptions();
            }
            else if (!containKeyWords(line)){
                if ( line.length() <= 4){
                    error = 2;
                    throw new EuanExceptions();
                }
            }
        }
        catch (EuanExceptions e) {
            if (error == 1){
                emptyStringError();
            }
            else if (error == 2){
                isRubbish();
            }
        }

    }


    public boolean containKeyWords(String line) {

        return keyWords.contains(line);
    }

    public void printKey()
    {
        System.out.println(keyWords);
    }

    public void emptyStringError()
    {
        System.out.println ("Congratulations! You have created in an empty task. There is nothing for you to do.");
        System.out.println("If this is a typo. Please try again.");
    }


    public void isRubbish()
    {
        System.out.println ("Sorry, I do not understand.");

    }

    public void dateFormat() {
        System.out.println("Please enter the deadline in yyyy-mm-dd format");
    }

    public void timeFormat() {
        System.out.println("Please enter the time in HH:MM format");
    }

}
