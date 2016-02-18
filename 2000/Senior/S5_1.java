// CCC 2000
// Problem S5 Sheep and Coyotes

// This is an exhaustive search problem
// given the location of up to 100 sheep
// find the closest to each distance from 0 to 1000 in steps of 0.01
// (y = 0 in all cases: they are the coyotes!)
// eliminating dups.

// file consists of number of sheep and their loactions.
// output is a list of closest sheep

import java.awt.*;
import hsa.*;


public class S5SheepandCoyotes
{
    static Console c;

    public static void main (String [] args)
    {
	c = new Console ();
	Point sheep [] = new Point [100];
	int eaten [] = new int [100]; // list of possibly eaten sheep
	int n, ne, s;
	double d, m;
	boolean dup;

	TextInputFile fi = new TextInputFile ("sheep.in5");
	TextOutputFile fo = new TextOutputFile ("sheep.ou5");

	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	    sheep [i] = new Point (fi.readDouble (), fi.readDouble ());
	ne = 0;
	for (double x = 0 ; x <= 1000.0 ; x = x + 0.01)
	{

	    // calculate distance to closest sheep
	    s = 0;
	    m = 9999999.0;
	    for (int i = 0 ; i < n ; i++)
	    {
		d = distance (sheep [i], x);
		if (d < m)
		{
		    m = d;
		    s = i;
		}
	    }

	    // check if that sheep is already on the list
	    dup = false;
	    for (int j = 0 ; j < ne ; j++)
		if (s == eaten [j])
		    dup = true;

	    // if not on the list, add to the list
	    if (!dup)
		eaten [ne++] = s;
	}
	for (int j = 0 ; j < ne ; j++)
	{
	    fo.println ("The sheep at (" + sheep [eaten [j]].x + ", " + sheep [eaten [j]].y + ") might be eaten.");
	    c.println ("The sheep at (" + sheep [eaten [j]].x + ", " + sheep [eaten [j]].y + ") might be eaten.");
	}
	fi.close ();
	fo.close ();
    }


    public static double distance (Point p, double x)
    {
	return (p.x - x) * (p.x - x) + p.y * p.y;
    }
}

class Point
{
    public double x, y;
    public Point (double a, double b)
    {
	x = a;
	y = b;
    }
}

