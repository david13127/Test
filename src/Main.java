import java.io.Serializable;
import java.util.*;

public class Main {
    private static final List<String> list = Arrays.asList("a", "b");
    public static void main(String[] args) {
        String str = "a";
        System.out.println(null instanceof Object);
        Main main = new Main();
        String o = main.getClass().getSuperclass().getName();
        System.out.println(o);
    }
}
