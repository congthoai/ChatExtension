package com.chatting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.smartfoxserver.v2.entities.User;

public class GameModel {

	private Map<Integer, PlayerModel> players = new HashMap<>();
	private List<User> users = new ArrayList<>();
	private List<Integer> serverNumberList = new ArrayList<>();
	private Integer serverRandomNumber;
	private boolean endGame = false;

	public GameModel(User p1, User p2) {
		super();
		players.put(p1.getId(), new PlayerModel(p1.getName()));
		players.put(p2.getId(), new PlayerModel(p2.getName()));
		users.add(p1);
		users.add(p2);
		this.endGame = false;
		this.serverNumberList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			this.serverNumberList.add(i);
		}
	}

	public Map<Integer, PlayerModel> getPlayers() {
		return players;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Integer> getServerNumberList() {
		return serverNumberList;
	}

	public Integer getServerRandomNumber() {
		return serverRandomNumber;
	}

	public void setServerRandomNumber(Integer serverNumber) {
		this.serverRandomNumber = serverNumber;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public int removeAndRandomNumberInList(Integer num) {
		// Remove
		this.serverNumberList.remove(num);

		if (serverNumberList.size() == 0) {
			endGame = true;
			return -999;
		}

		// Random
		Random rand = new Random();
		int randNum = this.serverNumberList.get(rand.nextInt(this.serverNumberList.size()));
		this.serverRandomNumber = randNum;
		return randNum;
	}

	public PlayerModel getWinner() {
		return players.get(users.get(0).getId()).compareTo(players.get(users.get(1).getId())) > 0
				? players.get(users.get(0).getId())
				: players.get(users.get(1).getId());
	}
	
}
