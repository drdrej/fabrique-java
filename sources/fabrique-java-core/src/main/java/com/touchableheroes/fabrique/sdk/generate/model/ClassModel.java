package com.touchableheroes.fabrique.sdk.generate.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.touchableheroes.fabrique.sdk.generate.util.MethodUtil;


/**
 * Abstract View of a class to build JSON-Fingerprint of this class.
 * this JSON_Model will be transfered with JS into Artefacts.
 * 
 * @author asiebert
 */
public class ClassModel {

	
	private transient final Class<?> typeReference;

	public ClassModel(final Class<?> type) {
		this.typeReference = type;
		
		name = extractName();
		isPublic = extractIsPublic();
		fqn = extractFQN();
		packageName = extractPackageName();
		
		anntoations = extractAnnotations(); // Collections.emptyList();
		fields = extractFields(); 
		methods = extractMethods();
		getter = extractGetter();
	}

	private List<MethodModel> extractMethods() {
		final Method[] methods = typeReference.getMethods();
		final List<MethodModel> rval = new ArrayList<MethodModel>();
		
		for (final Method method : methods) {
			if( MethodUtil.isGetter(method) )
				continue;
			
			final MethodModel model = new MethodModel(method);
			rval.add( model );
		}
		
		return rval;
	}
	
	/**
	 * collect methods with getter-syntax:
	 * 1. return value not void
	 * 2. no params
	 * 3. if Name starts with "get" followed by Capital-Char, name should be extracted without get
	 * 
	 * @return
	 */
	private List<MethodModel> extractGetter() {
		final Method[] methods = typeReference.getMethods();
		final List<MethodModel> rval = new ArrayList<MethodModel>();
		
		for (final Method method : methods) {
			if( !MethodUtil.isGetter(method) )
				continue;
			
			final MethodModel model = new MethodModel(method);
			rval.add( model );
		}
		
		return rval;
	}

	

	private List<FieldModel> extractFields() {
		final Field[] fields = typeReference.getFields();
		final List<FieldModel> rval = new ArrayList<FieldModel>();
		
		for (final Field field : fields) {
			final FieldModel model = new FieldModel(field);
			rval.add(model);
		}
		
		return rval;
	}

	private List<AnnotationModel> extractAnnotations() {
		final List<AnnotationModel> rval = new ArrayList<AnnotationModel>();
		
		final Annotation[] annotations = typeReference.getAnnotations();
		
		for (final Annotation annotation : annotations) {
			final AnnotationModel model = new AnnotationModel(annotation);
			rval.add( model );
		}
		
		return rval;
	}

	private String extractPackageName() {
		return typeReference.getPackage().getName();
	}

	private String extractFQN() {
		return typeReference.getName();
	}

	private boolean extractIsPublic() {
		return Modifier.isPublic( typeReference.getModifiers() );
	}

	private String extractName() {
		return typeReference.getSimpleName();
	}

	public String packageName;
	
	public final String name;
	
	public String fqn;
	
	public final boolean isPublic;
	
	

	public final List<AnnotationModel> anntoations;
	
	public final List<FieldModel> fields;

	public final List<MethodModel> methods;

	private List<MethodModel> getter;

}
