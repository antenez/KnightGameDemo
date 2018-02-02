package knightsdemo.interfaces;

public interface KnightsPlayer {

	public String getCurrentPlayerName();

	public String getLastPlayedMove();

	public void printMoveHistory();

	public KnightMove getLastMove();

	public void addMove(KnightMove move);
}
