import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import java.io.*;

class undoHandler implements EventHandler<ActionEvent>
{
	Cell[][] cells;
	Label[][] Plabel;
	// int row;
	// int column;
	gamePage game;
	// Player curPlayer;
	// Player newPlayer;
	animation anima;
	Dimension dimension;

	undoHandler(gamePage game)
	{ 
		this.game = game;
		anima = game.getAnimation();
		cells = game.getCells();
		Plabel = game.getPLabel();
		dimension = game.getDimension();
	}

	@Override
	public void handle(ActionEvent reset)
	{
		gamePage oldGame = null;
		
		try
		{
			oldGame = game.deserialize(game.getStartTime());
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		game.setCurPlayer(new Player(oldGame.getCurPlayer().getName(), oldGame.getCurPlayer().getColorString()));

		game.setCurIndex(oldGame.getCurIndex());
		game.setCount(oldGame.getCount());

		Cell[][] oldCells = oldGame.getCells();

		for(int i=0; i<dimension.getColumn(); i++)
		{
			for(int j=0; j<dimension.getRow(); j++)
			{
				cells[i][j].setPMass(oldCells[i][j].getPMass());
				Plabel[i][j].setText(Integer.toString(cells[i][j].getPMass()));
				cells[i][j].setCellColor(Color.valueOf(oldGame.getCurPlayer().getColorString()));
				// System.out.println(Plabel[i][j].getText());
				if(oldCells[i][j].getCurPlayer() != null)
				{
					cells[i][j].setCurPlayer(new Player(oldCells[i][j].getCurPlayer().getName(), oldCells[i][j].getCurPlayer().getColorString()));
					Plabel[i][j].setTextFill(cells[i][j].getCurPlayer().getColor());
					anima.add(cells[i][j].getCurPlayer().getColor(), cells[i][j].getOrbPane(), cells[i][j].getCMass(), cells[i][j].getPMass(), i, j);
				}

				else
				{
					cells[i][j].setCurPlayer(null);
					cells[i][j].getOrbPane().getChildren().clear();
					Plabel[i][j].setTextFill(Color.BLACK);
				}
			}
		}

	}
}
