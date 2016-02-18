// CCC 2003
// Problem S4 Substrings
//
// given a string, determine the number of DISTINCT substrings
// the empty string and the full string are counted.
//
// this involves taking each possible substring and
// checking if it is a duplicate.
//
// algorithm is:
// for each possible substring:
//   if the substring is found where you created it, it's not a dup.
//   else if that substring occurs earlier in the string it is a dup.
//
// this observation is due to Vassili Skarine, Northview Heights SS
//
// file input: number of strings and the strings themselves
// output the # of distinct substrings

import java.awt.*;
import hsa.*;

public class S4SubstringsVS
{
    static Console cc;
    //    static String [] sub;
    //    static int ns;

    public static void main (String[] args)
    {
	cc = new Console ();

	String s, t;
	int n;
	int total = 0;
	TextInputFile fi = new TextInputFile ("substr3.in");
	TextOutputFile fo = new TextOutputFile ("substr3.out");

	n = fi.readInt ();

	for (int i = 0 ; i < n ; i++)
	{
	    // read the string
	    s = fi.readLine ();
	    total = 2;

	    // for each different length
	    for (int k = 1 ; k < s.length () ; k++)
	    {
		for (int j = 0 ; j <= s.length () - k ; j++)
		{
		    t = s.substring (j, j + k);
		    // if found where it was created, not a dup
		    // otherwise a dup: don't count it.
		    if (s.indexOf (t) == j)
			total++;
		}
	    }
	    fo.println (total);
	    cc.println (total);
	}
	fi.close ();
	fo.close ();
    }
}
