package com.chatting.constant;

public class Constant {
	public interface SEND_EVENT {
        public static final String CHATTING_EVENT = "chatting";
        public static final String JOIN_GAME = "joingame";
        public static final String START_GAME = "start";
        public static final String ACTION_SEND = "actionSend";
        public static final String ACTION_RECEIVE = "actionReceive";
        public static final String WIN_GAME = "win";
        public static final String LOG_OUT = "logout";
        public static final String MATCH = "match";
        public static final String NOT_MATCH = "notMatch";
    }

	public interface CHAT_MODEL {
		public static final String MODEL_NAME = "ChatModel";
        public static final String USERNAME = "username";
        public static final String MESSAGE = "message";
    }
	
	public interface PLAYER_MODEL {
		
    }
}