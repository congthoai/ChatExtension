package com.chatting.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.chatting.constant.Constant;
import com.chatting.model.GameModel;
import com.chatting.model.PlayerModel;
import com.smartfoxserver.v2.entities.User;

public class GameProvider {
	private static GameProvider instance = null;

	List<GameModel> games;
	List<User> waitingList;
	Random random = new Random();

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

	public static void addWaitingList(User user) {
		instance.waitingList.add(user);
	}
	
	public static void removeWaitingList(List<User> uList) {
		uList.forEach(u ->instance.waitingList.remove(u));
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
	
	public static Random getRandom() {
		return instance.random;
	}
	
	public static List<User> join(User user) {
		instance.waitingList.add(user);
		
		if (instance.waitingList.size() < Constant.GAME_CONFIG.NUM_PLAYER) {
			return null;
		}

		return instance.waitingList.stream().limit(Constant.GAME_CONFIG.NUM_PLAYER).collect(Collectors.toList());
	}
}
