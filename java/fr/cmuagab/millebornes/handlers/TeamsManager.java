package fr.cmuagab.millebornes.handlers;

import java.util.HashMap;

import fr.cmuagab.millebornes.handlers.Team.TeamColors;

public class TeamsManager {
	
	private HashMap<TeamColors, Team> teams;
	
	public TeamsManager() {
		teams = new HashMap<>();
	}
	
	public HashMap<TeamColors, Team> getTeams() {
		return teams;
	}
	
	public Team getTeam(TeamColors teamcolor) {
		if(teams.containsKey(teamcolor)) {
			return teams.get(teamcolor);
		}
		return new Team(teamcolor, "Aucun Nom", "Aucun Chef");
	}
	
	public void addTeam(Team team) {
		teams.put(team.getColor(), team);
	}

}
