package de.lezleoh.mathgame.view;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

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

public class MainAppDemonstrateClicking extends Application {

	private Stage primaryStage;
	private TextFlow textFlow = new TextFlow();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Mathgame");
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 200, 100);
			primaryStage.setScene(scene);
			primaryStage.show();

			// TermInt term = getStructuredTerm();
			TermInt term = new Number(123456789);
			double fontsize = 30;
			Font font = Font.font("Monospaced", fontsize);
			FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();

			double fontWidth = fontLoader.computeStringWidth("1", font);

			Color hitColor = Color.RED;
			Style hitStyle = new Style(font, hitColor);

			Color normalColor = Color.BLACK;
			Style normalStyle = new Style(font, normalColor);

			StyledText<Style> styledTextList = new StyledText<Style>(term.getString(), normalStyle);
			for (int i : term.getPossibleHitPositions()) {
				styledTextList.setStyle(i, i + 1, hitStyle);
			}

			for (int i = 0; i < styledTextList.getLength(); i++) {
				StyledCharacter<Style> actStyledCharacter = styledTextList.getStyledCharacter(i);
				Text actText = new Text();
				actText.setText(actStyledCharacter.getString());
				actText.setFont(actStyledCharacter.getStyle().getFont());
				actText.setFill(actStyledCharacter.getStyle().getColor());
				textFlow.getChildren().add(actText);
			}
			textFlow.getChildren().add(new Text("\n"));

			root.setCenter(textFlow);
			textFlow.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					System.out.print("Mousepressed at ");
					System.out.print("x: " + mouseEvent.getX() + ", ");
					System.out.print("y: " + mouseEvent.getY() + ", ");
					Double indexAsDouble = mouseEvent.getX() / fontWidth;
					Integer index = indexAsDouble.intValue();
					System.out.println("CharacterIndex: " + index);
					textFlow.getChildren().clear();
					styledTextList.setStyle(index, index + 1, hitStyle);
					setTextFlow(styledTextList);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setTextFlow(StyledText styledTextList) {
		for (int i = 0; i < styledTextList.getLength(); i++) {
			StyledCharacter<Style> actStyledCharacter = styledTextList.getStyledCharacter(i);
			Text actText = new Text();
			actText.setText(actStyledCharacter.getString());
			actText.setFont(actStyledCharacter.getStyle().getFont());
			actText.setFill(actStyledCharacter.getStyle().getColor());
			textFlow.getChildren().add(actText);
		}
	}
}
