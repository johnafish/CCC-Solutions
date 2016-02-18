// CCC 2007
//
// S4: Waterpark
// Algorithm by Matthew Lai
//
// Ignoring the hint given in the problem,
// Matthew Lai was successful in creating a
// straight forward recursive approach, but with a
// clever time saving twist.
//
// the idea is that the number of paths from a point
// is simply the sum of the paths from the points below
// this point.

// Matthew's insight was that a straight
// recursion might hit the same point many times over
// in the recursion process, so instead, remember the
// the value and if you've ever been to a point
// before, don't recurse down, just use the remembered
// value. (This insight turns an impossibly slow
// algorithm into a reasonably fast one.)

import hsa.*;

public class CCC2007S4WaterparkRecursion
{
    static Console c;
    static int n;
    static int[] paths;  // array used to keep
    // # of paths from this point down
    static int numPaths = 0;
    static int[] startingPts;
    static int[] endingPts;

    public static void main (String[] args)
    {
	c = new Console ();


	// one BIG issue is how many paths?
	// one could use a linked list to store them
	// or keep expanding an array to hold each new
	// value. OR
	// a third approach is to cheat: Read the file twice!
	// read it to get the number of paths, then create
	// the arrays and read it for real the second time.

	TextInputFile f = new TextInputFile ("s4.4.in");
	n = f.readInt ();
	paths = new int [n + 1];
	for (int i = 0 ; i < n + 1 ; i++)
	    paths [i] = -1;

	// this is the cheat , reading ONLY to get
	// only the number of paths
	numPaths = 0;
	int x = f.readInt ();
	int y = f.readInt ();
	while (!(x == 0 && y == 0))
	{
	    numPaths++;
	    x = f.readInt ();
	    y = f.readInt ();
	}
	f.close ();


	// this does the real reading of the paths
	startingPts = new int [numPaths];
	endingPts = new int [numPaths];
	f = new TextInputFile ("s4.4.in");
	n = f.readInt ();
	numPaths = 0;
	x = f.readInt ();
	y = f.readInt ();
	while (!(x == 0 && y == 0))
	{
	    startingPts [numPaths] = x;
	    endingPts [numPaths] = y;
	    numPaths++;
	    x = f.readInt ();
	    y = f.readInt ();
	}
	f.close ();

	// Now that we have all the data,
	// we're done :-)
	c.println (findNumPaths (1));
    }


    // simpler, without - memory version (VERY slow)
    /*
    static public int findNumPaths (int pt)
    {
	if (pt == n)
	    return 1;
	else
	{
	    int sum = 0;
	    for (int i = 0 ; i < numPaths ; ++i)
		if (startingPts [i] == pt)
		    sum += findNumPaths (endingPts [i]);
	    return sum;
	}
    }
    */

    static public int findNumPaths (int pt)
    {
	if (pt == n)
	    return 1;
	else
	{
	    int sum = 0;
	    for (int i = 0 ; i < numPaths ; i++)
	    {
		if (startingPts [i] == pt)
		{
		    int answer = paths [endingPts [i]];
		    if (answer != -1)
			sum += answer;
		    else
		    {
			int a = findNumPaths (endingPts [i]);
			paths [endingPts [i]] = a;
			sum += a;
		    }
		}
	    }
	    return sum;
	}
    }
}

