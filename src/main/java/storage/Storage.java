package storage;
import parser.DatesTimes;
import tasks.Deadline;
import tasks.TaskList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import static tasks.TaskList.taskMap;
/**
 * All printing of tasks and writing to the file associated methods are in the storage class.
 * It has two public methods print and save.
 * First is to save and print the list for user to view the entire tasklist
 * Second, at the same time it writes the tasklist to the predefined flat file save in "data/Euan.txt"
 */
public class Storage {

    /**
     * Save the task list to a predefined flat file save in "data/Euan.txt" while at the same time prints the task list.
     */
    public void save(){
        if (taskMap.isEmpty()){
            System.out.println("There are no task currently.");
        }
        else {
            File f = new File("data/Euan.txt");
            System.out.println("This is your file path: " + f.getAbsolutePath());
            System.out.println("file exists?: " + f.exists());
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(f));
                System.out.println("\n");
                bf.write("\n");
                System.out.println("Your task(s) as of " + LocalDate.now() + "\n");
                bf.write("Your task(s) as of " + LocalDate.now() + "\n");
                for (HashMap.Entry<Integer, tasks.Task> entry : taskMap.entrySet()) {
                    if(entry.getKey() == null || entry.getValue() == null){
                        break;
                    }
                    else {
                        System.out.println(entry.getKey() + "." + entry.getValue());
                        bf.write(entry.getKey() + "." + entry.getValue().toString());
                        bf.newLine();
                    }

                }
                System.out.println("\n");
                bf.write("\n");
                Deadline.checkDeadline();
                bf.write("-------------------------------------- Start of Reminder ------------------------------------- \n");
                for (HashMap.Entry<Integer, tasks.Task> entry : TaskList.taskMap.entrySet()) {
                    bf.write("\n");
                    String description = String.valueOf(entry.getValue());

                    if (description.contains("by")) {
                        int dividerBy = description.indexOf("by: ");
                        String tempCheckDate = description.substring(dividerBy).replace("by: ", "");
                        String checkDate = tempCheckDate.replace(")", "");
                        bf.write("Task #" + entry.getKey() + ": " + DatesTimes.getRegularReminder(checkDate) + "\n"); // Date format here is in MMM DD YYYY
                    }
                    bf.write("\n");

                }
                bf.write("--------------------------------------  End of Reminder  ------------------------------------- \n");
                bf.close();
            }
            catch (
                    IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }


    }
    /**
     * Prints the task list.
     */
    public void printlist(){
        if (taskMap.isEmpty()){
            System.out.println("There are no task currently.");
        }
        else {
            System.out.println("Your task(s) as of " + LocalDate.now() + "\n");
            for (HashMap.Entry<Integer, tasks.Task> entry : taskMap.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    break;
                }
                else System.out.println(entry.getKey() + "." + entry.getValue());
            }
            System.out.println( "\n");
            Deadline.checkDeadline();
        }


    }

}
