// CCC 1999
// Problem 5: Letter Arithmethic

// I can see NO way any student could solve this problem
// (having never seen it before), plus 4 other problems in 3 hours!
// BUT here's a solution for what it's worth.

// This converts SEND + MORE = MONEY
// into the numbers that make it work.

// exhaustive search will be used. all permutations of 0,1,2,3,4,5,6,7,8,9
// will be tried.

// file input and output.
// input:  number of triple, then the triples themselves

import java.awt.*;
import hsa.*;

public class P5LetterArithmetic
{
    static Console cc;
    static public int [] a = new int [10]; // one permutation to be used
    static public String [] s = new String [3]; // the 3 strings
    static public String nodups;
    static public int cn = 9; // 0.. 9 digits are used
    static public int pn;     // number of unique letters
    static public byte [] value = new byte [255]; // value of 'A', etc...
    static public boolean stop;
    static public TextInputFile fi = new TextInputFile ("letter.in");
    static public TextOutputFile fo = new TextOutputFile ("letter.out");

    public static void main (String [] args)
    {
	cc = new Console ();
	int count;

	count = fi.readInt ();
	for (int i = 1 ; i <= count ; i++)
	{
	    s [0] = fi.readString ();
	    s [1] = fi.readString ();
	    s [2] = fi.readString ();
	    stop = false;
	    nodups = findUnique (s);
	    pn = nodups.length () - 1; // sets the length of the permutations
	    doit (s, nodups);
	}
    }


    // Given a string, it returns all the unique letters in the string
    public static String findUnique (String [] s)
    {
	String u = new String ();
	int k;
	u = "";
	for (int j = 0 ; j < 3 ; j++)
	    for (int i = 0 ; i < s [j].length () ; i++)
	    {
		k = 0;
		while (k < u.length () && u.charAt (k) != s [j].charAt (i))
		    k++;
		if (k >= u.length ())
		    u = u + s [j].charAt (i);
	    }
	return u;
    }


    public static void doit (String [] s, String u)
    {
	// need to generate all combinations of u.length() numbers
	// from the set 0 to 9 AND THEN all the permutations of each set!
	// UGH!!!
	choose (0, pn);

    }


    // this will generate all combinations of r numbers from the set 0,1,..9
    // call with 0, r-1
    public static void choose (int b, int c)
    {
	if (c == -1)
	    permute (0);
	else if (!stop)
	    for (int i = b ; i < cn - c + 1 ; i++)
	    {
		a [c] = i;
		choose (i + 1, c - 1);
	    }
    }


    // generates all the permutations of the array a.
    // array a has values 0, 1, 2, ..., pn (inclusive)
    public static void permute (int i)
    {
	int t;
	if (i > pn)
	    process ();
	else if (!stop)
	{
	    permute (i + 1);
	    for (int j = i + 1 ; j <= pn ; j++)
	    {
		t = a [j];
		a [j] = a [i];
		a [i] = t;
		permute (i + 1);
		t = a [j];
		a [j] = a [i];
		a [i] = t;
	    }
	}
    }


    public static void process ()
    {
	// move the combination of numbers (a) into the value array
	for (int i = 0 ; i < nodups.length () ; i++)
	    value [nodups.charAt (i)] = (byte) a [i];

	// if the permutation is okay, print it!
	// and stop the permutation recursion process
	if (okay ())
	{
	    for (byte j = 0 ; j < s [0].length () ; j++)
	    {
		cc.print (value [s [0].charAt (j)]);
		fo.print (value [s [0].charAt (j)]);
	    }
	    cc.println ();
	    fo.println ();
	    for (byte j = 0 ; j < s [1].length () ; j++)
	    {
		cc.print (value [s [1].charAt (j)]);
		fo.print (value [s [1].charAt (j)]);
	    }
	    cc.println ("");
	    fo.println ();
	    for (byte j = 0 ; j < s [2].length () ; j++)
	    {
		cc.print (value [s [2].charAt (j)]);
		fo.print (value [s [2].charAt (j)]);
	    }
	    cc.println ("");
	    fo.println ();
	    cc.println ("");
	    fo.println ();
	    stop = true;
	}
    }


    // Checks if the values for the permutation, using
    // combination of s[0] + s[1] = s[2] actually works
    public static boolean okay ()
    {
	int carry = 0;
	int t;
	int j = s [0].length () - 1;
	int k = s [1].length () - 1;
	int i = s [2].length () - 1;
	boolean fine = true;
	while (i >= 0 && fine)
	{
	    t = carry;
	    if (j >= 0)
		t = t + value [s [0].charAt (j--)];
	    if (k >= 0)
		t = t + value [s [1].charAt (k--)];
	    carry = t / 10;
	    t = t % 10;
	    fine = t == value [s [2].charAt (i--)];
	}
	return fine && carry == 0;
    }
}


