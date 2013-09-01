package com.touchableheroes.fabrique.sdk.commands;

import com.touchableheroes.fabrique.sdk.generate.android.AndroidAppManifestGenerator;

public class AndroidAppBuildProcess {

	
	public void build() {
		use( AndroidAppManifestGenerator.class );
	}

	private void use(Class<AndroidAppManifestGenerator> class1) {
		// TODO Auto-generated method stub
		
	}
}
