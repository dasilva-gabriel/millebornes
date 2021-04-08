package fr.cmuagab.millebornes.handlers;

import fr.cmuagab.millebornes.MilleBornes;

public class Shell {

	public Shell() {}
	
	public void running() {
		
		MilleBornes.getInstance().getLogger().info("Démarrage de la réception des commandes...");
		
		while(true) {
			
			MilleBornes.getInstance().getLogger().print("> ");
			
			String s = MilleBornes.getInstance().getLogger().getScanner().nextLine();
			s = s.toLowerCase();
			try {
				
				if(s.equals("exit")) {
					System.exit(0);
					continue;
				}
				
				MilleBornes.getInstance().getCommandMap().command(s);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
