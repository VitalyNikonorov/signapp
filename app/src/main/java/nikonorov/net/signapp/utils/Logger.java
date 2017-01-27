package nikonorov.net.signapp.utils;

import android.util.Log;

/**
 * Created by vitaly on 28.01.17.
 */

public class Logger {

    public static boolean isLoggingNeed = true;

    public static void d(String classFrom, String msg){
        if (isLoggingNeed){
            Log.d(classFrom, msg);
        }
    }

    public static void e(String classFrom, Throwable e){
        if (isLoggingNeed){
            e.printStackTrace();
        }
    }

}
