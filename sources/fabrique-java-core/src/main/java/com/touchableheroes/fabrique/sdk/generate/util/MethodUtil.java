package com.touchableheroes.fabrique.sdk.generate.util;

import java.lang.reflect.Method;

public class MethodUtil {

	/**
	 * Method has return value.
	 * Method has no params.
	 * 
	 * @param method
	 * @return
	 */
	public static final boolean isGetter(final Method method) {
		return (methodHasNoParams(method) && methodHasReturnValue(method));
	}

	private static boolean methodHasReturnValue(final Method method) {
		final Class<?> returnType = method.getReturnType();

		return (returnType != Void.class);
	}

	private static boolean methodHasNoParams(final Method method) {
		final Class<?>[] params = method.getParameterTypes();

		return (params == null || params.length < 1);

	}

}
