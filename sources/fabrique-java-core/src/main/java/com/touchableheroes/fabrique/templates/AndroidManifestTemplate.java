package com.touchableheroes.fabrique.templates;

import com.github.jknack.handlebars.TypeSafeTemplate;
import com.touchableheroes.fabrique.sdk.android.model.App;
import com.touchableheroes.fabrique.sdk.android.model.Generate;


@Generate( "AndroidManifest.xml" )
public interface AndroidManifestTemplate extends TypeSafeTemplate<App> {

	
	public AndroidManifestTemplate setVersion(int version);
	
	public AndroidManifestTemplate setName( String name);
	
}
