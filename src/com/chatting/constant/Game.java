package com.chatting.constant;

public class Game {
	
	public interface EVENT {
		public static final String JOIN_GAME = "joingame";
	    public static final String ACTION_SEND = "actionSend";	    
    }
	
	public interface CMD {
		public static final String WIN_GAME = "win";
	    public static final String LOG_OUT = "logout";
	    public static final String MATCH = "match";
	    public static final String NOT_MATCH = "notMatch";	    
    }
	
	public interface MODEL {
		public static final String RANDOM_NUMBER = "serverRandomNumber";
        public static final String NUMBER_LIST = "serverNumberList";
        public static final String END_GAME = "endGame";
        public static final String PLAYERS = "players";
        public static final String PLAYER_CHOICE = "playerChoice";
        public static final String WINNER_LIST = "winnerList";
        public static final String POINT = "point";
    }

}
