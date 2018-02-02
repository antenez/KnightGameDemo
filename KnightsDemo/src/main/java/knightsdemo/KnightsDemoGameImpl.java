package knightsdemo;

import java.util.ArrayList;
import java.util.List;

import knightsdemo.interfaces.KnightMove;
import knightsdemo.interfaces.KnightsDemoGame;
import knightsdemo.interfaces.KnightsPlayer;

/**
 * @author eno.ahmedspahic Game consist of board 8x8 (indexes from [0-7] and
 *         players)
 *
 */
public class KnightsDemoGameImpl implements KnightsDemoGame {
	private enum ValidationCase {
		VALID, OUT_OF_SCOPE, USED_FIELD, INVALID_FINAL_FIRST_MOVE, WIN;
	}

	private static int[][] possibleDeltas = { { -2, -1 }, { -2, +1 }, { +2, -1 }, { +2, +1 }, { -1, -2 }, { -1, +2 },
			{ +1, -2 }, { +1, +2 } };
	private KnightsPlayer[] knightPlayers;

	private int playedMovesCounter = 0;
	private int boardSize = 7;

	/**
	 * Will setup game for two players. This can be extended to receive number
	 * of players as argument
	 */
	public KnightsDemoGameImpl(String playerOneName, String playerTwoName) {
		KnightsPlayer p1 = new KnightsPlayerImpl(playerOneName);
		KnightsPlayer p2 = new KnightsPlayerImpl(playerTwoName);

		KnightsPlayer[] arrayOfPlayers = new KnightsPlayer[2];
		arrayOfPlayers[0] = p1;
		arrayOfPlayers[1] = p2;

		this.knightPlayers = arrayOfPlayers;
		this.playedMovesCounter = 0;
	}

	public String getCurrentPlayerName() {
		return getCurrentPlayer().getCurrentPlayerName();
	}

	public KnightsPlayer getCurrentPlayer() {
		return knightPlayers[this.playedMovesCounter % this.knightPlayers.length];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see knightsdemo.interfaces.KnightsDemoGame#payNextMove(int, int) move to
	 * field 7-7 as first move is invalid move to out of scope fields is invalid
	 * move to used field is invalid
	 */
	public boolean playNextMove(int moveToColumn, int moveToRow) throws IllegalArgumentException {
		if (isGameOver()) {
			throw new IllegalArgumentException("Game is over reset your game!");
		}
		KnightsPlayer currentPlayer = getCurrentPlayer();
		System.out.println("+++CurrentPlayer is " + currentPlayer.getCurrentPlayerName());
		if (currentPlayer.getLastMove() != null) {
			System.out.println("+++ moving from " + currentPlayer.getLastPlayedMove());
		}
		System.out.println("+++ to (" + moveToRow + "-" + moveToColumn + ")");

		// Is first final
		if (currentPlayer.getLastPlayedMove() == "" && moveToColumn == boardSize && moveToRow == boardSize) {
			throw new IllegalArgumentException("First move can not be on final field!");
		}
		// Check out of scope move
		if ((moveToColumn > boardSize || moveToColumn < 0) || (moveToRow > boardSize || moveToRow < 0)) {
			throw new IllegalArgumentException(
					"Move to Col:" + moveToColumn + ", Row: " + moveToRow + " is out of scope");
		}

		if (isMoveOnUsedField(moveToColumn, moveToRow)) {
			throw new IllegalArgumentException(
					"Move to Col:" + moveToColumn + ", Row: " + moveToRow + " is already used");
		}

		// Check is position in valid fields
		if (!isMoveValid(moveToColumn, moveToRow)) {
			System.out.println(
					"Move to Col:" + moveToColumn + ", Row: " + moveToRow + " is not in valid set of next moves!");
			System.out.println("This is list of valid moves ");
			this.printValidMoveProposals();
			throw new IllegalArgumentException(
					"Move to Col:" + moveToColumn + ", Row: " + moveToRow + " is not in Valid set of moves!");
		}

		// Here validation is passed we play right move ad add it in history.
		currentPlayer.addMove(new KnightsMoveImpl(moveToColumn, moveToRow));
		if (isGameOver()) {
			System.out.println("++++Winner is " + getCurrentPlayer().getCurrentPlayerName());
		}
		this.playedMovesCounter++;

		return !isGameOver();

	}

	public boolean isGameOver() {
		for (KnightsPlayer knightPlayer : this.knightPlayers) {
			if (knightPlayer.getLastMove() != null) {
				if (knightPlayer.getLastMove().getMoveColumn() == this.boardSize
						&& knightPlayer.getLastMove().getMoveRow() == this.boardSize) {

					return true;
				}
			}
		}
		return false;
	}

	private boolean isMoveOnUsedField(int moveToCol, int moveToRow) {
		for (KnightsPlayer knightPlayer : this.knightPlayers) {
			if (knightPlayer.getLastMove() != null) {
				if (knightPlayer.getLastMove().getMoveColumn() == moveToCol
						&& knightPlayer.getLastMove().getMoveRow() == moveToRow) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isMoveValid(int moveToCol, int moveToRow) {
		KnightsPlayer currentPlayer = getCurrentPlayer();
		// first move
		if (currentPlayer.getLastMove() == null && isInBoardScope(moveToCol, moveToRow)) {
			return true;
		}
		for (KnightMove move : getValidMoveProposals()) {
			if (move.getMoveColumn() == moveToCol && move.getMoveRow() == moveToRow) {
				return true;
			}
		}
		return false;
	}

	public void printValidMoveProposals() {
		KnightsPlayer currentPlayer = getCurrentPlayer();
		System.out.println("+++PrintProposals for player " + currentPlayer.getCurrentPlayerName());
		for (KnightMove move : getValidMoveProposals()) {
			System.out.println(" proposal (" + move.getMoveRow() + "," + move.getMoveColumn() + ")");
		}
	}

	public List<KnightMove> getValidMoveProposals() {
		KnightsPlayer currentPlayer = getCurrentPlayer();
		KnightMove lastPosition = currentPlayer.getLastMove();
		List<KnightMove> validMovesResponse = new ArrayList<KnightMove>();
		if (lastPosition != null) {

			// board is 8x8 rows and column are going from 0-7 from top to botom
			// and left to right
			// Going to all posible directions with considered other
			// constraingths

			for (int[] currentDelta : possibleDeltas) {
				int newRow = lastPosition.getMoveRow() + currentDelta[0];
				int newColumn = lastPosition.getMoveColumn() + currentDelta[1];

				if (isInBoardScope(newColumn, newRow)) {
					if (!isMoveOnUsedField(newColumn, newRow)) {
						validMovesResponse.add(new KnightsMoveImpl(newColumn, newRow));
					}
				}
			}
		}

		return validMovesResponse;
	}

	private boolean isInBoardScope(int moveToCol, int moveToRow) {
		if (moveToRow >= 0 && moveToRow <= boardSize && moveToCol >= 0 && moveToCol <= boardSize) {
			return true;
		} else {
			return false;
		}
	}

}
