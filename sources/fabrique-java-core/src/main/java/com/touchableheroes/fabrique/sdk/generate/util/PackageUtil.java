package com.touchableheroes.fabrique.sdk.generate.util;

import java.io.File;

public class PackageUtil {

	public static String asPath(final String packageName) {
		return packageName.replaceAll(".", "/" );
	}

}
