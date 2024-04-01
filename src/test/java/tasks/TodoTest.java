package tasks;

import org.junit.jupiter.api.Test;

import static tasks.TaskList.createNewTaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testcreateTodo(){
        createNewTaskList();
        String line = "Todo read book";
        boolean isSame = false;

        assertEquals("[T][ ] read book",Todo.createTodo(line, isSame));
    }


}
