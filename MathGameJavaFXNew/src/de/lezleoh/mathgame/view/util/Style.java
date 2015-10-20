package de.lezleoh.mathgame.view.util;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Style {
	private Font font;
	private Color color;

	public Style(Font font, Color color) {
		super();
		this.font = font;
		this.color = color;
	}

	public double getFontSize() {
		return font.getSize();
	}
	
	public Font getFont(){
		return font;
	}

	public Color getColor() {
		return color;
	}

}
