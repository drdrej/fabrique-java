package com.touchableheroes.fabrique.sdk.generate;

import java.io.File;
import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.touchableheroes.fabrique.sdk.generate.model.ClassModel;
import com.touchableheroes.fabrique.sdk.generate.util.FileUtil;
import com.touchableheroes.fabrique.sdk.generate.util.PackageUtil;

/**
 * Generate JSON-File from Java-Class.
 * 
 * @author asiebert
 */
public class JSONGenerator {

	private final Gson json;
	private final File workDir;

	public JSONGenerator(final File workDir) {
		this.workDir = workDir;

		final GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();

		builder.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT,
				Modifier.VOLATILE);

		json = builder.create();
	}

	public void generate(final ClassModel clazz) {
		final String rval = json.toJson(clazz);
		final File output = useFile(clazz);
		FileUtil.dump(rval, output);
	}

	private File useFile(final ClassModel clazz) {
		final String path = PackageUtil.asPath(clazz.packageName);
		final File fullPath = new File(workDir, path);

		if (!fullPath.exists())
			fullPath.mkdirs();

		final String fileName = clazz.name + ".class.json";

		return new File(fullPath, fileName);
	}

}
