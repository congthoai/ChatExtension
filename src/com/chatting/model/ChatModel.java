package com.chatting.model;

import com.chatting.constant.Constant;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class ChatModel {
	String username;
	String message;
	
	public ChatModel(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	
	public ISFSObject toSFSObject() {
		ISFSObject so = new SFSObject();
		so.putUtfString(Constant.CHAT_MODEL.USERNAME, username);
        so.putUtfString(Constant.CHAT_MODEL.MESSAGE, message);
        
		return so;
	}
}
