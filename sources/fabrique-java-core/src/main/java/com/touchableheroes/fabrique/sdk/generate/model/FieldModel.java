package com.touchableheroes.fabrique.sdk.generate.model;

import java.lang.reflect.Field;

public class FieldModel {

	public FieldModel(final Field field) {
	   name = extractName(field);	
	   typeName = extractTypeName(field);
	   packageName = extractPackage(field);
	   fqn = extractFQN(field);
	}
	
	private String extractFQN(final Field field) {
		return field.getType().getName();
	}

	private String extractPackage(final Field field) {
		return field.getType().getPackage().getName();
	}

	private String extractTypeName(final Field field) {
		return field.getType().getSimpleName();
	}

	private String extractName(final Field field) {
		return field.getName();
	}

	public final String name;
	
	public final String typeName;
	
	public final String packageName;
	
	public final String fqn;

}
