// CCC 2009
//
// J5/S3: Degrees of Separation
//
// this is relatively stright forward, except for the "s" command
// which requires Warshall's Algorithm for transitive closure.
//
// a square 2D array holds the relationships. I think a triangular array
// would have worked, but then I'd have to always order the two
// inputs. So keeping it square and always doing 2 things (to represent
// the dual nature of the connection) does simplify some things.
//
//


import java.awt.*;
import hsa.*;

public class CCC2009S3DegreesofSeparation
{

    public static void main (String[] args)
    {
	Console c;
	int[] [] g;

	c = new Console ();
	TextInputFile f = new TextInputFile ("s3.4.in");


	// initialize the grid
	g = new int [50] [50];
	for (int i = 0 ; i < 50 ; i++)
	    for (int j = 0 ; j < 50 ; j++)
		g [i] [j] = 0;
	g [1] [6] = 1;
	g [6] [1] = 1;
	g [2] [6] = 1;
	g [6] [2] = 1;
	g [3] [6] = 1;
	g [6] [3] = 1;
	g [4] [6] = 1;
	g [6] [4] = 1;
	g [5] [6] = 1;
	g [6] [5] = 1;
	g [7] [6] = 1;
	g [6] [7] = 1;
	g [3] [4] = 1;
	g [4] [3] = 1;
	g [4] [5] = 1;
	g [5] [4] = 1;
	g [3] [5] = 1;
	g [5] [3] = 1;
	g [3] [15] = 1;
	g [15] [3] = 1;
	g [13] [15] = 1;
	g [15] [13] = 1;
	g [14] [13] = 1;
	g [13] [14] = 1;
	g [12] [13] = 1;
	g [13] [12] = 1;
	g [7] [8] = 1;
	g [8] [7] = 1;
	g [8] [9] = 1;
	g [9] [8] = 1;
	g [9] [10] = 1;
	g [10] [9] = 1;
	g [9] [12] = 1;
	g [12] [9] = 1;
	g [10] [11] = 1;
	g [11] [10] = 1;
	g [11] [12] = 1;
	g [12] [11] = 1;
	g [16] [17] = 1;
	g [17] [16] = 1;
	g [16] [18] = 1;
	g [18] [16] = 1;
	g [18] [17] = 1;
	g [17] [18] = 1;


	// main processing loop
	char command;
	int x, y;
	command = f.readChar ();
	while (command != 'q')
	{
	    if (command == 'i')
	    {
		x = f.readInt ();
		y = f.readInt ();
		g [x] [y] = 1;
		g [y] [x] = 1;
	    }
	    else if (command == 'd')
	    {
		x = f.readInt ();
		y = f.readInt ();
		g [x] [y] = 0;
		g [y] [x] = 0;
	    }
	    else if (command == 'n')
	    {
		x = f.readInt ();
		int count = 0;
		for (int i = 0 ; i < 50 ; i++)
		    if (g [x] [i] == 1)
			count++;
		c.println (count);
	    }
	    else if (command == 'f')
	    {
		x = f.readInt ();
		int count = 0;
		count = friendofFriends (g, x);
		c.println (count);
	    }
	    else if (command == 's')
	    {
		x = f.readInt ();
		y = f.readInt ();
		int count = 0;
		count = shortestPath (g, x, y);
		if (count == 999)
		    c.println ("Not connected");
		else
		    c.println (count);
	    }
	    command = f.readChar ();
	}
    }


    // Friends of friends
    // If you can get from x to i and from i to j
    // then j is a friend of a friend of x.
    // (assuming you couldn't get directly from x to j,  and x not = j)
    // The original array is copied to a temp array q,
    // so as not to mess it up for furture commands.
    public static int friendofFriends (int[] [] g, int x)
    {
	int[] [] q = new int [50] [50];
	int count = 0;
	for (int i = 0 ; i < 50 ; i++)
	    for (int j = 0 ; j < 50 ; j++)
		q [i] [j] = g [i] [j];

	for (int i = 0 ; i < 50 ; i++)
	    if (q [x] [i] == 1)
		for (int j = 0 ; j < 50 ; j++)
		    if (q [i] [j] == 1 && j != x && q [x] [j] == 0)
			q [x] [j] = 2;

	for (int i = 0 ; i < 50 ; i++)
	    if (q [x] [i] == 2)
		count++;
	return count;
    }


    // Degree of Separation: i.e. shortest path!
    // This is Warshall's algorithm. 
    // (Prehaps a bit of overkill, but it works quickly)
    // The temp array is filled with 999 to indicate no connection,
    // if there is no direct connection.
    // The main loop works on the idea:
    // if you can get from i to j and from j to k
    // then you can get from i to k.
    // We are interested in the shortest path, so if
    // i to j takes 3 moves and j to k takes 5 moves
    // then i to k takes 8 moves, assuming it already had a value
    // greater than that. (Don't forget to set both directions!)
    public static int shortestPath (int[] [] g, int x, int y)
    {
	int[] [] q = new int [50] [50];
	int count = 0;
	for (int i = 0 ; i < 50 ; i++)
	    for (int j = 0 ; j < 50 ; j++)
		if (g [i] [j] == 1)
		    q [i] [j] = g [i] [j];
		else
		    q [i] [j] = 999;

	for (int i = 0 ; i < 50 ; i++)
	    for (int j = 0 ; j < 50 ; j++)
		if (q [i] [j] > 0)
		    for (int k = 0 ; k < 50 ; k++)
			if ((q [j] [k] > 0) && (q [i] [j] + q [j] [k] < q [i] [k]))
			{
			    q [i] [k] = q [i] [j] + q [j] [k];
			    q [k] [i] = q [i] [j] + q [j] [k];
			}

	return q [x] [y];
    }



}

