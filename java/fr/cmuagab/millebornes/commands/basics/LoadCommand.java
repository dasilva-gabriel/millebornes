package fr.cmuagab.millebornes.commands.basics;

import java.util.HashMap;

import fr.cmuagab.millebornes.MilleBornes;
import fr.cmuagab.millebornes.commands.CommandHandler;
import fr.cmuagab.millebornes.handlers.Team;
import fr.cmuagab.millebornes.handlers.Team.TeamColors;

public class LoadCommand {

	@CommandHandler(command = "load", description = "Charger les équipes depuis la database")
	public void onLoad(String line) {
	
		if(MilleBornes.getInstance().getTeamsManager().getTeams().size() <= 0) {
			
			HashMap<TeamColors, Team> teams = MilleBornes
					.getInstance()
					.getDatabases()
					.getTeams()
					.loadTeams();
			
			if(teams.size() > 0) {
				
				MilleBornes.getInstance().getLogger().line("Loader");
				MilleBornes.getInstance().getLogger().info("Chargement depuis la base de donnée...");
				
				for(Team t : teams.values()) {
					MilleBornes.getInstance().getLogger().println("> Equipe "+t.getColor().name()+" ("+t.getName()+") chargée.");
					t.build();
				}
				
			} else {
				MilleBornes.getInstance().getLogger().warn("Aucune équipe ne peut être chargée, la BDD est vide.");
			}
			
		} else {
			MilleBornes.getInstance().getLogger().warn("Il y a des équipes qui sont en train d'être utilisées. Impossible de charger de nouvelles équipes car elles seront écrasées.");
		}
		
	}
	
}
