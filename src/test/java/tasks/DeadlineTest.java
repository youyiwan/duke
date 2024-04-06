package tasks;
import org.junit.jupiter.api.Test;

import static tasks.TaskList.createNewTaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void testcreateDeadline() {
        createNewTaskList();
        String line = "deadline return book /by 2025-12-01";
        boolean isSame = false;

        assertEquals("[D][ ] return book (by: Dec 01 2025)",
                Deadline.createDeadline(line, isSame));
    }
}
