package com.touchableheroes.fabrique.sdk.commands;

public class BuilderCommandRegistry extends CommandRegistry {

	
	public void register() {
		command( AndroidAppBuildProcess.class);
	}
	
}
