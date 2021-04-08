package fr.cmuagab.millebornes.commands.basics;

import fr.cmuagab.millebornes.MilleBornes;
import fr.cmuagab.millebornes.commands.CommandHandler;
import fr.cmuagab.millebornes.handlers.Team;
import fr.cmuagab.millebornes.handlers.Team.TeamColors;
import fr.cmuagab.millebornes.utils.CommandUtil;
import fr.cmuagab.millebornes.utils.ConsoleColors;
import fr.cmuagab.millebornes.utils.Logger;

public class TeamCommand {
	
	@CommandHandler(command = "team", description = "Obtenir des informations sur une/des équipe.s")
	public void team(String line) {
		String[] args = CommandUtil.getArgs(line);
		
		if(args.length == 1 && args[0].equalsIgnoreCase("all")) {
			
			if(MilleBornes.getInstance().getTeamsManager().getTeams().size() == 0) {
				MilleBornes.getInstance().getLogger().info("Aucune équipe active.");
			} else {
				MilleBornes.getInstance().getLogger().line("Liste des équipes actives");
				
				for(Team t : MilleBornes.getInstance().getTeamsManager().getTeams().values()) {
					MilleBornes.getInstance().getLogger().println("> "+ConsoleColors.WHITE_BOLD+t.getColor().name()+ConsoleColors.WHITE+
							" ("+ConsoleColors.YELLOW+t.getName()+ConsoleColors.WHITE+")");
				}
				
			}
			
			
			
		} else if(args.length != 0) {
			
			for(String arg : args) {
				TeamColors tc = TeamColors.fromString(arg);
				if(tc == null) {
					MilleBornes.getInstance().getLogger().warn("La couleur \""+arg+"\" n'est pas valide.");
				} else if (!MilleBornes.getInstance().getTeamsManager().getTeams().containsKey(tc)) {
					MilleBornes.getInstance().getLogger().warn("L'équipe \""+tc.name()+"\" n'a pas été créée.");
				} else {
					Team t = MilleBornes.getInstance().getTeamsManager().getTeams().get(tc);
					
					Logger l = MilleBornes.getInstance().getLogger();
					l.info(">>> Equipe " + tc.name()+":");
					l.println("> Nom: " + t.getName());
					l.println("> Chef: " + t.getLeader());
					l.println("> Distance: " + t.getDistance());
					l.println("> Points: " + t.getCoins());
					l.println("> Feu vert ? " + t.isGo());
					l.println("> Limitation de distance ? " + t.isLimited());
					l.println("> Malus 1 ? " + t.isProblem1());
					l.println("> Malus 2 ? " + t.isProblem2());
					l.println("> Malus 3 ? " + t.isProblem3());
				}
			}
			
		} else {
			MilleBornes.getInstance().getLogger().info("Usage: "+ConsoleColors.WHITE_UNDERLINED+"team"+ConsoleColors.WHITE+"(<"+
					ConsoleColors.YELLOW+"all|team color"+ConsoleColors.WHITE+">, ["+ConsoleColors.YELLOW+"team_color..."+ConsoleColors.WHITE+"])"
					);
		}
		
	}

}
