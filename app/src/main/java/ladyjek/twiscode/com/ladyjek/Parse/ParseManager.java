package ladyjek.twiscode.com.ladyjek.Parse;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import ladyjek.twiscode.com.ladyjek.Model.ApplicationData;

/**
 * Created by Ravi on 01/06/15.
 */
public class ParseManager {

    private static String TAG = ParseManager.class.getSimpleName();

    public static void verifyParseConfiguration(Context context) {
        if (TextUtils.isEmpty(ApplicationData.PARSE_APPLICATION_ID) || TextUtils.isEmpty(ApplicationData.PARSE_CLIENT_KEY)) {
            Toast.makeText(context, "Please configure your Parse Application ID and Client Key in ApplicationData.java", Toast.LENGTH_LONG).show();
            ((Activity) context).finish();
        }
    }

    public static void registerParse(Context context) {
        Parse.initialize(context, ApplicationData.PARSE_APPLICATION_ID, ApplicationData.PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParsePush.subscribeInBackground(ApplicationData.PARSE_CHANNEL, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.e(TAG, "Successfully subscribed to Parse!");
            }
        });
    }

    public static String getDeviceToken(){
        final String[] dToken = new String[0];
        ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                String deviceToken = (String) ParseInstallation.getCurrentInstallation().get("deviceToken");
                dToken[0] = deviceToken;
            }
        });
        return dToken[0];
    }

    public static void subscribeWithEmail(String email) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("email", email);
        installation.saveInBackground();
    }
}
