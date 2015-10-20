package de.lezleoh.mathgame.view.util.test;

import java.util.Objects;

public class TestStyle {
	int style;

	public TestStyle(int style) {
		this.style = style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getStyle() {
		return style;
	}

	public String toString() {
		return String.valueOf(style);
	}

	public boolean equals(Object object) {
		if (object instanceof TestStyle) {
			TestStyle that = (TestStyle) object;
			return Objects.equals(this.style, that.style);
		} else {
			return false;
		}
	}
}
