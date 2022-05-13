package com.chatting.model;

public class PlayerModel {
	String username;
	int point;

	public PlayerModel(String username) {
		this.username = username;
		this.point = 0;
	}

	public String getUsername() {
		return username;
	}

	public int getPoint() {
		return point;
	}

	public void addPoint() {
		this.point++;
	}

}
