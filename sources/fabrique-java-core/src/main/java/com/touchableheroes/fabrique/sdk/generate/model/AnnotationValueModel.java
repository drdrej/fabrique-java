package com.touchableheroes.fabrique.sdk.generate.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationValueModel {


	public AnnotationValueModel(final Method method, final Annotation annotation) {
		name = method.getName();
		
		defaultValue = method.getDefaultValue();
		
		value = extractValue(method, annotation);
	}

	private Object extractValue(final Method method, final Annotation annotation) {
		try {
			return method.invoke(annotation);
		} catch (final Throwable e) {
			System.out.println("[ERROR] couldn't extract value of annotation.method {name : " + method.getName() + "} ");
			
			e.printStackTrace();
		} 
		
		return null;
	}

	public String name;
	
	public Object value;

	public Object defaultValue;

}
