package bean;

import java.io.Serializable;

public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Player(String name) {
		
		this.name=name;
		this.space=0;
		this.offset=0;
		
	}
	
	/**
	 * the name of the player
	 */
	private String name;
	
	/**
	 * the position of the player
	 */
	private int space;
	
	/**
	 * the movement of the player after throwing dice (diceOneValue+diceTwoValue)
	 */
	private int offset;
	/**
	 * value of dice1 after throwing
	 */
	private int diceOneValue;
	
	/**
	 * value of dice2 after throwing
	 */
	private int diceTwoValue;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the space
	 */
	public int getSpace() {
		return space;
	}

	/**
	 * @param space the space to set
	 */
	public void setSpace(int space) {
		this.space = space;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the diceOneValue
	 */
	public int getDiceOneValue() {
		return diceOneValue;
	}

	/**
	 * @param diceOneValue the diceOneValue to set
	 */
	public void setDiceOneValue(int diceOneValue) {
		this.diceOneValue = diceOneValue;
	}

	/**
	 * @return the diceTwoValue
	 */
	public int getDiceTwoValue() {
		return diceTwoValue;
	}

	/**
	 * @param diceTwoValue the diceTwoValue to set
	 */
	public void setDiceTwoValue(int diceTwoValue) {
		this.diceTwoValue = diceTwoValue;
	}
	
	

}
