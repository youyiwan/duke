package Tasks;


import org.junit.jupiter.api.Test;

import static Tasks.TaskList.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testgetTaskCount()
    {

        assertEquals(taskCount, TaskList.getTaskCount());


    }

}
