package com.touchableheroes.fabrique.sdk.generate.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class FileUtil {


	public static void dump(final String rval, final File output) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( output );
			writer.println( rval );
		} catch (final FileNotFoundException e) {
			System.out.println( "[ERROR] couldn't write JSON to File {file : " + output.getAbsolutePath() + " }" );
			e.printStackTrace();
		} finally {
			closeSilently(writer);
		}
	}
	
	public static void closeSilently( final Writer writer ) {
		if( writer == null )
			return;
		
		try {
			writer.close();
		} catch (IOException e) {
			;
		}
	}
}
