package com.chatting;

import com.chatting.constant.Constant;
import com.chatting.handler.ChattingHandler;
import com.chatting.provider.ZoneProvider;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class ChatExtension extends SFSExtension{

	@Override
	public void init() {
		addRequestHandler(Constant.SEND_EVENT.CHATTING_EVENT, ChattingHandler.class);
		ZoneProvider.getInstance(this);
	}
	
	@Override
    public void destroy()
    {
        super.destroy();
        trace("Destroy is called!");
    }

}
