package knightsdemo;

import knightsdemo.interfaces.KnightMove;

public class KnightsMoveImpl implements KnightMove {
	private int moveColumn;
	private int moveRow;

	public KnightsMoveImpl(int moveColumn, int moveRow) {
		this.moveColumn = moveColumn;
		this.moveRow = moveRow;
	}

	public int getMoveColumn() {
		return this.moveColumn;
	}

	public int getMoveRow() {
		return this.moveRow;
	}

}
