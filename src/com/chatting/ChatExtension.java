package com.chatting;

import com.smartfoxserver.v2.extensions.SFSExtension;

public class ChatExtension extends SFSExtension{

	@Override
	public void init() {
		// TODO Auto-generated method stub
		trace("Hello, this is my first SFS2X Extension!");
	      
		addRequestHandler("chatting", ChattingHandler.class);
		ZoneProvider.getInstance().setExt(this);
	}
	
	@Override
    public void destroy()
    {
        // Always make sure to invoke the parent class first
        super.destroy();
         
        trace("Destroy is called!");
    }

}
