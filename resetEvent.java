import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import java.io.*;

class resetEvent implements EventHandler<ActionEvent>
{
	Cell[][] cells;
	Label[][] Plabel;
	int row;
	int column;
	gamePage game;
	// Player curPlayer;
	Player newPlayer;

	resetEvent(gamePage game, Cell[][] cells, Label[][] Plabel, int row, int column, Player newPlayer)
	{
		this.cells = cells;
		this.Plabel = Plabel;
		this.row = row;
		this.column = column;
		this.game = game;
		// this.curPlayer = curPlayer;
		this.newPlayer = newPlayer;
	}

	@Override
	public void handle(ActionEvent reset)
	{
		game.setCount(0);
		game.setCurIndex(0);
		for(int i=0; i<column; i++)
	    {
	      for(int j=0; j<row; j++)
	      {
	        Plabel[i][j].setText("0");
	        Plabel[i][j].setTextFill(Color.BLACK);
	        cells[i][j].setPMass(0);
	        cells[i][j].setCurPlayer(null);
	        cells[i][j].getOrbPane().getChildren().clear();
	        cells[i][j].setCellColor(newPlayer.getColor());

	      }
	    }

	    game.setCurPlayer(newPlayer);

	    try
	    {
	    	game.serialize();
	    }

	    catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
}
