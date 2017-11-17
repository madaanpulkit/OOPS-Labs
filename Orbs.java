import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 

//this should be a generic class for all three type of orbs

class Orbs
{

	private Color oColor;
	private int no_balls;
	//private animation ani;

	public Orbs(Color c,int n)
	{
		this.oColor = c;
		this.no_balls = n;
		//this.ani = a;
	}
	public Color getOrbColor()
	{
		return this.oColor;
	}
	/*public animation getAnimation()
	{
		return this.ani;
	}*/
	public void setColor(Color c)
	{
		this.oColor=c;
	}
	/*public void setAnimation(animation a)
	{
		this.ani=a;
	}*/


}