package com.touchableheroes.fabrique.sdk.generate.android;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Jackson2Helper;
import com.github.jknack.handlebars.JsonNodeValueResolver;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.ValueResolver;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.context.MethodValueResolver;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.touchableheroes.fabrique.sdk.android.model.AndroidAppConfig;
import com.touchableheroes.fabrique.sdk.android.model.App;
import com.touchableheroes.fabrique.sdk.android.model.Generate;
import com.touchableheroes.fabrique.sdk.generate.Generator;

@Generate( "AndroidManifest.xml" )
public class AndroidAppManifestGenerator extends Generator {

	
	public void generate(final App app) {
		final TemplateLoader loader =
				new ClassPathTemplateLoader();
		final Handlebars handlebars = new Handlebars(loader);
		
		final Generate generatorConfig = this.getClass().getAnnotation( Generate.class );
		if( generatorConfig == null )
			throw new IllegalStateException( "GeneratorImpl should use @Generate to set config." );
		
		final String path = generatorConfig.value();
		final File file = new File( "c:\\temp\\" + path );
		
		FileWriter writer = null;
		try {
			writer = new FileWriter( file );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			
//			handlebars.registerHelper("json", Jackson2Helper.INSTANCE);
//			handlebars.registerHelpers( )
			
			final Template template = handlebars.compile( "templates/android/AndroidManifest.xml" );
		
			final Map<String, Object> map = createCtx(app);
			
			
			
			
			// ------------- RESOLVER :::

			ValueResolver resolver = new ValueResolver() {
			    	
				public Object resolve(Object context, String name) {
					try {
						final Class<? extends Object> type = context.getClass();
						final Method method = type.getMethod( name );
						
//						type.
						try {
							Object result = method.invoke(context);
							
							String rval = String.valueOf( result );
							
							return rval;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// TODO Auto-generated method stub
					return "";
				}
				
				public Set<Entry<String, Object>> propertySet(Object context) {
//					HashSet<String, Object> rval = null;
					
					// TODO Auto-generated method stub
					return null;
				}
			};			
			
			
			
			
			
			
			
			
			
			final Context ctx = Context.newBuilder( map )
				    .resolver(
					        MapValueResolver.INSTANCE,
					        JavaBeanValueResolver.INSTANCE,
					        MethodValueResolver.INSTANCE,
					        JsonNodeValueResolver.INSTANCE,
					        resolver
					   )
				   .build();			
			
			String applyied = template.apply( ctx );
			
			System.out.println("applied: " + applyied);
			System.out.println("ready!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
//	final TemplateLoader loader =
//			new ClassPathTemplateLoader( "/templates", ".tmpl" );
//	final Handlebars handlebars = new Handlebars(loader);
//	
//	final Generate generatorConfig = this.getClass().getAnnotation( Generate.class );
//	if( generatorConfig == null )
//		throw new IllegalStateException( "GeneratorImpl should use @Generate to set config." );
//	
//	final String path = generatorConfig.value();
//	final File file = new File( "c:\\temp\\" + path );
//	
//	FileWriter writer = null;
//	try {
//		writer = new FileWriter( file );
//	} catch (IOException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	
//	try {
//		final Template template = handlebars.compile( "android/AndroidManifest.xml" );
//	
//		final Map<String, Object> ctx = createCtx(app);
//		
//		template.apply(app, writer);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

	
	
	
	
	
	
	
	
	private Map<String, Object> createCtx(final App app) {
		final AndroidAppConfig config = app.getClass().getAnnotation( AndroidAppConfig.class );
		
		final Map<String, Object> rval = new HashMap<String, Object>();
//		Context ctx = Context.newContext(rval);
//		ctx.data(name, value)
		
//		config.versionCode();
		
		class T {
			public int getVersionCode() {
				return 100001;
			}
		};

//		JSONPObject json = new JSONPObject("test", new JSON);
				
//				JsonFactory().createParser( "{ 'a' : 'B' }").
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		final ObjectNode root = JsonNodeFactory.instance.objectNode();
		root.set( "useVersion", JsonNodeFactory.instance.numberNode( 1234) );
//		final ArrayNode root = JsonNodeFactory.instance.arrayNode();
//		root.
		
		
		
		
		rval.put( "test2", map);
		rval.put( "test", new T() );
		rval.put( "app", config );
		rval.put( "jsval", root );
		rval.put( "versionCode", "100" );

		
		return rval;
	}

	private void render(Object data) {
		// template wird mit mustache und data gerendert.
	}
	
}
