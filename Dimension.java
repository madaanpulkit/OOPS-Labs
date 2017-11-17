/**
* <h1>Class Dimension</h1>
* The Dimension class determines the dimension of the gamePage in terms of its rows and columns
* <p>
* <b>Note:</b> ......
*
* @author  Pulkit Madaan , Gyanesh Anand
* @version 1.0
* @since   2017-11-16
*/

import java.io.*;


public final class Dimension implements Serializable
{
	/**
     * To determine the row of a cell in the grid
     */
	private final int row;
	/**
     * To determine the column of a cell in the grid
     */
	private final int column;

	/**
     * Constructs a Dimension object containg rows and Columns
     * @param      r To determine the row of a cell in the grid
     * @param      c To determine the column of a cell in the grid
     */
	Dimension(int r, int c)
	{
		row = r;
		column = c;
	}

	/**
     * returns the row of a cell
     * @return     Returns the row of a cell in the grid
     */

	public int getRow()
	{
		return row;
	}

	/**
     * returns the column of a cell
     * @return     Returns the column of a cell in the grid
     */
	public int getColumn()
	{
		return column;
	}
}