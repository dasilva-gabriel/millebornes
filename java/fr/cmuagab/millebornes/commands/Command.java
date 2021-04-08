package fr.cmuagab.millebornes.commands;

import java.lang.reflect.Method;

public class Command {
	
	private final Object object;
	private final Method method;
	private final String command;
	private final String description;
	
	public Command(Object object, Method method, String command, String description) {
		this.object = object;
		this.method = method;
		this.command = command;
		this.description = description;
	}

	public Object getObject() {
		return object;
	}

	public Method getMethod() {
		return method;
	}

	public String getCommand() {
		return command;
	}

	public String getDescription() {
		return description;
	}
	
}
