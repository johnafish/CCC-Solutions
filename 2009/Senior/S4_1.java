// CCC 2009
//
// S4: Ship and Shop
//
// straight recursion, tracking if you visited a city before, too
// help recursion speed up.
//
// It works fines for data 1, 2, and 4. 
// to make it work for 3 and 5, i have to loop thru it again
// using the better values of shop, until there are no changes. 
//
//  reading takes ~20 sec for large files
//  recursion takes ~5 sec.
//

import java.awt.*;
import hsa.*;

public class CCC2009S4ShipAndShop
{

    public static int[] [] ship;  // lower triangular 2d array
    public static int[] shop;
    public static boolean[] visited;
    public static boolean improvement;
    public static int n, d;
    public static Console c;

    public static void main (String[] args)
    {

	c = new Console ();
	TextInputFile f = new TextInputFile ("s4.5.in");

	n = f.readInt ();
	ship = new int [n + 1] [];
	shop = new int [n + 1];
	visited = new boolean [n + 1];
	for (int i = 1 ; i <= n ; i++)
	{
	    ship [i] = new int [i + 1];
	    shop [i] = 999999999;
	    visited [i] = false;
	    for (int j = 1 ; j < i ; j++)
		ship [i] [j] = 999999999;
	    ship [i] [i] = 0;
	}

	int t = f.readInt ();
	int i, j, x;
	for (int k = 0 ; k < t ; k++)
	{
	    i = f.readInt ();
	    j = f.readInt ();
	    x = f.readInt ();
	    if (i < j && j <= n)
		ship [j] [i] = x;
	    else if (j < i && i <= n)
		ship [i] [j] = x;
	}
	int k = f.readInt ();
	for (i = 0 ; i < k ; i++)
	{
	    j = f.readInt ();
	    x = f.readInt ();
	    if (j <= n)
		shop [j] = x;
	}
	d = f.readInt ();
	c.println ("Input finished.");
	
	// this is to repeat the recursion, until the answer 
	// remains unchanged. I'd never have thought to do this
	// unless I had the real data to work with. so I suppose this 
	// shows the error of my method..... it works though :-)
	int old = 0;
	x = cheapest(d);
	while (x != old)
	{
	   old  = x;
	   for (i = 1 ; i <= n ; i++)
		visited [i] = false;
	   x = cheapest (d);
	}
	c.println (x);
    }


    // the cheapest city x can get the pencil is
    // the cheapest it can get it from any of the other cities, including
    // shipping. (Answers are stored in shop array.)
    public static int cheapest (int x)
    {
	if (visited [x])
	    return shop [x];
	visited [x] = true;
	int smallest = shop [x];
	for (int i = 1 ; i <= n ; i++)
	    if (ship [Math.max (i, x)] [Math.min (i, x)] + cheapest (i) < smallest)
		smallest = ship [Math.max (i, x)] [Math.min (i, x)] + cheapest (i);
	shop [x] = smallest;
	return smallest;
    }
}


