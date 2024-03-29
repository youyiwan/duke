/**
 * printList serves two purpose
 * First is to print the list for user to view the entire tasklist at head
 * Second, at the same time it writes the tasklist to the predefined flat file save in "data/Euan.txt"
 */


package Storage;
import Parser.DatesTimes;
import Tasks.Deadline;
import Tasks.TaskList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import static Tasks.TaskList.taskMap;

public class Storage {

    public void save()
    {
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
                for (HashMap.Entry<Integer, Tasks.Task> entry : taskMap.entrySet()) {
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
                for (HashMap.Entry<Integer, Tasks.Task> entry : TaskList.taskMap.entrySet()) {
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

    public void printlist()
    {
        if (taskMap.isEmpty()){
            System.out.println("There are no task currently.");
        }
        else {
            System.out.println("Your task(s) as of " + LocalDate.now() + "\n");
            for (HashMap.Entry<Integer, Tasks.Task> entry : taskMap.entrySet()) {
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
