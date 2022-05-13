package com.chatting.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.chatting.model.GameModel;
import com.chatting.model.PlayerModel;
import com.smartfoxserver.v2.entities.User;

public class GameProvider {
	private static GameProvider instance = null;

	List<GameModel> games;
	List<User> waitingList;

	private GameProvider() {
		games = new ArrayList<>();
		waitingList = new ArrayList<>();
	}

	public static GameProvider getInstance() {
		if (instance == null) {
			instance = new GameProvider();
		}

		return instance;
	}

	public static void addGames(GameModel game) {
		instance.games.add(game);
	}

	public static List<User> getWaitingList() {
		return instance.waitingList;
	}

	public static void addWaitingList(User user) {
		instance.waitingList.add(user);
	}
	
	public static void addWaitingList(List<User> uList) {
		uList.forEach(u -> {
			instance.waitingList.add(u);
		});
	}

	public static List<User> getAnotherUser(int numPlayer) {
		List<User> uList = new ArrayList<>();
		Random rand = new Random();
		try {	
			while(numPlayer > 1 && instance.waitingList.size() > 0) {
				User oPlayer = instance.waitingList.get(rand.nextInt(instance.waitingList.size()));
				instance.waitingList.remove(oPlayer);
				uList.add(oPlayer);
				numPlayer--;
			}
			
			return uList;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return uList;
	}

	public static GameModel getGameByPlayerId(int playerId) {
		for (GameModel game : instance.games) {
			for (Map.Entry<Integer, PlayerModel> entry : game.getPlayers().entrySet()) {
				if (entry.getKey() == playerId) {
					return game;
				}
			}
		}
		return null;
	}
	
	public static void addPoint(GameModel game, int playerId) {
		game.getPlayers().get(playerId).addPoint();
	}
}
