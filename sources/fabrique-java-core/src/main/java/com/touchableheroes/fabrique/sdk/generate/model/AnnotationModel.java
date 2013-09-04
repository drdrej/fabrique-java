package com.touchableheroes.fabrique.sdk.generate.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.touchableheroes.fabrique.sdk.generate.util.MethodUtil;

public class AnnotationModel {
	
	private static Set<String> EXCLUDE_METHODS = new HashSet<String>();
	
	static {
		EXCLUDE_METHODS.add("equals");
		EXCLUDE_METHODS.add( "toString" );
		EXCLUDE_METHODS.add( "hashCode" );
		EXCLUDE_METHODS.add( "annotationType" );
	}
	
	private transient final Annotation backRef;
	
	public final List<AnnotationValueModel> values;
	
	public AnnotationModel(final Annotation annotation) {
		backRef = annotation;
		
		final Class<? extends Annotation> type = annotation.annotationType();
		this.values = extractAnnotationValues(type);
	}

	private List<AnnotationValueModel> extractAnnotationValues(
			Class<? extends Annotation> type) {
		final Method[] methods = type.getMethods();
		final List<AnnotationValueModel> values = new ArrayList<AnnotationValueModel>();
		
		for (final Method method : methods ) {
			if( skipMethod(method) )
				continue;
			
			final AnnotationValueModel value = new AnnotationValueModel( method, backRef );
			values.add( value );
		};
		
		return values;
	}

	private boolean skipMethod(Method method) {
		if (EXCLUDE_METHODS.contains( method.getName() ) ) 
			return true;
	
		return !MethodUtil.isGetter(method);
	}

}
