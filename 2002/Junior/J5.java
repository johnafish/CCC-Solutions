// CCC 2002
// Problem S3J5 Blindfold

// given a grid with obstacles and a series of moves (R,L,F)
// determine the possible finish points.

// clear is '.' and obstacle is 'X'

// brute force method. Take every starting posititon and any of the 4 directions
// and see where you get. If still okay, mark that spot with a '*'

// file input: r and col of grid given
// then the grid and finally a # and the moves.
// output the grid with the '*' locations.

import java.awt.*;
import hsa.*;

public class S3J5Blindfold
{
    static Console cc;
    static char [] [] grid;
    static int r, c, n;
    static char [] m;

    public static void main (String [] args)
    {
	cc = new Console ();

	String line;

	TextInputFile fi = new TextInputFile ("blind4.in");
	TextOutputFile fo = new TextOutputFile ("blind4.out");

	// read grid
	r = fi.readInt ();
	c = fi.readInt ();
	grid = new char [r] [c];
	for (int i = 0 ; i < r ; i++)
	{
	    line = fi.readLine ();
	    for (int j = 0 ; j < c ; j++)
		grid [i] [j] = line.charAt (j);
	}

	//read moves
	n = fi.readInt ();
	m = new char [n];
	for (int i = 0 ; i < n ; i++)
	    m [i] = (fi.readLine ()).charAt (0);

	// check each square and direction
	for (int i = 0 ; i < r ; i++)
	    for (int j = 0 ; j < c ; j++)
		for (int d = 0 ; d < 360 ; d = d + 90)
		    check (i, j, d);

	// print grid
	for (int i = 0 ; i < r ; i++)
	{
	    for (int j = 0 ; j < c ; j++)
		fo.print (grid [i] [j]);
	    fo.println ();
	}
	fi.close ();
	fo.close ();
    }


    // this updates the global grid 2D array with a "*" if
    // the starting location (i,j) and direction d results in
    // a good square.
    // quit the process if you go off the square or hit a wall
    public static void check (int i, int j, int dir)
    {
	int pi, pj, k;
	pi = i;
	pj = j;
	k = 0;
	while (pi >= 0 && pi < r && pj >= 0 && pj < c &&
		(grid [pi] [pj] == '.' || grid [pi] [pj] == '*') &&
		k < n)
	{
	    if (m [k] == 'R')
	    {
		dir = dir - 90;
		if (dir < 0)
		    dir = 270;
	    }
	    else if (m [k] == 'L')
		dir = (dir + 90) % 360;
	    else if (m [k] == 'F')
	    {
		if (dir == 0)
		    pj = pj + 1;
		else if (dir == 180)
		    pj = pj - 1;
		else if (dir == 90)
		    pi = pi - 1;
		else
		    pi = pi + 1;
	    }
	    k++;
	}

	if (k >= n && pi >= 0 && pi < r && pj >= 0 && pj < c &&
		grid [pi] [pj] == '.')
	    grid [pi] [pj] = '*';

    }
}

