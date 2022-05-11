package com.chatting.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.chatting.model.PlayerModel;

public class GameProvider {
	private static GameProvider instance = null;

	public List<Integer> numberList = new ArrayList<>();
	public Map<Integer, PlayerModel> players = new HashMap<>();
	public Integer currentNumber;
	public boolean isStart = false;

	private GameProvider() {}

	public static GameProvider getInstance() {
		if (instance == null) {
			instance = new GameProvider();
		}
		
		return instance;
	}

	public List<Integer> getNumberList() {
		return numberList;
	}

	public void setNumberList(int n) {
		this.numberList = new ArrayList<>();
		for (int i = 0; i < n || i < 10; i++) {
			this.numberList.add(i);
		}
	}
	
	public Map<Integer, PlayerModel> getPlayers() {
		return players;
	}

	public void setPlayers(Map<Integer, PlayerModel> players) {
		this.players = players;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public int getRandomNumberInList() {
		Random rand = new Random();
	    int randNum = this.numberList.get(rand.nextInt(this.numberList.size()));
	    this.currentNumber = randNum;
	    return randNum;
	}
}
