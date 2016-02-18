// CCC 2002
// Problem S4: Bridge Crossing

// This uses a Dynamic Programming approach, due to Vassili Skarine.
// the key is a best array: best[i] holds the best time for the first i
// people. also group[i] holds the length of the group with i as the
// last person.

// essentially all the various combinations are tested in the following
// order (assuming m = 3 for example)
// a
// ab
// abc
//
// a b
// a bc
// a bcd
//
// ab c
// ab cd
// ab cde
// ...

import java.awt.*;
import hsa.*;

public class S4BridgeCrossingDP
{

    public static void main (String [] args)
    {
	TextInputFile fi = new TextInputFile ("bridge4.in");
	TextOutputFile c = new TextOutputFile ("bridge4.out");
	String [] name;
	int [] time;
	int m, n;
	int totalTime = 0;

	// read info;
	m = fi.readInt ();
	n = fi.readInt ();
	name = new String [n];
	time = new int [n];
	for (int i = 0 ; i < n ; i++)
	{
	    name [i] = fi.readLine ();
	    time [i] = fi.readInt ();
	}
	fi.close ();

	// determine the best times
	int [] best = new int [n + 1];
	int [] group = new int [n + 1];
	for (int i = 0 ; i < n + 1 ; i++)
	{
	    best [i] = 1000000;
	    group [i] = -1;
	}
	best [0] = 0;
	group [0] = 0;
	for (int i = 0 ; i < n + 1 ; i++)
	{
	    int cur = 0;
	    for (int j = 1 ; j <= m && i + j - 1 < n ; j++)
	    {
		cur = Math.max (cur, time [i + j - 1]);
		if (best [i] + cur < best [i + j])
		{
		    best [i + j] = best [i] + cur;
		    group [i + j] = j;
		}
	    }
	}

	c.println ("Total Time: " + best [n]);

	// to get the groups, work backward thru group creating
	// the dividing lines. Then use those lines (in reverse order)
	// to print the solution.
	int [] lines = new int [n + 1];
	int k = n;
	int x = 0;
	while (group [k] != 0)
	{
	    lines [x++] = group [k];
	    k = k - group [k];
	}
	int z = 0;
	for (int i = x - 1 ; i >= 0 ; i--)
	{
	    for (int j = 0 ; j < lines [i] ; j++)
		c.print (name [z++] + " ");
	    c.println ();
	}

	c.close ();
    }
}
