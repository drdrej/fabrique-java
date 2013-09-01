package com.touchableheroes.fabrique.sdk.generate;

import com.touchableheroes.fabrique.sdk.android.model.App;
import com.touchableheroes.fabrique.sdk.generate.android.AndroidAppManifestGenerator;

public class SimpleGenerator {

	
	public void generate( App app ) {
		generator(new AndroidAppManifestGenerator());
//		generator();
	}

	private void generator(AndroidAppManifestGenerator appModelGenerator) {
		// TODO Auto-generated method stub
		
	}
}
