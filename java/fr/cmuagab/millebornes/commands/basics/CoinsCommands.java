package fr.cmuagab.millebornes.commands.basics;

import fr.cmuagab.millebornes.MilleBornes;
import fr.cmuagab.millebornes.commands.CommandHandler;
import fr.cmuagab.millebornes.handlers.Team;
import fr.cmuagab.millebornes.handlers.Team.TeamColors;
import fr.cmuagab.millebornes.utils.CommandUtil;

public class CoinsCommands {

	@CommandHandler(command = "coins", description = "Voir les points/coins a une équipe")
	public void coins(String line) {
		
		String[] args = CommandUtil.getArgs(line);
		
		if(args.length == 1) {
			
			TeamColors tc = TeamColors.fromString(args[0]);
			if(tc == null) {
				MilleBornes.getInstance().getLogger().warn("La couleur \""+args[0]+"\" n'est pas valide.");
			} else if (!MilleBornes.getInstance().getTeamsManager().getTeams().containsKey(tc)) {
				MilleBornes.getInstance().getLogger().warn("L'équipe \""+tc.name()+"\" n'a pas été créée.");
			} else {
				Team t = MilleBornes.getInstance().getTeamsManager().getTeams().get(tc);
				int coins = t.getCoins();
				MilleBornes.getInstance().getLogger().info("Solde de l'équipe "+t.getColor().name()+": " + coins);
			}
			
		}
		
	}
	
	@CommandHandler(command = "add_coins", description = "Ajouter des points/coins a une équipe")
	public void add(String line) {
		
		String[] args = CommandUtil.getArgs(line);
		
		if(args.length == 2) {
			
			int coins = 0;
			try {
				coins = Integer.valueOf(args[1]);
			} catch (Exception e) {
				MilleBornes.getInstance().getLogger().info("L'argument \""+args[1]+"\" n'a pas été reconnu comme un entier.");
			}
			
			if(coins <= 0) {
				MilleBornes.getInstance().getLogger().warn("L'entier \""+coins+"\" est nul ou n'est pas strictement positif.");
			} else {
				TeamColors tc = TeamColors.fromString(args[0]);
				if(tc == null) {
					MilleBornes.getInstance().getLogger().warn("La couleur \""+args[0]+"\" n'est pas valide.");
				} else if (!MilleBornes.getInstance().getTeamsManager().getTeams().containsKey(tc)) {
					MilleBornes.getInstance().getLogger().warn("L'équipe \""+tc.name()+"\" n'a pas été créée.");
				} else {
					Team t = MilleBornes.getInstance().getTeamsManager().getTeams().get(tc);
					t.addCoins(coins);
					MilleBornes.getInstance().getLogger().info("+"+coins+" pour l'équipe "+tc.name()+" ("+t.getName()+"). Nouveau solde: " + t.getCoins());
				}
			}
			
		}
		
	}
	
	@CommandHandler(command = "remove_coins", description = "Retirer des points/coins a une équipe")
	public void remove(String line) {
		
		String[] args = CommandUtil.getArgs(line);
		
		if(args.length == 2) {
			
			int coins = 0;
			try {
				coins = Integer.valueOf(args[1]);
			} catch (Exception e) {
				MilleBornes.getInstance().getLogger().info("L'argument \""+args[1]+"\" n'a pas été reconnu comme un entier.");
			}
			
			if(coins <= 0) {
				MilleBornes.getInstance().getLogger().warn("L'entier \""+coins+"\" est nul ou n'est pas strictement positif.");
			} else {
				TeamColors tc = TeamColors.fromString(args[0]);
				if(tc == null) {
					MilleBornes.getInstance().getLogger().warn("La couleur \""+args[0]+"\" n'est pas valide.");
				} else if (!MilleBornes.getInstance().getTeamsManager().getTeams().containsKey(tc)) {
					MilleBornes.getInstance().getLogger().warn("L'équipe \""+tc.name()+"\" n'a pas été créée.");
				} else {
					Team t = MilleBornes.getInstance().getTeamsManager().getTeams().get(tc);
					
					if(t.getCoins()-coins < 0) {
						MilleBornes.getInstance().getLogger().warn("Le solde de cette équipe est insufisant.");
					} else {
						t.addCoins(coins);
						MilleBornes.getInstance().getLogger().info("-"+coins+" pour l'équipe "+tc.name()+" ("+t.getName()+"). Nouveau solde: " + t.getCoins());
					}
					
					
				}
			}
			
		}
		
	}
	
}
