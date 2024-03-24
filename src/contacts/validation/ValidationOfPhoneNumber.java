package contacts.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationOfPhoneNumber {

    public boolean isPhoneNumberValidated(String phoneNumber) {
        
        String regex = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {

            return true;
        }
        return false;
    }
}
