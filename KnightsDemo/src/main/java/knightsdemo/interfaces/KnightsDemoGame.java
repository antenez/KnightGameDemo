package knightsdemo.interfaces;

import java.util.List;

public interface KnightsDemoGame {

	public String getCurrentPlayerName();

	public KnightsPlayer getCurrentPlayer();

	public boolean playNextMove(int moveToCol, int moveToRow) throws IllegalArgumentException;

	public List<KnightMove> getValidMoveProposals();

	public void printValidMoveProposals();

	public boolean isGameOver();
}
