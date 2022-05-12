package com.chatting.handler;

import java.util.Arrays;
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
		List<User> waitingList = GameProvider.getWaitingList();

		if (waitingList.size() < 1) {
			System.out.println("Wait..");
			GameProvider.addWaitingList(sender);
			return;
		}

		User player2 = GameProvider.getAnotherUser();
		if (player2 == null) {
			return;
		}

		// Start Game
		newGame(sender, player2);
	}

	public void newGame(User u1, User u2) {
		GameModel game = new GameModel(u1, u2);
		game.removeAndRandomNumberInList(null);
		GameProvider.addGames(game);

		ISFSObject obj = new SFSObject();
		obj.putInt(Constant.GAME_MODEL.RANDOM_NUMBER, game.getServerRandomNumber());

		send(Constant.SEND_EVENT.NOT_MATCH, obj, (List<User>) Arrays.asList(u1, u2));
		return;
	}

}
