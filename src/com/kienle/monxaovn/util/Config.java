package com.kienle.monxaovn.util;

import android.os.Environment;

public class Config {
	public static final String FILE_JSON_NAME = "monxao.json";
	public static final String APP_FOLDER = Environment.getExternalStorageDirectory().getPath() + "/MonXaoVN";
	public static final String IMAGE_FOLDER = APP_FOLDER + "image";
	public static final String THUMB_FOLDER = APP_FOLDER + "/thumb";
}
