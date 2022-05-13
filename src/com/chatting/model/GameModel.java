package com.chatting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameModel {

	private Map<Integer, Integer> players;
	private List<Integer> serverNumberList;
	private Integer serverRandomNumber;
	private boolean endGame = false;
	private Random rand;

	public GameModel(Set<Integer> uList, Random rand) {
		this.rand = rand;
		this.players = new HashMap<>();		
		this.serverNumberList = IntStream.range(0, 10).boxed().collect(Collectors.toList());		
		this.serverRandomNumber = this.serverNumberList.get(rand.nextInt(this.serverNumberList.size()));	
		uList.forEach(uid -> players.put(uid, 0));
	}
	
	public Set<Integer> getPlayerIds() {
		return players.keySet();
	}

	public Map<Integer, Integer> getPlayers() {
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
			} else {
				this.serverRandomNumber = this.serverNumberList.get(rand.nextInt(this.serverNumberList.size()));
			}	
			return true;
		}
		return false;
	}

	public List<Integer> getWinners() {
		List<Integer> winList = new ArrayList<>();
		int winPoint = players.values().stream().map(c -> c).max((c1, c2) -> c1 - c2).orElse(0);
		
		players.keySet().forEach(uid ->  {
			if(players.get(uid) >= winPoint) {
				winList.add(players.get(uid));
			}
		});
	
		return winList;
	}

}
