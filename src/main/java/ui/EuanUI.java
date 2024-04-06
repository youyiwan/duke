package ui;
import java.util.ArrayList;
import java.util.Scanner;
public class EuanUI {

    public String myInput;
    public String getInput(){
        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();
        myInput = line;
        return myInput;
    }
    static ArrayList<String> keyWordsList;
    public static void createKeywords(){
        keyWordsList = new ArrayList<>();
        keyWordsList.add("Bye");
        keyWordsList.add("bye");
        keyWordsList.add("List");
        keyWordsList.add("list");
        keyWordsList.add("Mark");
        keyWordsList.add("mark");
        keyWordsList.add("mark");
        keyWordsList.add("Unmark");
        keyWordsList.add("unmark");
        keyWordsList.add("Delete");
        keyWordsList.add("delete");
        keyWordsList.add("Find");
        keyWordsList.add("find");
        keyWordsList.add("Todo");
        keyWordsList.add("todo");
        keyWordsList.add("Event");
        keyWordsList.add("event");
        keyWordsList.add("Deadline");
        keyWordsList.add("deadline");
        keyWordsList.add("Save");
        keyWordsList.add("save");
    }

    public void greetings(){
        String name = "Euan";
        System.out.println("Hello! I'm " + name+"\n"+"What can I do for you?");
    }

    public static String farewell(){
        System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }

    public static String getCommand(String line){

        if(line.length() == 4 && (line.equalsIgnoreCase("list") || line.equalsIgnoreCase("save") )){
            return line.substring(0,4);
        }
        else if (line.length() > 4){
            int dividerFirstSpace = line.indexOf(' ');
            return line.substring(0,dividerFirstSpace);
        }

        return line + "fail";

    }

    public static boolean isKeyWord(String line){
//        if(line.equalsIgnoreCase("bye")){
//            return true;
//        }
//        else if (line.equalsIgnoreCase("list")){
//            return true;
//        }
        return keyWordsList.contains(getCommand(line));
    }


}
