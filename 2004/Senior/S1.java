// CCC 2004
// Problem S1 Fix
//
// given a series of 3 words, determine if any are prefixes or suffixes
// of each other.
//
// simple ifs of all combos and built-in string functions do the trick.

import java.awt.*;
import hsa.*;

public class S1Fix
{
    static Console c;


    public static void main (String[] args)
    {
	c = new Console ();
	String file;
	int n;
	String[] word = new String [3];

	c.print ("file name: ");
	file = c.readString ();
	TextInputFile fi = new TextInputFile (file);

	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    for (int j = 0 ; j < 3 ; j++)
		word [j] = fi.readLine ();
	    if (word [0].startsWith (word [1]) ||
		    word [0].startsWith (word [2]) ||
		    word [1].startsWith (word [2]) ||
		    word [1].startsWith (word [0]) ||
		    word [2].startsWith (word [0]) ||
		    word [2].startsWith (word [1]) ||
		    word [0].endsWith (word [1]) ||
		    word [0].endsWith (word [2]) ||
		    word [1].endsWith (word [2]) ||
		    word [1].endsWith (word [0]) ||
		    word [2].endsWith (word [0]) ||
		    word [2].endsWith (word [1]))
		c.println ("NO");
	    else
		c.println ("YES");

	}
    }
}

