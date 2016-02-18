// CCC 2004
// Problem S2 TopYodeller
//
// given multiple series of integers, maintain cummulative totals,
// rankings and worse ranking.
//
// simple 1D integer arrays handle this

import java.awt.*;
import hsa.*;

public class S2TopYodeller
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	String file;
	int n, k;
	int[] y = new int [100];
	int[] r = new int [100];
	int[] w = new int [100];

	c.print ("file name: ");
	file = c.readString ();
	TextInputFile fi = new TextInputFile (file);

	n = fi.readInt ();
	k = fi.readInt ();

	for (int i = 0 ; i < n ; i++)
	{
	    y [i] = 0;
	    w [i] = 1;
	}

	for (int i = 0 ; i < k ; i++)
	{
	    for (int j = 0 ; j < n ; j++)
		y [j] = y [j] + fi.readInt ();
	    for (int j = 0 ; j < n ; j++)
	    {
		int t = 1;
		for (int p = 0 ; p < n ; p++)
		    if (p != j && y [p] > y [j])
			t++;
		if (t > w [j])
		    w [j] = t;
		r [j] = t;
	    }
	}
	for (int j = 0 ; j < n ; j++)
	    if (r [j] == 1)
		c.println ("Yodeller " + (j + 1) + " is the TopYodeller: score " + y [j] + ", worse rank " + w [j]);
    }
}

