package com.chatting.model;

import java.util.Date;

public class PlayerModel implements Comparable<PlayerModel> {
	String username;
	Integer id;
	Integer point;
	Long lastTime;

	public PlayerModel(String username, Integer point) {
		super();
		this.username = username;
		this.point = point;
	}

	public PlayerModel(String username) {
		super();
		this.username = username;
		this.point = 0;
		this.lastTime = 0L;
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
		Date d = new Date();
		this.lastTime = d.getTime();
	}

	@Override
	public int compareTo(PlayerModel player2) {
		if (point == player2.point) {
			return Long.compare(lastTime, player2.lastTime);
		} else {
			return Integer.compare(point, player2.point);
		}
	}

}
