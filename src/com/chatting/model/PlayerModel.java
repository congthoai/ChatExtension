package com.chatting.model;

public class PlayerModel {
	String username;
	Integer id;
	Integer point;

	public PlayerModel(String username, Integer point) {
		super();
		this.username = username;
		this.point = point;
	}

	public PlayerModel(String username) {
		super();
		this.username = username;
		this.point = 0;
	}

	public Integer getId() {
		return id;
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
