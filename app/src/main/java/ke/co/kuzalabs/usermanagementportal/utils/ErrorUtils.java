package ke.co.kuzalabs.usermanagementportal.utils;

import java.io.IOException;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:35
 */
public class ErrorUtils {

    public static String parseOnFailure(Throwable t) {
        ConsoleUtills.printException((Exception) t);
        if (t instanceof IOException) {
            //Add your code for displaying no network connection error
            return "Check your internet connectivity.";
        }

        return "Something went wrong. Try later";

    }

}
