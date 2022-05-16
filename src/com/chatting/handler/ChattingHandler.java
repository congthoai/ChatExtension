package com.chatting.handler;

import java.util.List;

import com.chatting.constant.Chat;
import com.chatting.model.ChatModel;
import com.chatting.provider.ZoneProvider;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ChattingHandler extends BaseClientRequestHandler{

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {

        // Create a response object
        ISFSObject resObj = new SFSObject(); 
        resObj.putSFSObject(Chat.MODEL.MODEL_NAME, params2ChatModel(params));
        
        List<User> users = ZoneProvider.getListUser();
        
        send(Chat.CMD.CHATTING, resObj, users);
	}
	
	public ISFSObject params2ChatModel(ISFSObject params) {
		String username = params.getUtfString(Chat.MODEL.USERNAME);
        String message = params.getUtfString(Chat.MODEL.MESSAGE);
        
		return new ChatModel(username, message).toSFSObject();
	}

}
