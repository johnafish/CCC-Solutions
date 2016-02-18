// The "CCC2005s5PinballRanking" class.
//
// this algorithm is by Robin Cheng of PineTree Secondary
//
// slowest time: 50 sec for #6 on a Dell GX280
// (compared to <1 sec for the merge approach on the same machine.)
//
// This is 2008. I'm not sure this would qualify as a correct solution
// due to time considerations on a typical machine of 3 years ago.
//
// This is a brute force, straight forward, approach.
//
// One cool part is how to calculate the average, AS YOU GO:
// I never knew how to do that before :-)
//
// If the average of n elements is a, what is the average of n+1 elements?
// Answer: let x be the (n+1)th value
// then the new average = a + (x-a)/(n+1)
// Proof: a + (x-a)/(n+1)
//       = ((n+1)a + x - a) / (n+1)
//       = (na + a + x - a) / (n+1)
//       = (na + x) / (n+1)
//       and that is the normal average because na is the sum of the 
//       first n elements. QED.

import java.awt.*;
import hsa.*;

public class CCC2005s5PinballRankingCounting
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s5.5.in");

	int n, count, rank;
	int[] s = new int [100000];
	double average = 0;

	n = f.readInt ();

	for (int i = 0 ; i < n ; i++)
	{
	    s [i] = f.readInt ();
	    count = 0;
	    for (int j = 0 ; j < i ; j++)
		if (s [j] > s [i])
		    count++;
	    rank = count + 1;
	    average = average + (rank - average) / (i + 1);
	}
	c.println (average, 0, 2);
    }
}
