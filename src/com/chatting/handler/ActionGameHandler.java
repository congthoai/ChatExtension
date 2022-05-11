package com.chatting.handler;

import java.util.List;
import java.util.Map;

import com.chatting.constant.Constant;
import com.chatting.model.PlayerModel;
import com.chatting.provider.GameProvider;
import com.chatting.provider.ZoneProvider;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ActionGameHandler extends BaseClientRequestHandler{

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		
		if(!GameProvider.getInstance().isStart()) {
			return;
		}
		
		List<User> users = ZoneProvider.getListUser(); 
		ISFSObject obj = new SFSObject(); 
		Integer currentNumber = GameProvider.getInstance().getCurrentNumber();
		
		if(!currentNumber.equals(params.getInt("playerChoice"))) {
			obj.putInt("currentNumber", GameProvider.getInstance().getCurrentNumber());
			send(Constant.SEND_EVENT.NOT_MATCH, obj, users);
			return;
		}
		
		GameProvider.getInstance().getPlayers().get(sender.getId()).addPoint();		
		GameProvider.getInstance().removeAndRandomNumberInList(currentNumber);
		
		if(GameProvider.getInstance().getNumberList().size() == 0) {
			PlayerModel winPlayer = getWinner(sender.getId());			
			obj.putUtfString("winnerPlayer", winPlayer.getUsername());
			
			send(Constant.SEND_EVENT.WIN_GAME, obj, users);
			return;
		}
			 
    	obj.putInt("currentNumber", GameProvider.getInstance().getCurrentNumber());
    	 	       
    	send(Constant.SEND_EVENT.MATCH, obj, users);
	}
	
	private PlayerModel getWinner(int currentPlayerId) {
		PlayerModel winPlayer = GameProvider.getInstance().getPlayers().get(currentPlayerId);
		for (Map.Entry<Integer, PlayerModel> entry : GameProvider.getInstance().getPlayers().entrySet()) {
		    if(entry.getValue().getPoint() > winPlayer.getPoint()) {
		    	winPlayer = entry.getValue();
		    }
		}
		return winPlayer;
	}
}
