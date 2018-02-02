package knightsdemo;

import java.util.LinkedList;

import knightsdemo.interfaces.KnightMove;
import knightsdemo.interfaces.KnightsPlayer;

public class KnightsPlayerImpl implements KnightsPlayer {
	private LinkedList<KnightMove> historyOfMoves;
	private String playerName;

	public KnightsPlayerImpl(String playerName) {
		this.playerName = playerName;
		historyOfMoves = new LinkedList<KnightMove>();
	}

	public String getCurrentPlayerName() {
		return this.playerName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see knightsdemo.interfaces.KnightsPlayer#getLastPlayedMove()
	 */
	public String getLastPlayedMove() {
		if (!historyOfMoves.isEmpty()) {
			return "(" + historyOfMoves.peek().getMoveColumn() + "-" + historyOfMoves.peek().getMoveRow() + ")";
		} else {
			return "";
		}

	}

	public void printMoveHistory() {
		for (KnightMove move : historyOfMoves) {
			System.out.println(playerName + " move (" + move.getMoveColumn() + "-" + move.getMoveRow() + ")");
		}
	}

	public KnightMove getLastMove() {
		if (!historyOfMoves.isEmpty()) {
			return historyOfMoves.peek();
		} else {
			return null;
		}
	}

	public void addMove(KnightMove move) {
		historyOfMoves.push(move);
	}

}
