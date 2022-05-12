package com.chatting.handler;

import java.util.stream.Collectors;

import com.chatting.constant.Constant;
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
		GameModel game = GameProvider.getGameByPlayerId(sender.getId());
		
		if (game == null || game.isEndGame() || !params.containsKey(Constant.GAME_MODEL.PLAYER_CHOICE)) {
			return;
		}
		
		if (!game.removeAndRandomNumberInList(params.getInt(Constant.GAME_MODEL.PLAYER_CHOICE))) {
			unmatchHandler(sender, game);
			return;
		}
		
		matchHandler(sender, game);
	}
	
	private void matchHandler(User sender, GameModel game) {		
		game.getPlayers().get(sender.getId()).addPoint();
	
		if (game.getServerNumberList().size() == 0) {
			winHandler(sender, game);
			return;
		}
		
		ISFSObject obj = new SFSObject();
		obj.putInt(Constant.GAME_MODEL.RANDOM_NUMBER, game.getServerRandomNumber());
		send(Constant.SEND_EVENT.MATCH, obj, ZoneProvider.getGameUsers(game.getPlayerIds()));
	}
	
	private void unmatchHandler(User sender, GameModel game) {
		ISFSObject obj = new SFSObject();
		obj.putInt(Constant.GAME_MODEL.RANDOM_NUMBER, game.getServerRandomNumber());
		send(Constant.SEND_EVENT.NOT_MATCH, obj, sender);
	}
	
	private void winHandler(User sender, GameModel game) {
		game.setEndGame(true);
		ISFSObject obj = new SFSObject();
		obj.putUtfStringArray(Constant.GAME_MODEL.WINNER_LIST, game.getWinners().stream().map(item -> item.getUsername()).collect(Collectors.toList()));
		
		send(Constant.SEND_EVENT.WIN_GAME, obj, ZoneProvider.getGameUsers(game.getPlayerIds()));
	}

}
