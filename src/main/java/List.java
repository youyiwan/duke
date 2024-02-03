import java.util.Map;
import java.util.HashMap;
public class List {

    protected String description;
    protected String mark;

    public List(String description, String mark) {     // Constructor
        this.description = description;
        this.mark = mark;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return getDescription();
    }


}
