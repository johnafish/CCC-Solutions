// The "CCC2005s4PyramidMessageScheme" class.
//
// determine height of a tree problem. 
//
// assume the input of 8:a,c,a,d,a,h,b,h (the example in the problem)
// my idea to to record those names in a 1D array
// replacing duplicates, so as to "flatten out the tree"
// so to speak and showing its true depth.
//
// my array starts with the root, the last name.
// s (my array) = h (at start)
// then as I process the names it becomes sucessively
// s = h,a
// s = h,a,c
// s = h,a
// s = h,a,d
// s = h,a,
// s = h
// s = h,b
// s = h
//
// the deepest it ever got was 2 (I'm counting 0,1,2 in the array). 
// so the savings in time is 8*10 - 2*2*10  = 40
// (the new time is twice the depth of the tree * 10)

import java.awt.*;
import hsa.*;

public class CCC2005s4PyramidMessageScheme
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s4.in");

	int l, n, p, max;
	String[] s = new String [1000];  
	String[] hold = new String [1000];
	String t;

	l = f.readInt ();
	for (int k = 0 ; k < l ; k++)
	{
	    n = f.readInt ();
	    p = 0;
	    for (int i = 0 ; i < n ; i++)
		hold [p++] = f.readLine ();
	    max = 0;
	    // start the array with the last name
	    s [0] = hold [p - 1];
	    p = 0;
	    // start putting names into s
	    for (int i = 0 ; i < n ; i++)
	    {
		int look = 0;
		while (look <= p && !hold [i].equals (s [look]))
		    look++;
		s [look] = hold [i];
		p = look;
		if (p >= max)
		    max = p;
	    }
	    c.println ("" + (n * 10 - 2 * max * 10));
	}
    }
}
