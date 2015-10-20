package de.lezleoh.mathgame.view;

import java.util.ArrayList;

import de.lezleoh.mathgame.term.Number;
import de.lezleoh.mathgame.term.Product;
import de.lezleoh.mathgame.term.Sum;
import de.lezleoh.mathgame.term.TermInt;
import de.lezleoh.mathgame.view.util.Style;
import de.lezleoh.mathgame.view.util.StyledCharacter;
import de.lezleoh.mathgame.view.util.StyledText;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainApp extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Mathgame");
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1000, 400);
			primaryStage.setScene(scene);
			primaryStage.show();

			TermInt term = getStructuredTerm();
			Font font = new Font(30);

			Color hitColor = Color.RED;
			Style hitStyle = new Style(font, hitColor);

			Color normalColor = Color.BLACK;
			Style normalStyle = new Style(font, normalColor);
			TextFlow textFlow = new TextFlow();
			int j = 0;
			while (j < 10) {
				StyledText<Style> styledTextList = new StyledText<Style>(term.getString(), normalStyle);
				for (int i : term.getHitPositions()) {
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
				term = term.simplifyOneStep();
				j++;
			}

			root.setCenter(textFlow);

			// @formatter:off
			/*
			ArrayList<Integer> hitPositions = new ArrayList<>();
			hitPositions.addAll(term.getHitPositions());
			TextFlow textFlow = new TextFlow();
			Integer oldHitPosition = new Integer(0);	
			for (Integer hitPosition : hitPositions){
				Text text1 = new Text(term.getString().substring(oldHitPosition, hitPosition));
				text1.setFill(Color.BLACK);
				text1.setFont(Font.font("Monospace", FontWeight.BOLD, 40));
				textFlow.getChildren().add(text1);
				Text text2 = new Text(term.getString().substring(hitPosition,hitPosition));
				text2.setFill(Color.RED);
				text2.setFont(Font.font("Monospace", FontWeight.BOLD, 40));
				textFlow.getChildren().add(text2);
				oldHitPosition = hitPosition;
			}
			Text text3 = new Text(term.getString().substring(oldHitPosition));
			text3.setFill(Color.BLACK);
			text3.setFont(Font.font("Monospace", FontWeight.BOLD, 40));
			textFlow.getChildren().add(text3);
			textFlow.setOnMouseClicked(mouseEvent -> System.out.println("entering at x: "+ mouseEvent.getX()));
			*/
			// @formatter:on

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
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
