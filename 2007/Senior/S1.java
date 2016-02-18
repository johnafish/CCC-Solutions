// CCC 2007
//
// S1: Federal voting Age
//
// You are 18 or old on 27 Feb 2007 if you were born
// on or before 27 Feb 1989. So this is an If statement.
//

import java.awt.*;
import hsa.*;

public class CCC2007S1FederalVotingAge
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s1.3.in");
	int n, y, m, d;

	n = f.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    y = f.readInt ();
	    m = f.readInt ();
	    d = f.readInt ();
	    if ((y < 1989) ||
		    (y == 1989 && m < 2) ||
		    (y == 1989 && m == 2 && d <= 27))
		c.println ("Yes");
	    else
		c.println ("No");
	}
    }
}
