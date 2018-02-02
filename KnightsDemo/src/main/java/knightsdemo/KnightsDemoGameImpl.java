package knightsdemo;

import java.util.List;

import knightsdemo.interfaces.KnightMove;
import knightsdemo.interfaces.KnightsDemoGame;
import knightsdemo.interfaces.KnightsPlayer;

/**
 * @author eno.ahmedspahic
 * Game consist of board 8x8 (indexes from [0-7] and players)
 *
 */
public class KnightsDemoGameImpl implements KnightsDemoGame{
	
	/**
	 * Will setup game for two players.
	 * This can be extended to receive number of players as argument
	 */
	public KnightsDemoGameImpl(String playerOneName, String playerTwoName){
		
	}

	public String getCurrentPlayerName() {
		// TODO Auto-generated method stub
		return null;
	}

	public KnightsPlayer getCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see knightsdemo.interfaces.KnightsDemoGame#payNextMove(int, int)
	 * move to field 7-7 as first move is invalid
	 * move to out of scope fields is invalid
	 * move to used field is invalid
	 */
	public void payNextMove(int moveToCol, int moveToRow) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	public List<KnightMove> getValidMoveProposals() {
		// TODO Auto-generated method stub
		return null;
	}

	public void printValidMoveProposals() {
		// TODO Auto-generated method stub
		
	}

	public void resetGame(int numberOfPlayers) {
		// TODO Auto-generated method stub
		
	}

}
