package de.lezleoh.mathgame.view.util;

import java.util.Objects;

public class StyledCharacter<S> {
	private Character character;
	private S style;

	public StyledCharacter(Character character, S style) {
		this.character = character;
		this.style = style;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public S getStyle() {
		return style;
	}

	public void setStyle(S style) {
		this.style = style;
	}

	public String toString() {
		return "[" + character.toString() + ", " + style.toString() + "]";
	}

	public String getString() {
		return character.toString();
	}
	
	public boolean equals(Object object){
		if (object instanceof StyledCharacter) {
			StyledCharacter<?> that = (StyledCharacter<?>) object;
			return Objects.equals(this.character, that.character) && Objects.equals(this.style, that.style);
		} else {
			return false;
		}
	}
}
