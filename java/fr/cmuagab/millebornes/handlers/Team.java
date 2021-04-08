package fr.cmuagab.millebornes.handlers;

import fr.cmuagab.millebornes.MilleBornes;

public class Team {

	public enum TeamColors {

		RED, BLUE, GREEN, ORANGE, YELLOW, BLACK, WHITE, PURPLE, PINK;
		
		public static TeamColors fromString(String s) {
			for(TeamColors tc: values()) {
				if(s.toLowerCase().equalsIgnoreCase(tc.name().toLowerCase())) {
					return tc;
				}
			}
			return null;
		}

	}

	private TeamColors color;
	private String name;
	private String leader;
	private int coins;
	
	private int distance;
	private boolean go;
	private boolean limited;
	private boolean problem1;
	private boolean problem2;
	private boolean problem3;
	
	public Team(TeamColors color, String name, String leader, int coins, int distance, boolean go, boolean limited,
			boolean problem1, boolean problem2, boolean problem3) {
		this.color = color;
		this.name = name;
		this.leader = leader;
		this.coins = coins;
		this.distance = distance;
		this.go = go;
		this.limited = limited;
		this.problem1 = problem1;
		this.problem2 = problem2;
		this.problem3 = problem3;
	}
	
	public Team(TeamColors color, String name, String leader) {
		this(color, name, leader, 0, 0, false, false, false, false, false);
	}
	
	
	public void build() {
		MilleBornes.getInstance().getTeamsManager().addTeam(this);
		MilleBornes.getInstance().getDatabases().getTeams().insertTeam(this);
	}
	
	public void save() {
		MilleBornes.getInstance().getDatabases().getTeams().saveUser(this);
	}
	
	public void reload() {
		MilleBornes.getInstance().getDatabases().getTeams().getCoins(this);
	}
	

	public TeamColors getColor() {
		return color;
	}

	public void setColor(TeamColors color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public int getCoins() {
		MilleBornes.getInstance().getDatabases().getTeams().getCoins(this);
		return coins;
	}
	
	public int getCoinsWithoutSql() {
		return coins;
	}

	public void addCoins(int coins) {
		this.coins = getCoins() + coins;
		MilleBornes.getInstance().getDatabases().getTeams().setCoins(this);
	}
	
	public void removeCoins(int coins) {
		this.coins = getCoins() - coins;
		MilleBornes.getInstance().getDatabases().getTeams().setCoins(this);
	}
	
	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isGo() {
		return go;
	}

	public void setGo(boolean go) {
		this.go = go;
	}

	public boolean isLimited() {
		return limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}

	public boolean isProblem1() {
		return problem1;
	}

	public void setProblem1(boolean problem1) {
		this.problem1 = problem1;
	}

	public boolean isProblem2() {
		return problem2;
	}

	public void setProblem2(boolean problem2) {
		this.problem2 = problem2;
	}

	public boolean isProblem3() {
		return problem3;
	}

	public void setProblem3(boolean problem3) {
		this.problem3 = problem3;
	}
	
}
