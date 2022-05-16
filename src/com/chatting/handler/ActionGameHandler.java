package com.chatting.handler;

import java.util.Set;

import com.chatting.constant.Config;
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
		
		String eventType = params.getUtfString(Game.MODEL.EVENT_TYPE);
		
		switch (eventType) {
		case Game.MODEL.JOIN:
			handleJoin(sender);
			break;
		case Game.MODEL.SEND:
			handleSend(sender, params);
			break;
		}
	}
	
	public void handleSend(User sender, ISFSObject params) {
		if (!params.containsKey(Game.MODEL.PLAYER_CHOICE)) {
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
	
	public void handleJoin(User sender) {
		if(GameProvider.join(sender.getId())) {
			newGame(GameProvider.startGame());
		}		
	}
	
	public void newGame(Set<Integer> uList) {
		if (uList.size() >= Config.GAME_CONFIG.NUM_PLAYER) {
			GameModel game = new GameModel(uList, GameProvider.getRandom());
			GameProvider.addGames(game);

			ISFSObject obj = new SFSObject();
			obj.putInt(Game.MODEL.RANDOM_NUMBER, game.getServerRandomNumber());

			send(Game.CMD.NOT_MATCH, obj, ZoneProvider.getGameUsers(uList));
		}
	}
	
}
