package com.chatting.handler;

import java.util.List;

import com.chatting.constant.Constant;
import com.chatting.model.GameModel;
import com.chatting.model.PlayerModel;
import com.chatting.provider.GameProvider;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ActionGameHandler extends BaseClientRequestHandler {

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		GameModel game = GameProvider.getGameByPlayerId(sender.getId());
		
		if (game == null || game.isEndGame()) {
			return;
		}
		
		if (!game.getServerRandomNumber().equals(params.getInt(Constant.GAME_MODEL.PLAYER_CHOICE))) {
			ISFSObject obj = new SFSObject();
			obj.putInt(Constant.GAME_MODEL.RANDOM_NUMBER, game.getServerRandomNumber());
			send(Constant.SEND_EVENT.NOT_MATCH, obj, sender);
			return;
		}
		
		matchHandler(sender, game);
	}

	private void matchHandler(User sender, GameModel game) {
		ISFSObject obj = new SFSObject();
		game.getPlayers().get(sender.getId()).addPoint();
		game.removeAndRandomNumberInList(game.getServerRandomNumber());
		List<User> users = game.getUsers();
		
		if (game.getServerNumberList().size() == 0) {
			game.setEndGame(true);
			PlayerModel winPlayer = game.getWinner();
			obj.putUtfString(Constant.GAME_MODEL.WINNER, winPlayer.getUsername());
			
			send(Constant.SEND_EVENT.WIN_GAME, obj, users);
			return;
		}
		
		obj.putInt(Constant.GAME_MODEL.RANDOM_NUMBER, game.getServerRandomNumber());
		send(Constant.SEND_EVENT.MATCH, obj, users);
	}

}
