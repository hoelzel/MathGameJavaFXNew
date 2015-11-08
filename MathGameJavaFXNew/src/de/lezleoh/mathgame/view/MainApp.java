package de.lezleoh.mathgame.view;

import java.util.HashSet;
import java.util.Set;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import de.lezleoh.mathgame.game.GameStateMachine;
import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Product;
import de.lezleoh.mathgame.term.Sum;
import de.lezleoh.mathgame.term.TermInt;
import de.lezleoh.mathgame.view.util.Style;
import de.lezleoh.mathgame.view.util.StyledCharacter;
import de.lezleoh.mathgame.view.util.StyledText;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainApp extends Application {
	// Model
	int maxLineIndex = 0;
	private Set<Integer> clickedPositions = new HashSet<Integer>();
	double fontsize = 30;
	Font font = Font.font("Monospaced", fontsize);
	FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();

	double fontWidth = fontLoader.computeStringWidth("1", font);

	Color hitColor = Color.RED;
	Style hitStyle = new Style(font, hitColor);

	Color normalColor = Color.BLACK;
	Style normalStyle = new Style(font, normalColor);

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
			scene = new Scene(root, 800, 800);
			primaryStage.setScene(scene);
			primaryStage.show();

			TermInt term = getStructuredTerm();
			GameStateMachine gameStateMachine = new GameStateMachine(term);

			StyledText<Style> styledTextList = new StyledText<Style>(gameStateMachine.getTerm().getString(),
					normalStyle);

			for (int i = 0; i < styledTextList.getLength(); i++) {
				StyledCharacter<Style> actStyledCharacter = styledTextList.getStyledCharacter(i);
				Text actText = new Text();
				actText.setText(actStyledCharacter.getString());
				actText.setFont(actStyledCharacter.getStyle().getFont());
				actText.setFill(actStyledCharacter.getStyle().getColor());
				textFlow.getChildren().add(actText);
			}
			textFlow.getChildren().add(new Text(" \n"));

			root.setCenter(textFlow);
			textFlow.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					System.out.print("Mousepressed at ");
					System.out.print("x: " + mouseEvent.getX() + ", ");
					System.out.print("y: " + mouseEvent.getY() + ", ");
					Double indexAsDouble = mouseEvent.getX() / fontWidth;
					Integer index = indexAsDouble.intValue();
					System.out.println("CharacterIndex: " + index);
					textFlow.getChildren().add(new Text("\n"));
					gameStateMachine.switchState(index);
					StyledText<Style> actStyledTextList = alternateStyledText(styledTextList, hitStyle,
							gameStateMachine.getHitPositions());
					appendToTextFlow(actStyledTextList);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void appendToTextFlow(StyledText<Style> styledText) {
		for (int i = 0; i < styledText.getLength(); i++) {
			StyledCharacter<Style> actStyledCharacter = styledText.getStyledCharacter(i);
			Text actText = new Text();
			actText.setText(actStyledCharacter.getString());
			actText.setFont(actStyledCharacter.getStyle().getFont());
			actText.setFill(actStyledCharacter.getStyle().getColor());
			textFlow.getChildren().add(actText);
		}
	}

	private StyledText<Style> alternateStyledText(StyledText<Style> styledText, Style style, Set<Integer> positions) {
		for (Integer position : positions) {
			styledText.setStyle(position, position + 1, style);
		}
		return styledText;
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

}
