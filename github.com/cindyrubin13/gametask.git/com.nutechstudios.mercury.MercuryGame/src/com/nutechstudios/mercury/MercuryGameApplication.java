package com.nutechstudios.mercury;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Top level Mercury declared as the application in the AndroidManifest.xml.
 * There is only one instance of this class created...by the android OS...upon
 * startup.
 */
public class MercuryGameApplication extends Application {

	private static final String TAG = "MercuryGameApplication";
	private static final int START_WAIT_TIME = 3000; // 3 seconds

	private static MercuryGameApplication instance;

	public static MercuryGameApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public void initializeParse() {
		try {
			synchronized (this) {
				Log.i(TAG, "Parse.initialize");
				// initialize Parse
				Parse.initialize(this, getString(R.string.parse_app_id),
						getString(R.string.parse_client_id));
				ParseUser.enableAutomaticUser();
				ParseACL defaultACL = new ParseACL();
				defaultACL.setPublicReadAccess(true);
				ParseACL.setDefaultACL(defaultACL, true);
				Log.i(TAG, "Parse.initialize - done");
				// add some delay, to show the splash screen
				wait(START_WAIT_TIME);
			}
		} catch (Exception ex) {
			Log.e(TAG + "." + "Exception in initializeParse", ex.getMessage());
		}
	}

	public void showToast(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
		toast.show();
	}

}
