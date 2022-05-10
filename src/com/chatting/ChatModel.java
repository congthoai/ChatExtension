package com.chatting;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class ChatModel {
	String username;
	String message;
	String dateTime;
	
	public ChatModel(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ISFSObject toSFSObject() {
		ISFSObject so = new SFSObject();
		so.putUtfString("username", username);
        so.putUtfString("message", message);
        
		return so;
	}
}
