package com.chatting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.smartfoxserver.v2.entities.User;

public class GameModel {

	private Map<Integer, PlayerModel> players = new HashMap<>();
	private List<Integer> serverNumberList = new ArrayList<>();
	private Integer serverRandomNumber;
	private boolean endGame = false;
	private Random rand;

	public GameModel(List<User> uList, Random rand) {
		uList.forEach(u -> { players.put(u.getId(), new PlayerModel(u.getName())); });
		for (int i = 0; i < 10; i++) {
			this.serverNumberList.add(i);
		}
		this.serverRandomNumber = this.serverNumberList.get(rand.nextInt(this.serverNumberList.size()));
		this.rand = rand;
	}
	
	public Set<Integer> getPlayerIds() {
		return players.keySet();
	}

	public Map<Integer, PlayerModel> getPlayers() {
		return players;
	}

	public List<Integer> getServerNumberList() {
		return serverNumberList;
	}

	public Integer getServerRandomNumber() {
		return serverRandomNumber;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public boolean removeAndRandomNumberInList(Integer num) {	
		if (serverRandomNumber == num && this.serverNumberList.remove(num)) {
			if(this.serverNumberList.size() == 0) {
				this.endGame = true;
				return true;
			} 
			
			this.serverRandomNumber = this.serverNumberList.get(rand.nextInt(this.serverNumberList.size()));
			return true;
		}
		return false;
	}

	public List<PlayerModel> getWinners() {
		List<PlayerModel> winList = new ArrayList<>();
		int winPoint = players.values().stream().map(c -> c.point).max((c1, c2) -> c1 - c2).orElse(0);
		
		players.values().forEach(item ->  {
			if(item.getPoint() >= winPoint) {
				winList.add(item);
			}
		}) ;
	
		return winList;
	}

}
