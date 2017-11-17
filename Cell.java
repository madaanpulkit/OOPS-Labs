import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.geometry.Orientation; 
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import java.io.*;
import java.util.*;

public class Cell implements Serializable
{
	private transient StackPane sPane;
	private transient Box cube;
	private transient Rectangle rect;
	private final int cMass;
	private int pMass;
	private transient StackPane orbPane;
	private Player curPlayer;
	private transient PhongMaterial material;

	/**
     * Constructs the Cell with given critical mass and color
     *
     * @param      c critical mass of the cell to be set up
     * @param      col Color of the cell to be set up
     */
	public Cell(int c, Color col)
	{
		sPane = new StackPane();
		orbPane = new StackPane();
		cube = new Box(50, 50, 50);
		rect = new Rectangle(38,38);
		cMass=c;
		material = new PhongMaterial();
		this.setCellColor(col);	
		this.setPane();
		this.setCube();
		this.setRotate();
		this.setRect();
		curPlayer = null;
		sPane.getChildren().add(orbPane);
	}
	//
	public Cell(int cMass, int pMass)
	{
		this.cMass = cMass;
		this.pMass = pMass;
	}
	/**
     * Setup the Cube
     */
	private void setCube()
	{
        cube.setDrawMode(DrawMode.LINE);
        sPane.getChildren().add(cube);
	}
	/**
     * Setup the Pane
     */
	private void setPane()
	{
		sPane.setPrefWidth(50);
		sPane.setPrefHeight(50);
		sPane.setAlignment(Pos.CENTER);
	}
	/**
     * Setup the Rectangle
     */
	private void setRect()
	{
		rect.setFill(Color.WHITE);
		sPane.getChildren().add(rect);
	}
	/**
     * Setup the Rotation of the cube
     */
	private void setRotate()
	{
		cube.getTransforms().add(new Rotate(-10, 0, 0, 0, Rotate.Y_AXIS));
		cube.getTransforms().add(new Rotate(10, 0, 0, 0, Rotate.X_AXIS));
	}
	/**
     * Sets up the Cell color
     * @param      c Color of the cell to be set up
     */
	public void setCellColor(Color c)
	{
		material.setDiffuseColor(c);
		material.setSpecularColor(c);
		cube.setMaterial(material);
	}

	/**
     * returns the critical mass of the current cell
     * @return     critical mass of the current cell
     */
	public int getCMass()
	{
		return cMass;
	}

	/**
     * returns the present mass of the current cell
     * @return     present mass of the current cell
     */
	public int getPMass()
	{
		return pMass;
	}

	/**
     * Sets up the present mass of the current cell
     * @param      p present mass of the current cel
     */
	public void setPMass(int p)
	{
		pMass = p;
	}

	/**
     * returns the stackPane of the current cell
     * @return     the stackPane of the current cell
     */
	public StackPane getPane()
	{
		return sPane;
	}

	/**
     * returns the Player of the current cell
     * @return     the Player of the current cell
     */
	public Player getCurPlayer()
	{
		return curPlayer;
	}

	/**
     * Sets up the Player of the current cell
     * Assigns the current cell to a player
     * @param      p Player of the current cel
     */
	public void setCurPlayer(Player p)
	{
		curPlayer = p;
	}

	/**
     * returns the orbPane of the current cell
     * @return     the orbPane of the current cell
     */
	public StackPane getOrbPane()
	{
		return orbPane;
	}
}