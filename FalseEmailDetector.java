import java.util.*;
import java.util.regex.Pattern;

public class FalseEmailDetector {

    private static final Pattern emailCheck = Pattern.compile(
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private static final Set<String> disposable = new HashSet<String>(
        Arrays.asList(
            "mailinator.com",
            "tempmail.com",
            "10minutemail.com",
            "guerrillamail.com",
            "yopmail.com",
            "trashmail.com",
            "fakeinbox.com",
            "maildrop.cc",
            "getnada.com"
        )
    );

    private static final Set<String> common = new HashSet<String>(
        Arrays.asList(
            "gmail.com",
            "outlook.com",
            "hotmail.com",
            "yahoo.com",
            "icloud.com",
            "proton.me",
            "protonmail.com"
        )
    );

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("---------------------------------");
        System.out.println("       FALSE EMAIL DETECTOR      ");
        System.out.println("---------------------------------");
        System.out.println("This system checks whether email is valid or not.");
        System.out.println();

        while (true) {

            System.out.print("Enter an email address(or type exit if you not wnat to continue): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            checkEmail(input);
            System.out.println();
        }

        sc.close();

        System.out.println("Program ended.");
    }

    private static void checkEmail(String email) {

        if (email.length() == 0) {
            showResult("INVALID", "No email address was entered.");
            return;
        }

        boolean correctFormat = emailCheck.matcher(email).matches();

        if (!correctFormat) {
            showResult("INVALID", "The email does not follow correct email format.");
            return;
        }

        String[] data = email.split("@", -1);

        String username = data[0].toLowerCase();
        String website = data[1].toLowerCase();

        ArrayList<String> problems = new ArrayList<>();

        if (disposable.contains(website)) {
            problems.add(
                "The domain is commonly used for temporary email addresses."
            );
        }

        if (website.startsWith(".") || website.endsWith(".") 
                || website.contains("..")) {

            problems.add("The domain has an unusual dot which is not expected.");
        }

        if (username.startsWith(".") || username.endsWith(".") || username.contains("..")) {

            problems.add("The username has an unusual dot which is not expected.");
        }

        if (username.length() > 64) {
            problems.add("The username is not valid.");
        }

        if (website.length() > 253) {
            problems.add("The domain is unusually long.");
        }

        if (checkNumbers(username)) {
            problems.add(
                "The username contains a pattern often seen in automatically generated addresses."
            );
        }

        if (problems.size() == 0) {

            if (common.contains(website)) {

                showResult(
                    "LIKELY VALID",
                    "The format is valid and the domain is a commonly used email provider."
                );

            } else {

                showResult(
                    "VALID FORMAT",
                    "The format looks valid, but this program does not verify whether the mailbox actually exists."
                );
            }

        } else {

            String message = String.join(" ", problems);
            showResult("SUSPICIOUS", message);
        }
    }

    private static boolean checkNumbers(String username) {

        int numbers = 0;
        int letters = 0;

        for (int i = 0; i < username.length(); i++) {

            char current = username.charAt(i);

            if (Character.isDigit(current)) {
                numbers++;
            } else if (Character.isLetter(current)) {
                letters++;
            }
        }

        if (numbers >= 7 && numbers > letters) {
            return true;
        }

        return false;
    }

    private static void showResult(String status, String message) {

        System.out.println("Result : " + status);
        System.out.println("Reason : " + message);
    }
}