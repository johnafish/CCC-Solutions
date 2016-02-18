// CCC 2003
// Problem S5 Trucking
//
// given a series of cities and "weights" between them
// find minimum weight needed to get to a subset of the cities
// starting from city #1.

// weights are stored in an adjacency matrix
// note if there are two weights between same two cities,
// use only the higher one.

// starting at 1, create the minimum spanning tree for the
// weighted graph, using prim's algorithm
// the maximum weight possible to get to each city is then
// stored in the val array. Find the smallest of these weights
// for the destination city: that's the answer!

// file input: number of cities, # of weights and # of destination cites given
// then the weights and finally the destination cites are given

// thanks to Vassili Skarine for pointing out this method to me :-)

import java.awt.*;
import hsa.*;

public class S5TruckingPrims
{
    static Console cc;
    static int [] [] weights;
    static int [] dest;

    static int n, r, d, a, b, c, tt;

    static int [] val;
    static boolean [] visited;
    static int max, maxt, k, smallest;


    public static void main (String [] args)
    {
	cc = new Console ();


	TextInputFile fi = new TextInputFile ("truck5.in");
	TextOutputFile fo = new TextOutputFile ("truck5a.out");

	n = fi.readInt ();
	r = fi.readInt ();
	d = fi.readInt ();

	weights = new int [n + 1] [n + 1];
	dest = new int [d];

	// read the roads and store the largest weight, if duplicates
	// (road may come in reversed, for arrange smallest first.)
	for (int i = 0 ; i < r ; i++)
	{
	    a = fi.readInt ();
	    b = fi.readInt ();
	    c = fi.readInt ();
	    if (a > b)
	    {
		tt = a;
		a = b;
		b = tt;
	    }
	    if (c > weights [a] [b])
	    {
		weights [a] [b] = c;
		weights [b] [a] = c;
	    }
	}

	// read the destination cities that need to be visited
	for (int i = 0 ; i < d ; i++)
	    dest [i] = fi.readInt ();

	// prim's MST algorithm
	val = new int [n + 1];
	visited = new boolean [n + 1];
	for (k = 0 ; k < n + 1 ; k++)
	{
	    val [k] = 0;
	    visited [k] = false;
	}

	val [1] = 100000;
	maxt = 1;

	do
	{
	    k = maxt;
	    visited [maxt] = true;
	    max = 0;
	    maxt = -1;
	    for (int t = 1 ; t < n + 1 ; t++)
	    {
		if (val [t] < Math.min (val [k], weights [k] [t]))
		    val [t] = Math.min (val [k], weights [k] [t]);
		if (val [t] >= max && !visited [t])
		{
		    max = val [t];
		    maxt = t;
		}
	    }
	}
	while (maxt != -1);

	// the answer is the smallest value for the destination cities.
	smallest = 100000;
	for (int i = 0 ; i < d ; i++)
	    if (val [dest [i]] < smallest)
		smallest = val [dest [i]];

	fo.println (smallest);
	cc.println (smallest);

	fi.close ();
	fo.close ();
    }
}


