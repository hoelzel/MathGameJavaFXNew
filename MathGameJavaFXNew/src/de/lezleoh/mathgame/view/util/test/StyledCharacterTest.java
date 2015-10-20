package de.lezleoh.mathgame.view.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.lezleoh.mathgame.view.util.StyledCharacter;

public class StyledCharacterTest {

	@Test
	public final void testEqualsObjectSuccess() {
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(0);

		StyledCharacter<TestStyle> styledCharacter0 = new StyledCharacter<TestStyle>('0', style0);
		StyledCharacter<TestStyle> styledCharacter1 = new StyledCharacter<TestStyle>('0', style1);
		assertEquals(styledCharacter0, styledCharacter1);
	}
	@Test
	public final void testEqualsObjectFailDifferentStyle() {
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(1);

		StyledCharacter<TestStyle> styledCharacter0 = new StyledCharacter<TestStyle>('0', style0);
		StyledCharacter<TestStyle> styledCharacter1 = new StyledCharacter<TestStyle>('0', style1);
		assertFalse(styledCharacter0.equals(styledCharacter1));
	}
	@Test
	public final void testEqualsObjectFailDifferentCharacter() {
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(0);

		StyledCharacter<TestStyle> styledCharacter0 = new StyledCharacter<TestStyle>('0', style0);
		StyledCharacter<TestStyle> styledCharacter1 = new StyledCharacter<TestStyle>('1', style1);
		assertFalse(styledCharacter0.equals(styledCharacter1));
	}
}
