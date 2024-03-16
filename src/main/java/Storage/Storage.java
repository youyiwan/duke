package Storage;
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
            bf.close();
        }
        catch (
                IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
