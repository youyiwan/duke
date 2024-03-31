package tasks;
import org.junit.jupiter.api.Test;

import static tasks.TaskList.createNewTaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {

    @Test
    public void testcreateEvent() {
        createNewTaskList();
        String line = "event project meeting /from 02:00/to 17:00";
        boolean isSame = false;

        assertEquals("[E][ ] project meeting (from: 02 00 to: 17 00)",
                            Event.createEvent(line, isSame)
        );
    }
}
