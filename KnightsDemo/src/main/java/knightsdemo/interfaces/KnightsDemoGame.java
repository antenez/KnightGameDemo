package knightsdemo.interfaces;

import java.util.List;

public interface KnightsDemoGame {

	public String getCurrentPlayerName();
	public KnightsPlayer getCurrentPlayer();
	public void resetGame(int numberOfPlayers);
	public void payNextMove(int moveToCol, int moveToRow) throws IllegalArgumentException;
	public List<KnightMove> getValidMoveProposals();
	public void printValidMoveProposals();
}
