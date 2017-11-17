import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;

public class winnerPage
{ 
   homePage home;

   winnerPage()
   {
      home = new homePage();
   }

   public void openWinnerPage(Stage stage , Player p, gamePage game) 
   {  
      VBox vBox = new VBox();
      vBox.setSpacing(20);
      vBox.setPadding(new Insets(20, 20, 20, 20));
      vBox.setAlignment(Pos.CENTER);

      String winnerName = p.getName() + " is Winner ";

      Label winLabel = new Label(winnerName);
      winLabel.setFont(new Font(50.0));
      vBox.getChildren().add(winLabel);

      Button startNewBut = new Button("START NEW GAME");
      startNewBut.setOnAction(e -> home.start(stage));
      vBox.getChildren().add(startNewBut);

      Button undoBut = new Button("UNDO");

      vBox.getChildren().add(undoBut);

      // undoBut.setOnAction(new undoHandler(game));

      Scene scene = new Scene(vBox);    
      stage.setTitle("Chain Reaction"); 
      stage.setScene(scene);
   } 
}
