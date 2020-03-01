package goosegame;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bean.Player;
import constants.Const;
import game.GameFactory;

public class GooseGameTest {
	
	public List<Player> partecipants;
	
	@Before
	public void addPartecipants() {
		
		partecipants = new ArrayList<Player>();
		
		Player playerOne = new Player("Massimo");
		partecipants.add(playerOne);
		
		Player playerTwo = new Player("Anna");
		partecipants.add(playerTwo);
		
	}
	
	@Test
	public void movePlayer() {
		
		Player player = partecipants.get(0);
		
		GameFactory gameFactory = new GameFactory();
		int start = player.getSpace();
		int StartOffset = player.getOffset();
		player = gameFactory.movePlayer(gameFactory.throwsDice(player));
		int space = player.getSpace();
		int offset = player.getOffset();
		
		assertTrue(start<space);
		assertTrue(StartOffset<offset);
		
		System.out.println("Player moved correctly");
		
	}
	
	@Test
	public void playGame() {
		
		GameFactory gameFactory = new GameFactory();
		gameFactory.playGame(partecipants);
		
	}
	
	
	@After
	public void findWinner() {
		
		List<Player> winner = partecipants.stream().filter(x->x.getSpace()==Const.TARGET_SPACE.getValue()).collect(Collectors.toList());
		assertTrue(!winner.isEmpty());
		System.out.println("Winner found");
		
	}
	

}
