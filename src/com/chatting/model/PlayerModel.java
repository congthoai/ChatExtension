package com.chatting.model;

public class PlayerModel {
	String username;
	Integer point;

	public PlayerModel(String username) {
		this.username = username;
		this.point = 0;
	}

	public String getUsername() {
		return username;
	}

	public Integer getPoint() {
		return point;
	}

	public void addPoint() {
		this.point++;
	}

}
