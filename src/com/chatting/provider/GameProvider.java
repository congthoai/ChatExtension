package com.chatting.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.chatting.constant.Constant;
import com.chatting.model.GameModel;

public class GameProvider {
	private static GameProvider instance = null;

	List<GameModel> games;
	Set<Integer> waitingList;
	Random random = new Random();

	private GameProvider() {
		games = new ArrayList<>();
		waitingList = new HashSet<>();
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

	public static void addWaitingList(int uid) {
		instance.waitingList.add(uid);
	}
	
	public static Set<Integer> startGame() {

		if (instance.waitingList.size() < Constant.GAME_CONFIG.NUM_PLAYER) {
			return Collections.emptySet();
		}

		Set<Integer> uList = instance.waitingList.stream().limit(Constant.GAME_CONFIG.NUM_PLAYER).collect(Collectors.toSet()); 		
		Set<Integer> removedList = new HashSet<>();
		
		for(int u : uList) {
			if(!instance.waitingList.remove(u)) {
				removedList.forEach(rm -> instance.waitingList.add(rm));
				return Collections.emptySet();
			}
			removedList.add(u);
		}
		
		return uList;
	}

	public static GameModel getGameByPlayerId(int playerId) {
		for (GameModel game : instance.games) {
			for(int uid : game.getPlayerIds()) {
				if(uid == playerId) {
					return game;
				}
			}
		}
		return null;
	}
	
	public static void addPoint(GameModel game, int playerId) {
		game.getPlayers().put(playerId, game.getPlayers().get(playerId) + 1);
	}
	
	public static Random getRandom() {
		return instance.random;
	}
	
	public static boolean join(int uid) {
		return instance.waitingList.add(uid);
	}
}
