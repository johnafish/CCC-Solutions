// CCC2006s4Groups
//
// this is a rather straight forward 2D array problem
//
// read and test for closure
// find the identity, if there is one
// test for inverses
// test for associativity
//
// quit as soon as any test fails
//

import java.awt.*;
import hsa.*;

public class CCC2006s4Groups
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s4.1.in");
	int[] [] g;
	int n;
	int identity;
	boolean group, found;

	n = f.readInt ();
	group = false;
	while (n > 0)
	{
	    group = true;
	    g = new int [n + 1] [n + 1];

	    // step 1: read the group and test for closure
	    for (int i = 1 ; i <= n && group ; i++)
		for (int j = 1 ; j <= n && group ; j++)
		{
		    g [i] [j] = f.readInt ();
		    if (g [i] [j] < 1 || g [i] [j] > n)
			group = false;
		}

	    // step 2: find indentity, if there is one
	    // (one row must equal 1,2,3...) and its column must also
	    //  equal 1,2,3...)

	    identity = 0;
	    found = false;
	    for (int i = 1 ; i <= n && group && identity == 0 ; i++)
	    {
		found = true;
		int j;
		for (j = 1 ; j <= n && found ; j++)
		{
		    if (g [i] [j] != j)
			found = false;
		}
		if (found)
		{

		    identity = i;
		    for (int k = 1 ; k <= n && found ; k++)
			if (g [identity] [k] != k)
			    found = false;
		}
	    }
	    group = group && found;

	    // step 3: test for inverses
	    // every row MUST have an identity value
	    // and if its at i,j then j,i must also be the identity
	    for (int i = 1 ; i <= n && group ; i++)
	    {
		found = false;
		for (int j = 1 ; j <= n && !found ; j++)
		    if (g [i] [j] == identity && g [j] [i] == identity)
			found = true;
		group = group && found;
	    }

	    // step 4: test for associativity
	    // test every combo...
	    for (int i = 1 ; i <= n && group ; i++)
		for (int j = 1 ; j <= n && group ; j++)
		    for (int k = 1 ; k <= n && group ; k++)
			if (g [g [i] [j]] [k] != g [i] [g [j] [k]])
			    group = false;

	    if (group)
		c.println ("yes");
	    else
		c.println ("no");
	    n = f.readInt ();
	}
    }
}


