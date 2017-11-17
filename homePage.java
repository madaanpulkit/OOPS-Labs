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
import java.util.*;
import java.io.*;

public class homePage extends Application 
{ 
   settingsPage settings;
   ArrayList<Player> players;

   @Override 
   public void start(Stage stage) 
   {   
      // System.out.println(2 % 2);
      settings = new settingsPage();
      players = new ArrayList<Player>();

      VBox vBox1 = new VBox();

      vBox1.setSpacing(20);
      vBox1.setPadding(new Insets(20, 20, 20, 20));

      vBox1.setAlignment(Pos.CENTER);

      Label winLabel = new Label("CHAIN REACTION");
      winLabel.setFont(new Font(50.0));
      vBox1.getChildren().add(winLabel);

      Label dropLabel = new Label("Select the number of Players");
      vBox1.getChildren().add(dropLabel);

      ChoiceBox<String> numPlayerChoice = new ChoiceBox<String>();
      numPlayerChoice.getItems().addAll("2 Players", "3 Players", "4 Players", "5 Players", "6 Players", "7 Players", "8 Players");
      numPlayerChoice.getSelectionModel().select(0);
      vBox1.getChildren().add(numPlayerChoice);

      RadioButton gridOption1 = new RadioButton("9x6");
      RadioButton gridOption2 = new RadioButton("15x10");
      
      ToggleGroup gridOptionGroup = new ToggleGroup();

      gridOption1.setToggleGroup(gridOptionGroup);
      gridOption2.setToggleGroup(gridOptionGroup);

      gridOption1.setSelected(true);

      Label grSizeLabel = new Label("Select the Grid Size");
      vBox1.getChildren().add(grSizeLabel);

      HBox hBox0 = new HBox(gridOption1, gridOption2);
      hBox0.setAlignment(Pos.CENTER);
      hBox0.setSpacing(30);

      vBox1.getChildren().add(hBox0);


      HBox hBox1 = new HBox();
      hBox1.setAlignment(Pos.CENTER);
      hBox1.setSpacing(30);

      Button resumeBut = new Button("RESUME");
      hBox1.getChildren().add(resumeBut);

      Button newGameBut = new Button("NEW GAME");
      hBox1.getChildren().add(newGameBut);

      vBox1.getChildren().add(hBox1);

      HBox hBox2 = new HBox();
      hBox2.setAlignment(Pos.CENTER);
      hBox2.setSpacing(50);

      ToggleButton toggleSound = new ToggleButton("SOUND");
      hBox2.getChildren().add(toggleSound);

      Button settingsBut = new Button("SETTINGS");
      settingsBut.setOnAction(e -> settings.openSettings(stage));
      hBox2.getChildren().add(settingsBut);

      vBox1.getChildren().add(hBox2);

      Scene scene = new Scene(vBox1);  
      
      stage.setTitle("Chain Reaction"); 
       
      stage.setScene(scene); 
      
      stage.show();

      EventHandler<MouseEvent> gameHandler = new EventHandler<MouseEvent>(){
         @Override
         public void handle(MouseEvent e)
         {
            int k = Integer.parseInt(numPlayerChoice.getSelectionModel().getSelectedItem().substring(0,1));
            System.out.println(k);

            for(int i=0; i<k; i++)
            {
               
               System.out.println("home " + settings.getPlayer(i));
               players.add(settings.getPlayer(i));
            }

            if(gridOption1.isSelected())
            {
               new gamePage().openGame(stage, new Dimension(9, 6), players);   
            }

            else
            {
               new gamePage().openGame(stage, new Dimension(15, 10), players);
            }
            
         }
      };

      newGameBut.addEventFilter(MouseEvent.MOUSE_CLICKED, gameHandler);
   } 

   public static void main(String args[])
   { 
      launch(args); 
   } 
}