package com.touchableheroes.fabrique.sdk.param;

import com.touchableheroes.fabrique.sdk.validate.Validator;

public @interface Param {

	public String name();
	
	public boolean required() default false;
	
	public Class<? extends Validator> validator() default DEFAULT.class;
	
	public static final class DEFAULT implements Validator {} 
	
}
