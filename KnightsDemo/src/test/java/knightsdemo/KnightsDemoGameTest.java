package knightsdemo;


import static org.junit.Assert.fail;

import org.junit.Test;

import junit.framework.Assert;
import knightsdemo.interfaces.KnightsDemoGame;

public class KnightsDemoGameTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/*
	 * Test is game configured according to input parameters.
	 * Field size
	 * Number of players
	 * Initial position
	 */
	@Test
	public void testSetupGame(){
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1","player2");
		Assert.assertEquals("player1", kdg.getCurrentPlayerName());
		
	}
	@Test
	public void testSetupGameMoveOutOfBoard(){
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1","player2");
		try{
			kdg.payNextMove(8, 8);
			fail("Final field as first move should throw exception");
		}catch(IllegalArgumentException e){
			System.out.println("Expected error for initial end field");
		}
		
		try{
			kdg.payNextMove(-1, -1);
			fail("Final field as first move should throw exception");
		}catch(IllegalArgumentException e){
			System.out.println("Expected error for initial end field");
		}
	}
	@Test
	public void testFinalFieldInitialMove(){
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1","player2");
		try{
			kdg.payNextMove(7, 7);
			fail("Final field as first move should throw exception");
		}catch(IllegalArgumentException e){
			System.out.println("Expected error for initial end field");
		}
	}
	
	
	@Test
	public void testMoveOnUsedFields(){
		KnightsDemoGame kdg = setupGameWithTwoPlayersWithPositions5_5_and_4_4();
		try{
			kdg.payNextMove(4, 4);
			fail("Next Move for player one should be on not used field!");
		}catch(IllegalArgumentException e){
			System.out.println("Expected error for initial end field");
		}
	}
	
	
	/**
	 * This is construction helper method for test casses
	 * @return initialized game with two players player1 and player2 with initial coordinates (5,5) and (4,4).
	 */
	private KnightsDemoGame setupGameWithTwoPlayersWithPositions5_5_and_4_4(){
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1","player2");
		kdg.payNextMove(5, 5);
		kdg.payNextMove(4, 4);
		return kdg;
	}
	
	

}
