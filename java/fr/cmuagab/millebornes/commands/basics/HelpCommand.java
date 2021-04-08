package fr.cmuagab.millebornes.commands.basics;

import fr.cmuagab.millebornes.MilleBornes;
import fr.cmuagab.millebornes.commands.CommandHandler;
import fr.cmuagab.millebornes.utils.ConsoleColors;

public class HelpCommand {
	
	@CommandHandler(command = "help", description = "Affiche les commandes")
	public void onHelp(String line) {
		MilleBornes.getInstance().getLogger().line("Liste des commandes");
		
		MilleBornes.getInstance().getCommandMap().getCommands().forEach(cmd -> {
			
			MilleBornes.getInstance().getLogger().println(ConsoleColors.WHITE_BOLD + "> "
					+ ConsoleColors.BLUE_BOLD + cmd.getCommand()+"()"+ConsoleColors.RESET+" "+cmd.getDescription());
			
		});
	}

}
