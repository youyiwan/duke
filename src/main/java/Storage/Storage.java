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
import java.util.HashMap;

public class Storage {

    public void printlist()
    {
        File f = new File("data/Euan.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(f));
            for (HashMap.Entry<Integer, Tasks.Task> entry : TaskList.taskMap.entrySet()) {
                System.out.println(entry.getKey() + "." + entry.getValue().toString());
                bf.write(entry.getKey() + "." + entry.getValue().toString());
                bf.newLine();
            }

//            Deadline.checkDeadline();

            bf.close();
        }
        catch (
                IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
