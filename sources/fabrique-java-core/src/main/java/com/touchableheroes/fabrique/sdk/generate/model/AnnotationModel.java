package com.touchableheroes.fabrique.sdk.generate.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.touchableheroes.fabrique.sdk.generate.util.MethodUtil;

/**
 * @author asiebert
 */
public class AnnotationModel {
	
	private static Set<String> EXCLUDE_METHODS = new HashSet<String>();
	
	static {
		EXCLUDE_METHODS.add("equals");
		EXCLUDE_METHODS.add( "toString" );
		EXCLUDE_METHODS.add( "hashCode" );
		EXCLUDE_METHODS.add( "annotationType" );
	}
	
	private transient final Annotation backRef;
	
	public final Map<String, Object> values;
	
	public final String fqn;
	public final String name;
	public final String packageFqn;
	
	
	public AnnotationModel(final Annotation annotation) {
		backRef = annotation;
		
		final Class<? extends Annotation> type = annotation.annotationType();
		this.values = extractAnnotationValues(type);
		
		fqn = extractFQN( type );
		name = extractName(type);
		packageFqn = extractPackage(type);
	}

	private String extractPackage(final Class<? extends Annotation> type) {
		return type.getPackage().getName();
	}

	private String extractName(final Class<? extends Annotation> type) {
		return type.getSimpleName();
	}

	private String extractFQN(Class<? extends Annotation> type) {
		return type.getName();
	}

	private Map<String, Object> extractAnnotationValues(
			Class<? extends Annotation> type) {
		final Method[] methods = type.getMethods();
		final Map<String, Object> values = new HashMap<String, Object>(methods.length);
		
		for (final Method method : methods ) {
			if( skipMethod(method) )
				continue;
			
			final Object value = extractValue( method, backRef );
			values.put( method.getName(), value );
		};
		
		return values;
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
	
//	private List<AnnotationValueModel> extractAnnotationValues(
//			Class<? extends Annotation> type) {
//		final Method[] methods = type.getMethods();
//		final List<AnnotationValueModel> values = new ArrayList<AnnotationValueModel>();
//		
//		for (final Method method : methods ) {
//			if( skipMethod(method) )
//				continue;
//			
//			final AnnotationValueModel value = new AnnotationValueModel( method, backRef );
//			values.add( value );
//		};
//		
//		return values;
//	}

	private boolean skipMethod(Method method) {
		if (EXCLUDE_METHODS.contains( method.getName() ) ) 
			return true;
	
		return !MethodUtil.isGetter(method);
	}

}
