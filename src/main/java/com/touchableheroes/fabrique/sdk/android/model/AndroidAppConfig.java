package com.touchableheroes.fabrique.sdk.android.model;

public @interface AndroidAppConfig {

	
	int getVersionCode();

	String versionName();

	AndroidSdk sdk();

	boolean allowBackup();

	Drawable icon();

	Message label();

	Style theme();

}
