package com.chatting;

import java.util.List;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ChattingHandler extends BaseClientRequestHandler{

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		// Get the client parameters
		trace("ZOOOOOOOOOOOOOOOO");
        String username = params.getUtfString("username");
        String message = params.getUtfString("message");
        
        ChatExtension ext = ZoneProvider.getInstance().getExt();

        // Create a response object
        ISFSObject resObj = new SFSObject(); 
        resObj.putUtfString("username", username);
        resObj.putUtfString("message", message);
        resObj.putSFSObject("ChatModel", new ChatModel(username, message).toSFSObject());
        
        send("chatting", resObj, (List<User>) ext.getParentZone().getUserList());
	}

}
