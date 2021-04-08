package fr.cmuagab.millebornes.commands.basics;

import fr.cmuagab.millebornes.MilleBornes;
import fr.cmuagab.millebornes.commands.CommandHandler;
import fr.cmuagab.millebornes.handlers.Team;
import fr.cmuagab.millebornes.handlers.Team.TeamColors;
import fr.cmuagab.millebornes.utils.CommandUtil;
import fr.cmuagab.millebornes.utils.ConsoleColors;

public class CreateTeamCommand {

	@CommandHandler(command = "create", description = "Créer une équipe")
	public void onCreateTeam(String line) {
	
		String[] args = CommandUtil.getArgs(line);
		
		if(args.length >= 3) {
			
			String color = args[0];
			String name = args[1];
			String leader = args[2];
			
			int coins = 0;
			if(args.length >= 4) {
				try {
					coins = Integer.valueOf(args[3]);
				} catch (Exception e) {
					MilleBornes.getInstance().getLogger().info("L'argument \""+args[3]+"\" n'a pas été reconnu comme un entier. Les points ont été définis a 0.");
				}
			}
			
			TeamColors tc = TeamColors.fromString(color);
			if(tc == null) {
				MilleBornes.getInstance().getLogger().warn("La couleur \""+color+"\" n'est pas valide.");
			} else if(MilleBornes.getInstance().getTeamsManager().getTeams().containsKey(tc)) {
				MilleBornes.getInstance().getLogger().warn("La couleur \""+color+"\" est déjà utilisée.");
			} else {
				MilleBornes.getInstance().getLogger().info(ConsoleColors.GREEN+"L'équipe \""+tc.name()+"\" a été créée.");
				Team t = new Team(tc, name, leader, coins, 0, false, false, false, false, false);
				t.build();
			}
			
		} else {
			MilleBornes.getInstance().getLogger().info("Usage: "+ConsoleColors.WHITE_UNDERLINED+"create"+ConsoleColors.WHITE+"(<"+
					ConsoleColors.YELLOW+"team color"+ConsoleColors.WHITE+">, <"+ConsoleColors.YELLOW+"nom"+ConsoleColors.WHITE+">, <"+ConsoleColors.YELLOW+"leader"+ConsoleColors.WHITE+">, "
							+ "[" +ConsoleColors.GREEN+"points"+ConsoleColors.WHITE+"])"
					);
		}
		
	}
	
}
