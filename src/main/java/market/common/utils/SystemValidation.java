package market.common.utils;

import java.util.regex.Pattern;

public class SystemValidation {

    public static final Pattern CARD_ID_PATTERN = Pattern.compile("\\b[A-Za-z0-9 -]{1,12}");
    public static final Pattern TAG_ID_PATTERN = Pattern.compile("\\b[A-Za-z0-9]{6,12}");
    public static final Pattern ROOM_ID_PATTERN = Pattern.compile("^[A-Za-z0-9-]{1,12}$");
    public static final Pattern ROLENAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]{4,15}$");
    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]{4,100}$");
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^[\\s\\Wa-zA-Z0-9]{4,50}$");

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
