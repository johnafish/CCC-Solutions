// The "CCC2005s5PinballRanking" class.
//
// this is essentially a sorting problem
// HOWEVER one can't use any sorting method. In
// particular one can't use quick sort because each element
// must be "inserted" into proper order, one at a time
// so it's ranking can be determined. (The array is sorted
// descending so that the rank = index.)
//
// This solution works but takes ~1:30 on a P4 1.2 Ghz machine, for 
// the two worse data files 7 and 8. This could be solved by
// first getting a rough sense of original order then sorting
// the reverse way if necessary. But this smacks of cheating to me :-)
//
// This solution takes ~30 seconds on a P4 2.8 GHz machine, for 
// the two worse data files 7 and 8.... So When Waterloo says 
// "only 1 minute per test case" what is the base line machine?

// For THIS solution, hardware (not software) determines the score !!!!


import java.awt.*;
import hsa.*;

public class CCC2005s5PinballRanking
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s5.8.in");

	int t, i, hold;
	int[] s = new int [100001];
	long sum = 0;

	t = f.readInt ();
	s [0] = Integer.MAX_VALUE;  // used as a sentinel so inner while
				    // need not ask if i >= 0
	for (int k = 1 ; k <= t ; k++)
	{
	    hold = f.readInt ();
	    // use insertion sort: s decreasing
	    i = k - 1;
	    while (hold >= s [i])
	    {
		s [i + 1] = s [i];
		i--;
	    }
	    s [i + 1] = hold;
	    sum += i + 1;
	}
	double x = (double) (sum) / t;
	c.println (x, 0, 2);
    }
}
