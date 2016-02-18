// CCC 2000
// Problem S4 Golf

// This is a classic Dynamic programming problem
// given a set of numbers(clubs), determine the total number of clubs
// needed to get to a given distance.

// (full solution giving the clubs themselves is in javapgms)

// For example if there were clubs 2,4,5 and the total distance was 12
// then it would take 3 clubs (5, 5 and 2)

// method is calculate # for all distances from 0 to given!
// F(0) = 0
// F(n) = (minimum of F(n-club(i)) i=0 to #clubs, provided minimum >= 0) + 1

// using the above example of 2, 4 and 5 clubs you would have:
// F(0) = 0
// F(1) = -1   (can't get there)
// F(2) = 1
// F(3) = -1
// F(4) = 1
// F(5) = 1
// F(6) = 2 because F(6-2) = 1 is min, therefore answer is F(4) + 1 = 2
// F(7) = 2 because F(7-2) = 1 is min, therefore answer is F(5) + 1 = 2
// F(8) = 2 because F(8-4) = 1 is min, therefore answer is F(4) + 1 = 2
// F(9) = 2 because F(9-4) = 1 is min, therefore answer is F(5) + 1 = 2
// F(10) = 2 because F(10-5) = 1 is min, therefore answer is F(5) + 1 = 2
// F(11) = 3 because F(11-2) = 2 is min, therefore answer is F(9) + 1 = 3
// F(12) = 3 because F(12-2) = 2 is min, therefore answer is F(10) + 1 = 3

// file consists of distance, number of clubs and then the clubs.
// output is a statement about number of strokes, or "defeat"

import java.awt.*;
import hsa.*;


public class S4Golf
{
    static Console c;

    public static void main (String [] args)
    {
	c = new Console ();
	int club [] = new int [32];
	int dis, n, ans;

	TextInputFile fi = new TextInputFile ("golf.in1");
	TextOutputFile fo = new TextOutputFile ("golf.ou1");

	dis = fi.readInt ();
	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	    club [i] = fi.readInt ();
	ans = solve (dis, club, n);
	if (ans == -1)
	{
	    fo.println ("Roberta acknowledges defeat.");
	    c.println ("Roberta acknowledges defeat.");
	}
	else
	{
	    fo.println ("Roberta wins in " + ans + " strokes.");
	    c.println ("Roberta wins in " + ans + " strokes.");
	}
    }


    public static int solve (int distance, int [] club, int n)
    {
	int [] f;
	int min, t;

	f = new int [distance + 1];

	f [0] = 0;

	for (int x = 1 ; x <= distance ; x++)
	{
	    min = 999999999;
	    for (int j = 0 ; j < n ; j++)
	    {
		t = x - club [j];
		if (t >= 0 && f [t] >= 0 && f [t] < min)
		    min = f [t];
	    }
	    if (min < 999999999)
		f [x] = min + 1;
	    else
		f [x] = -1;
	}
	return f [distance];
    }
}


