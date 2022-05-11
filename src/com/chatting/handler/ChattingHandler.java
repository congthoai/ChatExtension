package com.chatting.handler;

import java.util.List;

import com.chatting.constant.Constant;
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
        resObj.putSFSObject(Constant.CHAT_MODEL.MODEL_NAME, params2ChatModel(params));
        
        List<User> users = ZoneProvider.getListUser();
        
        send(Constant.SEND_EVENT.CHATTING_EVENT, resObj, users);
	}
	
	public ISFSObject params2ChatModel(ISFSObject params) {
		String username = params.getUtfString(Constant.CHAT_MODEL.USERNAME);
        String message = params.getUtfString(Constant.CHAT_MODEL.MESSAGE);
        
		return new ChatModel(username, message).toSFSObject();
	}

}
