package com.chatting.handler;

import com.chatting.constant.Game;
import com.chatting.model.GameModel;
import com.chatting.provider.GameProvider;
import com.chatting.provider.ZoneProvider;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ActionGameHandler extends BaseClientRequestHandler {

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		if(!params.containsKey(Game.MODEL.PLAYER_CHOICE)) {
			return;
		}
		
		GameModel game = GameProvider.getGameByPlayerId(sender.getId());
		
		if (game == null) {
			return;
		}
		
		if (!game.removeAndRandomNumberInList(params.getInt(Game.MODEL.PLAYER_CHOICE))) {
			handleUnmatch(sender, game);
			return;
		}
		
		handleMatch(sender, game);
		
		if (game.isEndGame()) {
			handleWin(sender, game);
		}
	}
	
	private void handleMatch(User sender, GameModel game) {	
		GameProvider.addPoint(game, sender.getId());
			
		ISFSObject obj = new SFSObject();
		obj.putInt(Game.MODEL.RANDOM_NUMBER, game.getServerRandomNumber());
		send(Game.CMD.MATCH, obj, ZoneProvider.getGameUsers(game.getPlayerIds()));
	}
	
	private void handleUnmatch(User sender, GameModel game) {
		ISFSObject obj = new SFSObject();
		obj.putInt(Game.MODEL.RANDOM_NUMBER, game.getServerRandomNumber());
		send(Game.CMD.NOT_MATCH, obj, sender);
	}
	
	private void handleWin(User sender, GameModel game) {
		ISFSObject obj = new SFSObject();
		obj.putIntArray(Game.MODEL.WINNER_LIST, game.getWinners());
		
		send(Game.CMD.WIN_GAME, obj, ZoneProvider.getGameUsers(game.getPlayerIds()));
	}

}
