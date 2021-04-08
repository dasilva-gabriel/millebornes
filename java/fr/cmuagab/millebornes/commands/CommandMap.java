package fr.cmuagab.millebornes.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.cmuagab.millebornes.MilleBornes;
import fr.cmuagab.millebornes.utils.CommandUtil;

public final class CommandMap {

	private final List<Command> commands = new ArrayList<>();

	public List<Command> getCommands() {
		return commands;
	}
	
	public void registerCommand(Object cmd){
        for(Method method : cmd.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(CommandHandler.class)){
            	CommandHandler annotation = method.getAnnotation(CommandHandler.class);
                method.setAccessible(true);
                Command register = new Command(cmd, method, annotation.command(), annotation.description());
                commands.add(register);
            }
        }
    }
	
	public void registerCommands(Object... objects){
        for(Object object : objects) registerCommand(object);
    }
	
	public Command command(String entry) throws Exception {
		Command[] res = new Command[1];
		if(CommandUtil.isCommandWithArgs(entry)){
			String cmd = entry.split("\\(")[0];
			if(!invoke(entry, cmd, res)) {
				MilleBornes.getInstance().getLogger().warn("Commande inconnue.");
			}
		}

		return res[0];
	}
	

	private boolean invoke(String entry, String id, Command[] res) {
		
		
		commands.forEach(cmd -> {
			MilleBornes.getInstance().getLogger().info("REGISTER="+cmd.getCommand());
		});
		
		List<Command> cmd = commands.stream().filter(ac -> ac.getCommand().equalsIgnoreCase(id)).collect(Collectors.toList());
		if(cmd.size() > 0) {
			cmd.forEach(exec -> {
				Parameter[] parameters = exec.getMethod().getParameters();
				Object[] objects = new Object[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					if (parameters[i].getType() == String.class) objects[i] = entry;
				}
				
				try {
					exec.getMethod().invoke(exec.getObject(), objects);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				res[0] = exec;
				
			});
			return true;
		} else {
			return false;
		}
		
	}
}
