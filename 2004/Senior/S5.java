// CCC 2004
// Problem S5 Super Plumber
//
// A dynamic programming problem: given a 2D array with
// coins and obstacles, find max money you can get from bottom
// left to bottom right, going only up, down and right.
//
// Idea is to find the max amount of money to every square in the grid
// working laft to right, one column at a time,
// fill up the max money array. 
// at any row, go right (if you can) and up (all the way)
// updating money. Then go right and down (all the way)
// update money. 
//
// the answer is read from the array at (rows-1, cols-1).  

import java.awt.*;
import java.util.*;
import hsa.*;

public class S5SuperPlumberDP
{
    static Console cc;
    static int[] [] grid;
    static int rows, cols;

    public static void main (String[] args)
    {
	cc = new Console ();
	String file;

	cc.print ("file name: ");
	file = cc.readString ();
	TextInputFile fi = new TextInputFile (file);

	rows = fi.readInt ();
	cols = fi.readInt ();
	while (rows > 0)
	{
	    getGrid (fi);
	    cc.println (solve ());
	    rows = fi.readInt ();
	    cols = fi.readInt ();
	}
    }


    public static void getGrid (TextInputFile fi)
    {
	String line;
	grid = new int [rows] [cols];
	for (int r = 0 ; r < rows ; r++)
	{
	    line = fi.readLine ();
	    for (int c = 0 ; c < cols ; c++)
		if (line.charAt (c) == '.')
		    grid [r] [c] = 0;
		else if (line.charAt (c) == '*')
		    grid [r] [c] = -1;
		else
		    grid [r] [c] = line.charAt (c) - '0';
	}
    }


    public static int solve ()
    {
	int[] [] money;
	money = new int [rows] [cols];
	for (int i = 0 ; i < rows ; i++)
	    for (int j = 0 ; j < cols ; j++)
		money [i] [j] = -1;

	// establish first column (go up and add them all)
	// stop if there's a star (above is unreachable)
	money [rows - 1] [0] = grid [rows - 1] [0];
	for (int i = rows - 2 ; i >= 0 ; i--)
	{
	    if (grid [i] [0] >= 0)
		money [i] [0] = money [i + 1] [0] + grid [i] [0];
	    else
		i = -1;
	}

	// For each column, go right from each square and
	// work up and down filling in money.
	for (int c = 1 ; c < cols ; c++)
	{
	    // going down from each square (0 to rows-1)
	    for (int r = 0 ; r < rows ; r++)
	    {
		if (money [r] [c - 1] >= 0) // can't go right from a wall
		{
		    int t = money [r] [c - 1];
		    for (int k = r ; k < rows ; k++)
		    {
			if (grid [k] [c] >= 0)
			{
			    t = t + grid [k] [c];
			    if (t > money [k] [c])
				money [k] [c] = t;
			}
			else
			    k = rows; // get out: hit a wall
		    }
		}
	    }
	    // going up from each square (rows-1 to 0)
	    for (int r = rows - 1 ; r >= 0 ; r--)
	    {
		if (money [r] [c - 1] >= 0) // can't go right from a wall
		{
		    int t = money [r] [c - 1];
		    for (int k = r ; k >= 0 ; k--)
		    {
			if (grid [k] [c] >= 0)
			{
			    t = t + grid [k] [c];
			    if (t > money [k] [c])
				money [k] [c] = t;
			}
			else
			    k = -1; // get out: hit a wall
		    }
		}
	    }

 /*           for (int i = 0 ; i < rows ; i++)
	    {
		for (int j = 0 ; j < cols ; j++)
		{
		    if (money [i] [j] < 10)
			cc.print (money [i] [j] + "  ");
		    else if (money [i] [j] < 100)
			cc.print (money [i] [j] + " ");
		    else
			cc.print (money [i] [j] + "");
		}
		cc.println ();
	    }
	    cc.println ();
	    cc.println ();
	    cc.getChar ();
 */       }
	return money [rows - 1] [cols - 1];
    }
}

