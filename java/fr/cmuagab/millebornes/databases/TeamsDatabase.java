package fr.cmuagab.millebornes.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import fr.cmuagab.millebornes.handlers.Team;
import fr.cmuagab.millebornes.handlers.Team.TeamColors;
import fr.cmuagab.millebornes.utils.MySQL;

public class TeamsDatabase {
	
	private static final String TABLE_NAME = "teams";
	
	private static final String COLUMN_COLOR = "color";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_LEADER = "leader";
	private static final String COLUMN_COINS = "coins";
	private static final String COLUMN_DISTANCE = "distance";
	private static final String COLUMN_GO = "go";
	private static final String COLUMN_LIMITED = "limited";
	private static final String COLUMN_PROBLEM1 = "problem1";
	private static final String COLUMN_PROBLEM2 = "problem2";
	private static final String COLUMN_PROBLEM3 = "problem3";
	
	public TeamsDatabase() {
		MySQL.prepareStatement("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_COLOR+" VARCHAR(255), "+COLUMN_NAME+" VARCHAR(255), "+COLUMN_LEADER+" VARCHAR(255), "+COLUMN_COINS+" INT(65), "+COLUMN_DISTANCE+" INT(65), "+COLUMN_GO+" BOOLEAN, "+COLUMN_LIMITED+" BOOLEAN, "+COLUMN_PROBLEM1+" BOOLEAN, "+COLUMN_PROBLEM2+" BOOLEAN, "+COLUMN_PROBLEM3+" BOOLEAN) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
	}
	
	public void saveUser(Team t) {
		MySQL.reconnect();
		try {
			if(MySQL.contains(TABLE_NAME, COLUMN_COLOR, t.getColor().name(), false, false)){
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE "+TABLE_NAME+" SET "+COLUMN_NAME+" = ?, "+COLUMN_LEADER+" = ?, "+COLUMN_COINS+" = ?, "+COLUMN_DISTANCE+" = ?, "+COLUMN_GO+" = ?, "+COLUMN_LIMITED+" = ?, "+COLUMN_PROBLEM1+" = ?, "+COLUMN_PROBLEM2+" = ?, "+COLUMN_PROBLEM3+" = ? WHERE "+COLUMN_COLOR+" = ?");
				
				ps.setString(1, t.getName());
				ps.setString(2, t.getLeader());
				ps.setInt(3, t.getCoinsWithoutSql());
				ps.setInt(4, t.getDistance());
				ps.setBoolean(5, t.isGo());
				ps.setBoolean(6, t.isLimited());
				ps.setBoolean(7, t.isProblem1());
				ps.setBoolean(8, t.isProblem2());
				ps.setBoolean(9, t.isProblem3());
				ps.setString(10, t.getColor().name());
				
				ps.execute();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertTeam(Team t) {
		MySQL.reconnect();
		
		try {
			
			if(!MySQL.contains(TABLE_NAME, COLUMN_COLOR, t.getColor().name(), false, false)){
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO "+TABLE_NAME+" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, t.getColor().name());
				ps.setString(2, t.getName());
				ps.setString(3, t.getLeader());
				ps.setInt(4, t.getCoinsWithoutSql());
				ps.setInt(5, t.getDistance());
				ps.setBoolean(6, t.isGo());
				ps.setBoolean(7, t.isLimited());
				ps.setBoolean(8, t.isProblem1());
				ps.setBoolean(9, t.isProblem2());
				ps.setBoolean(10, t.isProblem3());
				ps.execute();
				ps.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getCoins(Team t) {
		MySQL.reconnect();
		try {
			
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_COLOR+" = ?");
			ps.setString(1, t.getColor().name());
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				int i = result.getInt(COLUMN_COINS);
				t.setCoins(i);
				return i;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void setCoins(Team t) {
		MySQL.reconnect();
		try {
			if(MySQL.contains(TABLE_NAME, COLUMN_COLOR, t.getColor().name(), false, false)){
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE "+TABLE_NAME+" SET "+COLUMN_COINS+" = ? WHERE "+COLUMN_COLOR+" = ?");
				
				ps.setInt(1, t.getCoinsWithoutSql());
				ps.setString(2, t.getColor().name());
				
				ps.execute();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<TeamColors, Team> loadTeams() {
		MySQL.reconnect();
		HashMap<TeamColors, Team> res = new HashMap<TeamColors, Team>();
		try {
			
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+TABLE_NAME);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				
				TeamColors tc = TeamColors.fromString(result.getString(COLUMN_COLOR));
				if (tc != null && !res.containsKey(tc)) {

					res.put(tc, new Team(tc, result.getString(COLUMN_NAME), result.getString(COLUMN_LEADER),
							result.getInt(COLUMN_COINS), result.getInt(COLUMN_DISTANCE), result.getBoolean(COLUMN_GO),
							result.getBoolean(COLUMN_LIMITED), result.getBoolean(COLUMN_PROBLEM1),
							result.getBoolean(COLUMN_PROBLEM2), result.getBoolean(COLUMN_PROBLEM3)));
				}

			}
			result.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
