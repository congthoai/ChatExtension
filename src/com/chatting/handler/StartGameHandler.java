package com.chatting.handler;

import java.util.HashMap;
import java.util.List;

import com.chatting.constant.Constant;
import com.chatting.model.PlayerModel;
import com.chatting.provider.GameProvider;
import com.chatting.provider.ZoneProvider;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class StartGameHandler extends BaseClientRequestHandler{

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		GameProvider.getInstance().setPlayers(new HashMap<Integer, PlayerModel>());
		GameProvider.getInstance().getPlayers().put(params.getInt("player1Id"), new PlayerModel(params.getUtfString("player1"), 0));
		GameProvider.getInstance().getPlayers().put(params.getInt("player2Id"), new PlayerModel(params.getUtfString("player2"), 0));
		GameProvider.getInstance().setNumberList(params.getInt("arraySize"));
		GameProvider.getInstance().removeAndRandomNumberInList(null);
		GameProvider.getInstance().setStart(true);
		
		ISFSObject obj = new SFSObject(); 
    	obj.putInt("currentNumber", GameProvider.getInstance().getCurrentNumber());
    	
    	List<User> users = ZoneProvider.getListUser();    	
    	send(Constant.SEND_EVENT.NOT_MATCH, obj, users);
	}	
}
