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
		System.out.println("Join Game: " + sender.getName());
		List<User> uList = GameProvider.join(sender);
		
		if(uList == null) {
			System.out.println("Wait..");
			return;
		} 			

		// Start Game
		newGame(uList);
	}

	public void newGame(List<User> uList) {
		GameProvider.removeWaitingList(uList);
		GameModel game = new GameModel(uList, GameProvider.getRandom());
		game.removeAndRandomNumberInList(-1);
		GameProvider.addGames(game);

		ISFSObject obj = new SFSObject();
		obj.putInt(Constant.GAME_MODEL.RANDOM_NUMBER, game.getServerRandomNumber());

		send(Constant.SEND_EVENT.NOT_MATCH, obj, uList);
		return;
	}

}
