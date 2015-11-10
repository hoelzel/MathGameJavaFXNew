package de.lezleoh.mathgame.view;

import java.util.Set;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import de.lezleoh.mathgame.game.GameStateMachine;
import de.lezleoh.mathgame.game.TermWithHitPositions;
import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Product;
import de.lezleoh.mathgame.term.Sum;
import de.lezleoh.mathgame.term.TermInt;
import de.lezleoh.mathgame.view.util.Style;
import de.lezleoh.mathgame.view.util.StyledCharacter;
import de.lezleoh.mathgame.view.util.StyledText;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainApp2 extends Application {

	private static final String FERTIG = " Fertig";

	double fontsize = 20;
	Font monospacedFont = Font.font("Monospaced", fontsize);
	Font normalFont = Font.font("Verdana", fontsize);
	FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();

	double fontWidth = fontLoader.computeStringWidth("1", monospacedFont);

	Color hitColor = Color.BLUE;
	Style hitStyle = new Style(monospacedFont, hitColor);

	Color normalColor = Color.BLACK;
	Style normalStyle = new Style(monospacedFont, normalColor);

	Color faultColor = Color.RED;
	Style faultStyle = new Style(monospacedFont, faultColor);
	
	Color buttonColor = Color.GREEN;
	Style buttonStyle = new Style(normalFont, buttonColor);
	

	// View
	private Stage primaryStage;
	private static TextFlow textFlow = new TextFlow();
	private static Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Mathgame");
		try {
			BorderPane root = new BorderPane();
			scene = new Scene(root, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
			root.setCenter(textFlow);

			GameStateMachine gameStateMachine = new GameStateMachine(getStructuredTerm());
			appendToTextFlow(new StyledText<Style>(gameStateMachine.getTerm().getString(), normalStyle));
			appendToTextFlow( new StyledText<Style>(FERTIG, buttonStyle));

			textFlow.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					textFlow.getChildren().clear();
					// calculate Index
					Double indexAsDouble = mouseEvent.getX() / fontWidth;
					Integer index = indexAsDouble.intValue();
					
					gameStateMachine.switchState(index);
					for (TermWithHitPositions termWithHitPositions : gameStateMachine.getHistory()) {
						StyledText<Style> styledText = new StyledText<Style>(termWithHitPositions.getTerm().getString(),
								normalStyle);
						styledText = alternateStyledText(styledText, hitStyle, termWithHitPositions.getHitPositions());
						appendToTextFlow(styledText);
						appendToTextFlow(new StyledText<Style>("\n", normalStyle));
					}
					StyledText<Style> styledText = new StyledText<Style>(
							gameStateMachine.getTerm().getString(), normalStyle);
					styledText = alternateStyledText(styledText, hitStyle, gameStateMachine.getHitPositions());
					appendToTextFlow(styledText);
					
					styledText = new StyledText<Style>(" "+ gameStateMachine.getFaultDescription(), faultStyle);
					appendToTextFlow(styledText);
					
					styledText = new StyledText<Style>(FERTIG, buttonStyle);
					appendToTextFlow(styledText);

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TermInt getStructuredTerm() {
		// term under test: 11 + 21 * (31 + 32) + (-12)
		// index__________: 012345678901234567890123456
		Number summand11 = new Number(11);
		Number factor21 = new Number(21);
		Number summand31 = new Number(31);
		Number summand32 = new Number(32);
		Number summand12 = new Number(-12);
		Sum sum3 = new Sum();
		sum3.addOperand(summand31);
		sum3.addOperand(summand32);
		Product product2 = new Product();
		product2.addOperand(factor21);
		product2.addOperand(sum3);
		Sum sum1 = new Sum();
		sum1.addOperand(summand11);
		sum1.addOperand(product2);
		sum1.addOperand(summand12);
		return sum1;
	}

	private StyledText<Style> alternateStyledText(StyledText<Style> styledText, Style alternateStyle,
			Set<Integer> positionsToAlternate) {
		for (int position : positionsToAlternate) {
			styledText.setStyle(position, position + 1, alternateStyle);
		}
		return styledText;
	}

	private void appendToTextFlow(StyledText<Style> styledText) {
		for (int i = 0; i < styledText.getLength(); i++) {
			StyledCharacter<Style> actStyledCharacter = styledText.getStyledCharacter(i);
			Text actText = new Text();
			actText.setText(actStyledCharacter.getString());
			actText.setFont(actStyledCharacter.getStyle().getFont());
			actText.setFill(actStyledCharacter.getStyle().getColor());
			textFlow.getChildren().add(actText);
		}
	}

}
