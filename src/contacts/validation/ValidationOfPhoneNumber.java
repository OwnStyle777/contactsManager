package contacts.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationOfPhoneNumber {

    public boolean isPhoneNumberValidated(String phoneNumber) {

        String prefix = "(\\+?[A-Za-z\\d]*[ -]?)?";
        String secondGroup = "((?:\\([A-Za-z\\d]+\\)|[A-Za-z\\d]{2,})[ -]?)?";
        String regex = prefix + secondGroup + "([A-Za-z\\d]{2,}[ -])*([A-Za-z\\d]{2,})*";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {

            return true;
        }
        return false;
    }
}
