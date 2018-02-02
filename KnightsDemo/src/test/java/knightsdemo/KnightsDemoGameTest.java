package knightsdemo;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import knightsdemo.interfaces.KnightMove;
import knightsdemo.interfaces.KnightsDemoGame;

public class KnightsDemoGameTest {

	/*
	 * Test is game configured according to input parameters. Field size Number
	 * of players Initial position
	 */
	@Test
	public void testSetupGame() {
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1", "player2");
		Assert.assertEquals("player1", kdg.getCurrentPlayerName());

	}

	@Test
	public void testSetupGameMoveOutOfBoard() {
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1", "player2");
		try {
			kdg.playNextMove(8, 8);
			fail("Final field as first move should throw exception");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Move to Col:8, Row: 8 is out of scope", e.getMessage());
		}

		try {
			kdg.playNextMove(-1, -1);
			fail("Final field as first move should throw exception");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Move to Col:-1, Row: -1 is out of scope", e.getMessage());
		}
	}

	@Test
	public void testFinalFieldInitialMove() {
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1", "player2");
		try {
			kdg.playNextMove(7, 7);
			fail("Final field as first move should throw exception");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("First move can not be on final field!", e.getMessage());
		}
	}

	@Test
	public void testMoveOnUsedFields() {
		KnightsDemoGame kdg = setupGameWithTwoPlayersWithPositions5_5_and_4_4();
		try {
			kdg.playNextMove(4, 4);
			fail("Next Move for player one should be on not used field!");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Move to Col:4, Row: 4 is already used", e.getMessage());
		}
	}

	@Test
	public void testFullGame() {
		KnightsDemoGame kdg = setupGameWithTwoPlayersWithPositions5_5_and_4_4();

		// firstPlayer is on 5-5
		List<KnightMove> validMoveProposals = kdg.getValidMoveProposals();
		List<KnightMove> expectedMoveProposals = new LinkedList<>();
		expectedMoveProposals.add(new KnightsMoveImpl(4, 3));
		expectedMoveProposals.add(new KnightsMoveImpl(3, 4));
		expectedMoveProposals.add(new KnightsMoveImpl(6, 3));
		expectedMoveProposals.add(new KnightsMoveImpl(7, 4));
		expectedMoveProposals.add(new KnightsMoveImpl(3, 6));
		expectedMoveProposals.add(new KnightsMoveImpl(4, 7));
		expectedMoveProposals.add(new KnightsMoveImpl(6, 7));
		expectedMoveProposals.add(new KnightsMoveImpl(7, 6));

		for (KnightMove expected : expectedMoveProposals) {
			if (!existMove(expected, validMoveProposals)) {
				fail("NotFound expected move " + expected.getMoveRow() + "-" + expected.getMoveColumn());
			}
		}
		kdg.printValidMoveProposals();

		kdg.playNextMove(4, 3);
		kdg.playNextMove(3, 2);

		kdg.printValidMoveProposals();
		kdg.playNextMove(3, 5);
		kdg.playNextMove(4, 4);

		kdg.printValidMoveProposals();
		kdg.playNextMove(5, 6);
		kdg.playNextMove(3, 2);

		kdg.printValidMoveProposals();
		kdg.playNextMove(7, 7);
		try {
			// Should fail because game is over
			kdg.playNextMove(4, 4);
			fail("Expect errorr");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Game is over reset your game!", e.getMessage());
		}

		Assert.assertTrue("Game suppose to be over ", kdg.isGameOver());
	}

	private boolean existMove(KnightMove move, List<KnightMove> inList) {
		for (KnightMove moveFromList : inList) {
			if (moveFromList.getMoveColumn() == move.getMoveColumn() && moveFromList.getMoveRow() == move.getMoveRow())
				return true;
		}
		return false;
	}

	/**
	 * This is construction helper method for test casses
	 * 
	 * @return initialized game with two players player1 and player2 with
	 *         initial coordinates (5,5) and (4,4).
	 */
	private KnightsDemoGame setupGameWithTwoPlayersWithPositions5_5_and_4_4() {
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1", "player2");
		kdg.playNextMove(5, 5);
		kdg.playNextMove(4, 4);
		return kdg;
	}

}
