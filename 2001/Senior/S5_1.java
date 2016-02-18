// CCC 2001
// Problem S5: Post's Correspondence Problem

// given a value of m and two sequences of strings,
// is there a combination of strings from the first group that equals the
// same combination from the second group.
// That is: is there a sequence of ai1+ai2+ai3...+aik = bi1+bi2+bi3+...+bik
// where 0 < k < m

// a more or less direct recursive approach does the trick.

// File I/O
// Input: m and n and the strings for the two groups
// Ouput: k, and i1, i2, ..., ik

// CAUTION: This takes a LONG time to solve post4.in, but it does work :-)

import java.awt.*;
import hsa.*;

public class S5Post
{
    static Console c;
    static String [] a, b;
    static int m, n, k;
    static int [] iarray;

    public static void main (String [] args)
    {
	c = new Console ();

	a = new String [40];
	b = new String [40];
	iarray = new int [40];

	TextInputFile fi = new TextInputFile ("post3.in");
	TextOutputFile fo = new TextOutputFile ("post3.out");

	// read info
	m = fi.readInt ();
	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	    a [i] = fi.readString ();
	for (int i = 0 ; i < n ; i++)
	    b [i] = fi.readString ();

	// the array with the answer, iarray has good values from 0 to k
	// inclusive and due to Java which counts 0,1,2.. all numbers
	// are printed +1 so they count 1,2,3...
	if (Post ("", "", 0))
	{
	    c.println (k + 1);
	    for (int f = 0 ; f <= k ; f++)
		c.println (iarray [f] + 1);
	}
	else
	    c.println ("No solution.");
    }

    
    // In pseudo English:
    // ta and tb are the two strings you've created so far using a & b arrays
    // p counts the number of strings added
    // 
    // if you've added more than m strings 
    //      you're toast (can't do it)     
    // else if the two strings are equal (and not just empty)
    //      you win! 
    // else if the strings aren't equal so far
    //      you're toast
    // else
    //      add all the posssible strings from and a and b, stopping if any 
    //      actually work!
    //      the iarray keeps the list of i's used to create the strings   
    static boolean Post (String ta, String tb, int p)
    {
	boolean okay;
	if (p > m)
	    return false;
	else if (ta.equals (tb) && p > 0)
	    return true;
	else if (!equalSoFar (ta, tb))
	    return false;
	else
	{
	    int i = 0;
	    boolean done = false;
	    while (i < n && !done)
	    {
		k = p;
		iarray [k] = i;
		done = Post (ta + a [i], tb + b [i], p + 1);
		i++;
	    }
	    return done;
	}
    }

    // returns true if the shorter of two string a and b equals the 
    // first part of the the longer string. 
    // eg "aaab" and "aaabab" is true
    //    "aaab" and "aab" is false
    static boolean equalSoFar (String a, String b)
    {
	if (a.equals (b))
	    return true;
	else if (a.length () < b.length ())
	    return b.startsWith (a);
	else if (a.length () > b.length ())
	    return a.startsWith (b);
	else
	    return false;
    }
}



