package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EuanUITest {

    @Test
    public void testfarewell()
    {

        assertEquals("Bye. Hope to see you again soon!", EuanUI.farewell());
    }

}
