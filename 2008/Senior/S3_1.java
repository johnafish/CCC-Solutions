// CCC 2008
//
// S3: Maze
//
// This is a recursion 2D array problem.
// (a relatively straight forward algorithm without tricks)
//
// grid is a 2d array of ints holding the number of steps
// needed to get to this space. It is initialized to a
// large number 99999
//
// maze is the 2d array of char, holding the maze itself.
//
// traverse puts the value of x into location r,c
//
// the main starts by putting a 1 in 0,0.
// from there, depending on what is in the maze itself
// traverse is called for the squares next to r,c with x+1
//
// There are a few points. Obviously r,c must be in bounds.
// The maze can't be a '*' at r,c (if it is, set grid r,c to -1)
// The other thing is x only goes into grid(r,c) if x is less than
// what's already there (it is a MINIMUM we're after.)
//
// traverse will go everywhere it is possible to go,
// so back in the main the answer is in bottom right of grid.
// If the value there is -1 or the original 99999, -1 is printed.
//

import java.awt.*;
import hsa.*;

public class CCC2008S3Maze
{
    static Console c;
    static char[] [] maze;
    static int[] [] grid;
    static int row, col;

    public static void traverse (int r, int c, int x)
    {
	if (r >= 0 && r < row && c >= 0 && c < col)
	    if (grid [r] [c] >= 0 && x < grid [r] [c])
		if (maze [r] [c] == '*')
		    grid [r] [c] = -1;
		else if (maze [r] [c] == '+')
		{
		    grid [r] [c] = x;
		    traverse (r - 1, c, x + 1);
		    traverse (r + 1, c, x + 1);
		    traverse (r, c - 1, x + 1);
		    traverse (r, c + 1, x + 1);
		}
		else if (maze [r] [c] == '|')
		{
		    grid [r] [c] = x;
		    traverse (r - 1, c, x + 1);
		    traverse (r + 1, c, x + 1);
		}
		else if (maze [r] [c] == '-')
		{
		    grid [r] [c] = x;
		    traverse (r, c - 1, x + 1);
		    traverse (r, c + 1, x + 1);
		}
    }


    public static void main (String[] args)
    {
	int t;
	c = new Console ();
	TextInputFile f = new TextInputFile ("s3.5.in");
	t = f.readInt ();
	for (int k = 0 ; k < t ; k++)
	{
	    row = f.readInt ();
	    col = f.readInt ();
	    maze = new char [row] [col];
	    grid = new int [row] [col];
	    for (int i = 0 ; i < row ; i++)
		for (int j = 0 ; j < col ; j++)
		    maze [i] [j] = f.readChar ();
	    for (int i = 0 ; i < row ; i++)
		for (int j = 0 ; j < col ; j++)
		    grid [i] [j] = 99999;
	    traverse (0, 0, 1);
	    if (grid [row - 1] [col - 1] > 0 && grid [row - 1] [col - 1] < 99999)
		c.println (grid [row - 1] [col - 1]);
	    else
		c.println (-1);
	}
    }
}


