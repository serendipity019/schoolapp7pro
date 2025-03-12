package gr.aueb.cf.schoolapp.authedication;

import java.util.Arrays;

public class AuthedicationManager {

    private AuthedicationManager() {}

    public static boolean authedicate(String username, char[] password) {
        return username.matches("[aA]dmin") && Arrays.equals(password, "12345".toCharArray());
    }
}
