package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import bean.Player;
import constants.Const;

/**
 * 
 * @author Tommaso D'Ippolito
 * @version 0.1
 */
public class GameFactory {

	public GameFactory() {

		theGoose = new ArrayList<Const>();
		theGoose.add(Const.THEGOOSE_FIVE);
		theGoose.add(Const.THEGOOSE_NINE);
		theGoose.add(Const.THEGOOSE_FOURTEEN);
		theGoose.add(Const.THEGOOSE_EIGHTEEN);
		theGoose.add(Const.THEGOOSE_TWENTYTHREE);
		theGoose.add(Const.THEGOOSE_TWENTYTSEVEN);
		
		partecipants = new ArrayList<Player>();
		
	}
	
	/**
	 * indicates there is a winner
	 */
	private boolean endGame;
	
	/**
	 * list of partecipants
	 */
	private static List<Player> partecipants;
	
	/**
	 * data where goose number are stored
	 */
	private List<Const> theGoose;
	
	/**
	 * 
	 * Add player to the game
	 * 
	 * @param player
	 * @return flag: false when player is duplicated
	 */
	public boolean addPlayer(Player player) {

		List<Player> alreadyPresentPlayer = partecipants.stream().filter(z -> z.getName().equals(player.getName()))
				.collect(Collectors.toList());

		if (alreadyPresentPlayer.size() == 0) {
			System.out.println("add player: " + player.getName());
			partecipants.add(player);
			return true;
		}

		System.out.println(player.getName() + ": already existing player");
		return false;

	}
	
	/**
	 * 
	 * @return
	 */
	public List<Player> getPartecipants(){
		return partecipants;
	}

	/**
	 * 
	 * 	The Game throws the dice
	 * 
	 * @param player 
	 * @return player with the throws data stored
	 */
	public Player throwsDice(Player player) {

		// game throws the dice
		player.setDiceOneValue(ThreadLocalRandom.current().nextInt(1, 7));
		player.setDiceTwoValue(ThreadLocalRandom.current().nextInt(1, 7));
		player.setOffset(player.getDiceOneValue() + player.getDiceTwoValue());
		System.out.println("move "+player.getName()+" "+player.getDiceOneValue()+" "+player.getDiceTwoValue());

		return player;
	}

	/**
	 * 
	 * move players on the space after game throws dices. Check if a bridge or goose or bounce scenario occured
	 * 
	 * @param player
	 * @return
	 */
	public Player movePlayer(Player player) {

		int startSpace = player.getSpace();
		player.setSpace(player.getSpace() + player.getOffset());
		player = checkBridgeSpace(player);

		while (findGooseSpace(player)) {
			player = movePlayerOnGooseSpace(player);
		}
		player = bounceFromTarget(player);
		System.out.println(player.getName()+" rolls " + player.getDiceOneValue() + ", " + player.getDiceTwoValue());
		System.out.println(player.getName()+" moves from " + startSpace + " to " + player.getSpace());
		
		return player;

	}

	/**
	 * Manage the scenario where the players is in a space greater than TARGET_SPACE. If so, player bounces back of the over amount
	 * 
	 * @param player
	 * @return
	 */
	private Player bounceFromTarget(Player player) {

		if (player.getSpace() > Const.TARGET_SPACE.getValue()) {
			int overSpace = player.getSpace();
			int delta = overSpace - Const.TARGET_SPACE.getValue();
			player.setSpace(Const.TARGET_SPACE.getValue() - delta);
			System.out.println(player.getName()+" rolls "+player.getDiceOneValue()+", "+player.getDiceTwoValue()+". "+player.getName()+" moves from "+(overSpace-player.getOffset())+" to "+Const.TARGET_SPACE.getValue()+". "+player.getName()+" bounces! "+player.getName()+" returns to "+player.getSpace());
		}
		return player;
	
	}

	/**
	 * 
	 *  filter the goose object in order to check if player is on a goose space
	 * 
	 * @param player
	 * @return true if the condition is verified
	 */
	private boolean findGooseSpace(Player player) {

		if ((theGoose.stream().filter(x -> x.getValue() == player.getSpace()).collect(Collectors.toList()))
				.size() > 0) {
			return true;
		}
		return false;

	}

	/**
	 * 
	 * Condition: Check if the playes is on a Bridge space. if so player jump to 12
	 * 
	 * @param player
	 * @return player with the new space data
	 */
	private Player checkBridgeSpace(Player player) {

		int startSpace = player.getSpace();
		if (player.getSpace() == Const.BRIGDE_TRIGGER.getValue()) {
			System.out.println("move " + player.getName());// da spostare nel main prima di lanciare il metodo
			System.out.println(
					player.getName() + " rolls " + player.getDiceOneValue() + "," + player.getDiceTwoValue() + ".");
			System.out.println(player.getName() + " moves from " + startSpace + " to the bridge");
			System.out.println(player.getName() + " jumps to "+Const.BRIGDE_TARGET.getValue());
			player.setSpace(Const.BRIGDE_TARGET.getValue());
		}
		return player;
	}

	/**
	 * 
	 * check if player is the winner
	 * 
	 * @param player
	 * @return
	 */
	public boolean isWinner(Player player) {

		if (player.getSpace() == Const.TARGET_SPACE.getValue()) {
			System.out.println(player.getSpace()+" rolls "+player.getDiceOneValue()+", "+player.getDiceTwoValue()+". "+player.getName()+" moves from "+(Const.TARGET_SPACE.getValue()-player.getOffset())+" to "+Const.TARGET_SPACE.getValue()+". "+player.getName()+" Wins!");
			
			return true;
		}
		return false;

	}

	/**
	 * 
	 *  As the player is on a goose space, game moves the player according to game rules
	 * 
	 * @param player
	 * @return
	 */
	private Player movePlayerOnGooseSpace(Player player) {

		int currentSpace = player.getSpace();
		player.setSpace(player.getSpace() + player.getOffset());
		System.out.println(
				player.getName() + " rolls " + player.getDiceOneValue() + "," + player.getDiceTwoValue() + ". ");
		System.out.println(player.getName() + " moves from " + currentSpace + " to " + player.getSpace() + "The Goose");
		return player;

	}

	/**
	 * @return the endGame
	 */
	public boolean isEndGame() {
		return endGame;
	}

	/**
	 * @param endGame the endGame to set
	 */
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}
	
	public void addPartecipants(List<String> players) {
		
		//add players to the gamefactory
		for (String currentPlayer : players) {
			Player player = new Player(currentPlayer);
			addPlayer(player);
			System.out.println("players: ");
			partecipants.stream().map(e->e.getName()).forEach(System.out::println);
		}
		
	}
	
	/**
	 * 
	 * thorws the dice -> moves players -> finds the winner -> ends the game
	 * 
	 * @param partecipants: List of players
	 */
	public void playGame(List<Player> partecipants) {
		
		//throws the dice foreach partecipant till the game is ended
		while (!isEndGame()&&partecipants.size()>0) {
			for (Player player : partecipants) {
				movePlayer(throwsDice(player));
				if (isWinner(player)) {
					setEndGame(true);
					break;
				}
			}
		}
	}

	/**
	 * 
	 * Simulation of a game with two players
	 * 
	 * @param args input players as array of string
	 */
	public static void main(String args[]) {
		
		GameFactory gameFactory = new GameFactory();
		//String inputArray[] = {"Massimo","Anna"};
		List<String> players = Arrays.asList(args);
		
		//add players to the gamefactory
		gameFactory.addPartecipants(players);
		
		//start of the game
		List<Player> partecipants = gameFactory.getPartecipants();
		
		//play game only if at least two partecipants are added
		if (partecipants.size()>1) {
			gameFactory.playGame(partecipants);
		}
		
	}
}
