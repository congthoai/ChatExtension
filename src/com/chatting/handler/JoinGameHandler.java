package com.chatting.handler;

import java.util.List;

import com.chatting.constant.Constant;
import com.chatting.provider.ZoneProvider;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class JoinGameHandler extends BaseClientRequestHandler{

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		System.out.println("Join Game: " + sender.getName());
        List<User> users = ZoneProvider.getListUser();
        
        if(users.size() < 2) {
        	System.out.println("Wait..");
        	return;
        }
        
        if(users.size() > 2) {
        	System.out.println("Full Member, logout..");
        	send(Constant.SEND_EVENT.LOG_OUT, null, sender);
        	return;
        }
                
        // Start Game
        if(users.size() == 2) {
        	ISFSObject obj = new SFSObject(); 
        	obj.putUtfString("player1", users.get(0).getName());
        	obj.putInt("player1Id", users.get(0).getId());
        	obj.putUtfString("player2", users.get(1).getName());
        	obj.putInt("player2Id", users.get(1).getId());
        	
        	send(Constant.SEND_EVENT.START_GAME, obj, sender);
        	return;
        }
	}
	
	
}
