// CCC 1998
// Problem E: MountianPassage

// Can't go up or down more than 2 units, if either location > top left
// corner, oxygen is reguired.

// this is a dynamic programmming type problem:
// starting at top corner, create a list of all points visited (the top one)
// got thru the list and update a distance array (a 2D array)
// (the distance is the same as current or 1 more, provided you can reach it.)
// create a new list of all the newly changed points.
// continue until the list is empty.

// eg: if you had
// 2 3 4
// 4 6 9
// 6 9 5
//
// the list (x) would be (0,0)
// distance array would be:
// 0    9999 9999
// 9999 9999 9999
// 9999 9999 9999

// then the new list (y) would be (0,1) and (1,0)
// distance array would be:
// 0       1 9999
// 1    9999 9999
// 9999 9999 9999

// copy y into x
// then the new list (y) would be (0,2), (1,1) and (2,0)
// distance array would be:
// 0    1    2
// 1    2 9999
// 2 9999 9999

// copy y into x
// then the new list (y) would be null
// can't go any further

// BUT if you could, reading the bottom right element gives the answer.

// file input and output.
// input:  number of trips, then size of square and the then the square itself
// output: the # of oxygen units used.

import java.awt.*;
import hsa.*;

public class P5MountianPassage
{
    static Console cc;

    public static void main (String [] args)
    {
	cc = new Console ();
	TextInputFile fi = new TextInputFile ("passage.in");
	TextOutputFile fo = new TextOutputFile ("passage.out");
	int n, oxy;
	int [] [] g;
	int size;

	n = fi.readInt ();
	for (int i = 1 ; i <= n ; i++)
	{


	    // read the size,
	    // create the main grid (g) and distance (d) 2D arrays
	    size = fi.readInt ();
	    g = new int [size] [size];
	    int [] [] d = new int [size] [size];
	    for (int r = 0 ; r < size ; r++)
		for (int c = 0 ; c < size ; c++)
		{
		    g [r] [c] = fi.readInt ();
		    d [r] [c] = 9999;
		}

	    // create the old (x) and new(y) lists of points
	    Point [] x = new Point [625];
	    Point [] y = new Point [625];
	    for (int j = 0 ; j < 625 ; j++)
	    {
		x [j] = new Point ();
		y [j] = new Point ();
	    }

	    // set oxygen limit and initialize list (x) and d
	    oxy = g [0] [0];
	    x [0].r = 0;
	    x [0].c = 0;
	    d [0] [0] = 0;
	    int k = 1;  // size of old list (x)
	    int h;
	    do
	    {

		// t is the size of the new list (y)
		int t = 0;

		for (int j = 0 ; j < k ; j++)
		{
		    int a = x [j].r;
		    int b = x [j].c;

		    // go up if you can
		    if (a - 1 >= 0 && d [a - 1] [b] > d [a] [b] && Math.abs (g [a - 1] [b] - g [a] [b]) <= 2)
		    {
			h = d [a - 1] [b];
			if (g [a - 1] [b] > oxy || g [a] [b] > oxy)
			    d [a - 1] [b] = d [a] [b] + 1;
			else
			    d [a - 1] [b] = d [a] [b];
			if (d [a - 1] [b] < h)
			{
			    y [t].r = a - 1;
			    y [t].c = b;
			    t++;
			}
		    }

		    // go left if you can
		    if (b - 1 >= 0 && d [a] [b - 1] > d [a] [b] && Math.abs (g [a] [b - 1] - g [a] [b]) <= 2)
		    {
			h = d [a] [b - 1];
			if (g [a] [b - 1] > oxy || g [a] [b] > oxy)
			    d [a] [b - 1] = d [a] [b] + 1;
			else
			    d [a] [b - 1] = d [a] [b];
			if (d [a] [b - 1] < h)
			{
			    y [t].r = a;
			    y [t].c = b - 1;
			    t++;
			}
		    }

		    // go down if you can
		    if (a + 1 < size && d [a + 1] [b] > d [a] [b] && Math.abs (g [a + 1] [b] - g [a] [b]) <= 2)
		    {
			h = d [a + 1] [b];
			if (g [a + 1] [b] > oxy || g [a] [b] > oxy)
			    d [a + 1] [b] = d [a] [b] + 1;
			else
			    d [a + 1] [b] = d [a] [b];
			if (d [a + 1] [b] < h)
			{
			    y [t].r = a + 1;
			    y [t].c = b;
			    t++;
			}
		    }

		    // go right if you can
		    if (b + 1 < size && d [a] [b + 1] > d [a] [b] && Math.abs (g [a] [b + 1] - g [a] [b]) <= 2)
		    {
			h = d [a] [b + 1];
			if (g [a] [b + 1] > oxy || g [a] [b] > oxy)
			    d [a] [b + 1] = d [a] [b] + 1;
			else
			    d [a] [b + 1] = d [a] [b];
			if (d [a] [b + 1] < h)
			{
			    y [t].r = a;
			    y [t].c = b + 1;
			    t++;
			}
		    }
		}

		// move new (y) into old (x) list
		for (int j = 0 ; j < t ; j++)
		{
		    x [j].r = y [j].r;
		    x [j].c = y [j].c;
		}
		k = t;
	    }
	    while (k > 0);

	    // the answer is in the bottom right of distance array
	    if (d [size - 1] [size - 1] == 9999)
	    {
		fo.println ("CANNOT MAKE TRIP");
		cc.println ("CANNOT MAKE TRIP");
	    }
	    else
	    {
		fo.println (d [size - 1] [size - 1]);
		cc.println (d [size - 1] [size - 1]);
	    }
	    fo.println ();
	}

    }
}

class Point
{
    public int r, c;
}
