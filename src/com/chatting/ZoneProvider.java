package com.chatting;

public class ZoneProvider {
	private static ZoneProvider instance = null;

	public ChatExtension ext;

	private ZoneProvider() {}

	public static ZoneProvider getInstance() {
		if (instance == null)
			instance = new ZoneProvider();

		return instance;
	}

	public ChatExtension getExt() {
		return ext;
	}

	public void setExt(ChatExtension ext) {
		this.ext = ext;
	}
	
	
}
