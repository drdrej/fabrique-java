package com.touchableheroes.fabrique.sdk.generate.model;

import java.lang.reflect.Method;

public class MethodModel {

	public final String name;

	public int defaultValue = 5;
	
	public MethodModel( final Method method ) {
		name = method.getName();
	}
	
	
}
