import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import java.io.*;

public class Player implements Serializable
{
	private transient Color pColor;
	private String name;
	private String colName;

	/**
     * Sets up a player with given name and color.
     *
     * @param      n Name of the player
     * @param      c Color of the orb to be set up
     */
	public Player(String n, Color c)
	{
		this.pColor = c;
		this.name = n;
		this.colName = pColor.toString();		
	}

	public Player(String n, String c)
	{
		this.colName = c;
		this.pColor = Color.valueOf(c);
		this.name = n;
	}

	/**
     * returns the color of the player
     * @return     the color of the player
     */
	public Color getColor()
	{
		return this.pColor;
	}

	public String getColorString()
	{
		return colName;
	}
	
	/**
     * returns the name of the player
     * @return     the name of the player
     */
	public String getName()
	{
		return this.name;
	}
	
	/**
     * Sets up the color of the player
     * @param      c the color of the player
     */
	public void setColor(Color c)
	{
		this.pColor=c;
	}

	/**
     * Returns the details of the player
     * @return  String containg the details of the player   
     */
	@Override
	public String toString()
	{
		return name + " " + pColor.toString();
	}

	/**
     * Returns the details of the player.
     * @param      p Plyer to be compared with
     * @return     Whether two players under comparison are equal or not. 
     */
	public boolean equals(Player p)
	{
		return this.getName().equals(p.getName());
	}

}