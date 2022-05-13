package com.chatting.handler;

import java.util.List;

import com.chatting.constant.Constant;
import com.chatting.model.GameModel;
import com.chatting.provider.GameProvider;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class JoinGameHandler extends BaseClientRequestHandler {

	@Override
	public void handleClientRequest(User sender, ISFSObject params) {
		
		if(!GameProvider.join(sender)) {
			return;
		}		
		
		// Start Game
		newGame(GameProvider.getPlayersAndRemoveWaitingList());
	}

	public void newGame(List<User> uList) {	
		GameModel game = new GameModel(uList, GameProvider.getRandom());
		GameProvider.addGames(game);

		ISFSObject obj = new SFSObject();
		obj.putInt(Constant.GAME_MODEL.RANDOM_NUMBER, game.getServerRandomNumber());

		send(Constant.SEND_EVENT.NOT_MATCH, obj, uList);
	}

}
