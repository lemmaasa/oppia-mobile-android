package org.digitalcampus.mtrain.application;

import java.io.File;

import android.app.Application;
import android.os.Environment;

public class MTrain extends Application {

	public static final String TAG = "MTrain";

	public static final String MTRAIN_ROOT = Environment
			.getExternalStorageDirectory() + "/mtrain/";
	public static final String MODULES_PATH = MTRAIN_ROOT + "modules/";
	public static final String MEDIA_PATH = MTRAIN_ROOT + "media/";
	public static final String DOWNLOAD_PATH = MTRAIN_ROOT + "download/";
	public static final String MODULE_XML = "module.xml";

	public static final String SERVER_MODULES_PATH = "/modules/list/";
	public static final String TRACKER_PATH = "/api/?method=tracker";
	public static final String LOGIN_PATH = "/api/?method=login";
	public static final String REGISTER_PATH = "/api/?method=register";
	
	public static final int PASSWORD_MIN_LENGTH = 6;
	
	public MTrain(){
		
	}
	
	public static void createMTrainDirs() throws RuntimeException {
		String cardstatus = Environment.getExternalStorageState();
		if (cardstatus.equals(Environment.MEDIA_REMOVED)
				|| cardstatus.equals(Environment.MEDIA_UNMOUNTABLE)
				|| cardstatus.equals(Environment.MEDIA_UNMOUNTED)
				|| cardstatus.equals(Environment.MEDIA_MOUNTED_READ_ONLY)
				|| cardstatus.equals(Environment.MEDIA_SHARED)) {
			RuntimeException e = new RuntimeException(
					"mTrain reports :: SDCard error: "
							+ Environment.getExternalStorageState());
			throw e;
		}

		String[] dirs = { MTRAIN_ROOT, MODULES_PATH, MEDIA_PATH, DOWNLOAD_PATH };

		for (String dirName : dirs) {
			File dir = new File(dirName);
			if (!dir.exists()) {
				if (!dir.mkdirs()) {
					RuntimeException e = new RuntimeException(
							"mTrain reports :: Cannot create directory: "
									+ dirName);
					throw e;
				}
			} else {
				if (!dir.isDirectory()) {
					RuntimeException e = new RuntimeException(
							"mTrain reports :: " + dirName
									+ " exists, but is not a directory");
					throw e;
				}
			}
		}
	}

}
