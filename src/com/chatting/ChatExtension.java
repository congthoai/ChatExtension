package com.chatting;

import com.chatting.constant.Chat;
import com.chatting.constant.Game;
import com.chatting.handler.ActionGameHandler;
import com.chatting.handler.ChattingHandler;
import com.chatting.handler.LoginHandler;
import com.chatting.provider.GameProvider;
import com.chatting.provider.ZoneProvider;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class ChatExtension extends SFSExtension{

	@Override
	public void init() {
		addEventHandler(SFSEventType.USER_LOGIN, LoginHandler.class);
		addEventHandler(SFSEventType.USER_JOIN_ZONE, LoginHandler.class);
		
		addRequestHandler(Chat.EVENT.CHATTING, ChattingHandler.class);
		
		addRequestHandler(Game.EVENT.ACTION_SEND, ActionGameHandler.class);
		
		ZoneProvider.getInstance(this);
		GameProvider.getInstance();
	}
	
	@Override
    public void destroy()
    {
        super.destroy();
        trace("Destroy is called!");
    }

}
