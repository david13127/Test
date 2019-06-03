package main.java.com.function.emailcheck;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
public class EmailCheck {
    private static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
    private List<String> a = new ArrayList<String>();
    private static Function<String, Result<String>> emailChecker1 = s -> {
        if (s == null) {
            return Result.failure("email must not be null");
        } else if (s.length() == 0) {
            return Result.failure("email must not be empty");
        } else if (emailPattern.matcher(s).matches()) {
            return Result.success(s);
        } else {
            return Result.failure("email " + s + " is invalid");
        }
    };

    private static Function<String, Result<String>> emailChecker2 = s -> Case.match(
            Case.mcase(() -> Result.success(s)),
            Case.mcase(() -> s == null, () -> Result.failure("email must not be null")),
            Case.mcase(() -> s.length() == 0, () -> Result.failure("email must not be empty")),
            Case.mcase(() -> !emailPattern.matcher(s).matches(), () -> Result.failure("email " + s + " is invalid"))
    );

    private static Effect<String> success = s -> System.out.println("Mail sent to " + s);
    private static Effect<String> failure = s -> System.err.println("Error message logged: " + s);

    public static void main(String[] args) {
        emailChecker2.apply("this.is@my.email").bind(success, failure);
        emailChecker2.apply(null).bind(success, failure);
        emailChecker2.apply("").bind(success, failure);
        emailChecker2.apply("this.is@my.com").bind(success, failure);
    }
}
