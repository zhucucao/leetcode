import java.util.ArrayList;
import java.util.Arrays;

public class BugTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.remove(new Integer(2));
        list.add(1,3);
        System.out.println(list);
    }
}


