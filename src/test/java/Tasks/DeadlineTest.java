package Tasks;
import Parser.DatesTimes;
import org.junit.jupiter.api.Test;

import static Tasks.TaskList.createNewTaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void testcreateDeadline() {
        createNewTaskList();
        String line = "deadline return book /by 2019-12-01";
        boolean isSame = false;

        assertEquals("[D][ ] return book (by: Dec 01 2019)",
                Deadline.createDeadline(line, isSame)
        );
    }
}
