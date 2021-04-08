package fr.cmuagab.millebornes.databases;

import fr.cmuagab.millebornes.MilleBornes;
import fr.cmuagab.millebornes.utils.CommandUtil;
import fr.cmuagab.millebornes.utils.Logger;
import fr.cmuagab.millebornes.utils.MySQL;

public class Databases {
	
	private TeamsDatabase teams;
	
	public Databases(String host, String base, String username, String password) {
		MySQL.setHost(host);
		MySQL.setBase(base);
		MySQL.setUsername(username);
		MySQL.setPassword(password);
		
		if(!MySQL.connect()) {
			MilleBornes.getInstance().getLogger().warn("Attention! La configuration entrée n'est pas bonne. Lancement d'une nouvelle configuration...");
			launch();
		} else {
			teams = new TeamsDatabase();
		}
		
	}
	
	public Databases() {
		
		teams = new TeamsDatabase();
		launch();
	}
	
	private void launch() {
		Logger log = MilleBornes.getInstance().getLogger();
		log.line("Configuration MySQL");
		
		log.info("Lancement de la configuration pour MySQL...");
		
		String host, base, username, password = "";
		
		while(!MySQL.isConnected()) {
			log.println("> Adresse IP:");
			log.print(">> ");
			host = log.getScanner().nextLine();
			host = CommandUtil.clearString(host);
			
			if(!host.endsWith("/")) host = host + "/";
			
			log.println("> Nom de la base de donnée:");
			log.print(">> ");
			base = log.getScanner().nextLine();
			
			log.println("> Nom d'utilisateur:");
			log.print(">> ");
			username = log.getScanner().nextLine();
			
			log.println("> Mot de passe:");
			log.print(">> ");
			password = log.getScanner().nextLine();
			
			
			base = CommandUtil.clearString(base);
			username = CommandUtil.clearString(username);
			password = CommandUtil.clearString(password);
			
			MySQL.setHost(host);
			MySQL.setBase(base);
			MySQL.setUsername(username);
			MySQL.setPassword(password);
			
			if(!MySQL.connect()) {
				log.warn("MySQL n'a pas pu être connecté. Vérifiez les identifiants...");
			}
			
		}
		
		log.info("Configuration terminée.");
		log.println(" ");
	}
	
	
	
	public TeamsDatabase getTeams() {
		return teams;
	}

}
