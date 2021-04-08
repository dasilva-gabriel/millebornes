package fr.cmuagab.millebornes;

import fr.cmuagab.millebornes.commands.CommandMap;
import fr.cmuagab.millebornes.commands.basics.CoinsCommands;
import fr.cmuagab.millebornes.commands.basics.CreateTeamCommand;
import fr.cmuagab.millebornes.commands.basics.HelpCommand;
import fr.cmuagab.millebornes.commands.basics.LoadCommand;
import fr.cmuagab.millebornes.commands.basics.TeamCommand;
import fr.cmuagab.millebornes.databases.Databases;
import fr.cmuagab.millebornes.handlers.Shell;
import fr.cmuagab.millebornes.handlers.TeamsManager;
import fr.cmuagab.millebornes.utils.ConsoleColors;
import fr.cmuagab.millebornes.utils.Logger;

public class MilleBornes {
	
	private static MilleBornes instance;
	private Logger logger;
	private Databases databases;
	private CommandMap commandMap;
	private TeamsManager teamsManager;
	
	public static void main(String[] args) {
		new MilleBornes(args);
	}
	
	public MilleBornes(String[] args) {
		instance = this;
		
		logger = new Logger(System.in);
		teamsManager = new TeamsManager();
		commandMap = new CommandMap();
		
		initCmds();
		
		if(args.length == 4) {
			getLogger().info(ConsoleColors.WHITE_BOLD_BRIGHT + "Une configuration MySQL a été détectée.");
			databases = new Databases(args[0], args[1], args[2], args[3]);
		} else {
			databases = new Databases();
		}
		
		
		new Shell().running();
	}

	private void initCmds() {
		getCommandMap().registerCommand(new HelpCommand());
		getCommandMap().registerCommands(new LoadCommand(), new CreateTeamCommand());
		getCommandMap().registerCommands(new TeamCommand());
		getCommandMap().registerCommands(new CoinsCommands());
	}

	public static MilleBornes getInstance() {
		return instance;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public Databases getDatabases() {
		return databases;
	}
	
	public CommandMap getCommandMap() {
		return commandMap;
	}
	
	public TeamsManager getTeamsManager() {
		return teamsManager;
	}
	
}
