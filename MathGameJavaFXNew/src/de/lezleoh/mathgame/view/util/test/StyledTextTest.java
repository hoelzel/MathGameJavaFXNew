/**
 * 
 */
package de.lezleoh.mathgame.view.util.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.lezleoh.mathgame.view.util.StyledCharacter;
import de.lezleoh.mathgame.view.util.StyledText;

/**
 * @author hoel
 *
 */
public class StyledTextTest {

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.view.util.StyledText#StyledText(java.lang.String, java.lang.Object)}
	 * .
	 */
	@Test
	public final void testStyledTextEmptyText() {
		TestStyle style = new TestStyle(0);

		StyledText<TestStyle> actual = new StyledText<TestStyle>("", style);
		ArrayList<StyledCharacter<TestStyle>> actualText = (ArrayList<StyledCharacter<TestStyle>>) AccessPrivate
				.getPrivateField(actual, "listOfStyledCharacters");
		assertTrue(actualText.isEmpty());
	}

	/**
	 * Test method for
	 * {@link de.lezleoh.mathgame.view.util.StyledText#StyledText(java.lang.String, java.lang.Object)}
	 * .
	 */
	@Test
	public final void testStyledTextSingleCharacter() {
		TestStyle style = new TestStyle(0);

		StyledText<TestStyle> actualStyledText = new StyledText<TestStyle>("a", style);
		ArrayList<StyledCharacter<TestStyle>> actualText = (ArrayList<StyledCharacter<TestStyle>>) AccessPrivate
				.getPrivateField(actualStyledText, "listOfStyledCharacters");

		StyledCharacter<TestStyle> actualStyledCharacter = actualText.get(0);
		StyledCharacter<TestStyle> expectedStyledCharacter = new StyledCharacter<TestStyle>('a', style);
		assertEquals(expectedStyledCharacter, actualStyledCharacter);
	}

	@Test
	public final void testEqualsEmptyText() {
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(1);
		StyledText<TestStyle> styledText0 = new StyledText<TestStyle>("", style0);
		StyledText<TestStyle> styledText1 = new StyledText<TestStyle>("", style1);
		assertTrue(styledText0.equals(styledText1));
	}

	@Test
	public final void testEqualsSuccess() {
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(0);
		StyledText<TestStyle> styledText0 = new StyledText<TestStyle>("012", style0);
		StyledText<TestStyle> styledText1 = new StyledText<TestStyle>("012", style1);
		assertTrue(styledText0.equals(styledText1));
	}

	@Test
	public final void testEqualsFailDifferentStyle() {
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(1);
		StyledText<TestStyle> styledText0 = new StyledText<TestStyle>("012", style0);
		StyledText<TestStyle> styledText1 = new StyledText<TestStyle>("012", style1);
		assertFalse(styledText0.equals(styledText1));
	}

	@Test
	public final void testEqualsFailDifferentText() {
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(0);
		StyledText<TestStyle> styledText0 = new StyledText<TestStyle>("0", style0);
		StyledText<TestStyle> styledText1 = new StyledText<TestStyle>("012", style1);
		assertFalse(styledText0.equals(styledText1));
	}

	@Test
	public final void testConcatStringStyle() {
		TestStyle style0 = new TestStyle(0);

		StyledText<TestStyle> styledTextActual = new StyledText<TestStyle>("0", style0);
		styledTextActual = styledTextActual.concat("1", style0);

		StyledText<TestStyle> styledTextExpected = new StyledText<TestStyle>("01", style0);

		assertEquals(styledTextExpected, styledTextActual);
	}
	
	@Test
	public final void testConcatStyledText() {
		TestStyle style0 = new TestStyle(0);

		StyledText<TestStyle> styledTextActual = new StyledText<TestStyle>("0", style0);
		StyledText<TestStyle> styledTextToConcat = new StyledText<TestStyle>("1", style0);
		styledTextActual = styledTextActual.concat(styledTextToConcat);

		StyledText<TestStyle> styledTextExpected = new StyledText<TestStyle>("01", style0);

		assertEquals(styledTextExpected, styledTextActual);
	}
	
	@Test
	public final void testSetStyle(){
		TestStyle style0 = new TestStyle(0);
		TestStyle style1 = new TestStyle(1);
		
		StyledText<TestStyle> styledTextActual = new StyledText<TestStyle>("0123", style0);
		styledTextActual.setStyle(1, 3, style1);
		
		StyledText<TestStyle> styledTextExpected = new StyledText<TestStyle>("0", style0);
		styledTextExpected.append("12", style1);
		styledTextExpected.append("3", style0);
		
		assertEquals(styledTextExpected, styledTextActual);
		
	}

}
