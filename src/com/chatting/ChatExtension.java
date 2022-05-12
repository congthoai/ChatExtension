package com.chatting;

import com.chatting.constant.Constant;
import com.chatting.handler.ActionGameHandler;
import com.chatting.handler.ChattingHandler;
import com.chatting.handler.JoinGameHandler;
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
		
		addRequestHandler(Constant.SEND_EVENT.CHATTING_EVENT, ChattingHandler.class);
		
		addRequestHandler(Constant.SEND_EVENT.JOIN_GAME, JoinGameHandler.class);
		addRequestHandler(Constant.SEND_EVENT.ACTION_SEND, ActionGameHandler.class);
		
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
