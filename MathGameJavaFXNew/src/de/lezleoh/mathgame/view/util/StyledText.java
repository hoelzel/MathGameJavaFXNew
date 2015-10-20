package de.lezleoh.mathgame.view.util;

import java.util.ArrayList;
import java.util.Objects;

public class StyledText<S> {
	private ArrayList<StyledCharacter<S>> listOfStyledCharacters;

	public StyledText() {
		listOfStyledCharacters = new ArrayList<StyledCharacter<S>>();
	}

	public StyledText(String string, S style) {
		listOfStyledCharacters = new ArrayList<StyledCharacter<S>>();
		for (int i = 0; i < string.length(); i++) {
			Character character = string.charAt(i);
			StyledCharacter<S> styledCharacter = new StyledCharacter<S>(character, style);
			listOfStyledCharacters.add(styledCharacter);
		}
	}

	/**
	 * @Parameters beginIndexInclusive the beginning index, inclusive. endIndexExclusive the
	 *             ending index, exclusive
	 */
	public void setStyle(int beginIndexInclusive, int endIndexExclusive, S style) {
		for (int i = beginIndexInclusive; i < endIndexExclusive; i++) {
			StyledCharacter<S> actStyledCharacter = listOfStyledCharacters.remove(i);
			actStyledCharacter.setStyle(style);
			listOfStyledCharacters.add(i, actStyledCharacter);
		}
	}

	public StyledText<S> concat(String string, S style) {
		StyledText<S> styledTextToConcat = new StyledText<S>(string, style);
		return concat(styledTextToConcat);
	}

	public StyledText<S> concat(StyledText<S> styledText) {
		StyledText<S> newStyledText = new StyledText<S>();
		newStyledText.listOfStyledCharacters.addAll(this.listOfStyledCharacters);
		newStyledText.listOfStyledCharacters.addAll(styledText.listOfStyledCharacters);
		return newStyledText;
	}

	public void append(StyledText<S> styledText) {
		StyledText<S> newStyledText = concat(styledText);
		this.listOfStyledCharacters = newStyledText.listOfStyledCharacters;
	}

	public void append(String string, S style) {
		StyledText<S> newStyledText = new StyledText<S>(string, style);
		append(newStyledText);

	}
	
	public StyledCharacter<S> getStyledCharacter(int index){
		//TODO IndexOutOfBoundsException
		return listOfStyledCharacters.get(index);
	}
	
	public int getLength(){
		return listOfStyledCharacters.size();
	}

	public boolean equals(Object object) {
		if (object instanceof StyledText) {
			StyledText<?> that = (StyledText<?>) object;
			return Objects.equals(this.listOfStyledCharacters, that.listOfStyledCharacters);
		}
		return false;
	}

	public String toString() {
		return listOfStyledCharacters.toString();
	}

	public String getString() {
		String returnString = "";
		for (StyledCharacter<S> styledCharacter : listOfStyledCharacters) {
			returnString = returnString + styledCharacter.getString();
		}
		return returnString;
	}

}
