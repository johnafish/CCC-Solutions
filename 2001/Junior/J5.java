// CCC 2001
// Problem J5S3: Stategic bombing

// Points A, B, C, D, E, etc are joined by a series of roads.
// find a single road that will cut A from B (find all such single roads)

// solution method: get the roads
//                  fill 2d adjacency matrix will all but 1 road
//                  using Warshall's, check if A is connected to B
//                  repeat leaving out each road in turn.

// File I/O
// Input: a series of roads, given as a pair of letters
//        (eg DF means D and F are connected).
//        "**" denotes end of data
// Ouput: list of roads to bomb and a count.


import java.awt.*;
import hsa.*;

public class J5S3Bombing
{
    static Console c;

    public static void main (String [] args)
    {
	c = new Console ();
	boolean [] [] adj = new boolean [26] [26];
	String [] roads = new String [1000];
	String s;
	int a, b;
	int n, count;

	TextInputFile fi = new TextInputFile ("bomb5.in");
	TextOutputFile fo = new TextOutputFile ("bomb5.out");

	// get the roads
	s = fi.readString ();
	n = 0;
	count = 0;
	while (!s.equals ("**"))
	{
	    roads [n++] = s;
	    s = fi.readString ();
	}
	fi.close ();
	for (int i = 0 ; i < n ; i++)
	{
	    // reset adj matrix
	    for (int k = 0 ; k < 26 ; k++)
		for (int j = 0 ; j < 26 ; j++)
		{
		    if (k == j)
			adj [k] [j] = true;
		    else
			adj [k] [j] = false;
		}
	    //load the adj matrix with the ith road missing
	    for (int j = 0 ; j < n ; j++)
	    {
		if (j != i)
		{
		    a = roads [j].charAt (0) - 'A';
		    b = roads [j].charAt (1) - 'A';
		    adj [a] [b] = true;
		    adj [b] [a] = true;
		}
	    }

	    // Do transitive closure
	    warshalls (adj);

	    if (!adj [0] [1])
	    {
		fo.println (roads [i]);
		c.println (roads [i]);
		count++;
	    }
	}
	fo.println ("There are " + count + " disconnecting roads.");
	c.println ("There are " + count + " disconnecting roads.");
	fo.close ();
    }


    // Warshall's classic transitive closure algorithm
    static public void warshalls (boolean [] [] a)
    {
	for (int y = 0 ; y < 26 ; y++)
	    for (int x = 0 ; x < 26 ; x++)
		if (a [x] [y])
		    for (int j = 0 ; j < 26 ; j++)
			if (a [y] [j])
			    a [x] [j] = true;
    }
}


