// CCC 2000
// Problem S3J5: Surfin'
//
// This reads a series of web pages and the idea is to find the links
// to other pages and to determine if you can "get there from here"
// Warshall's Transitive closure and 2D adjacency matrix are used.
//
// Input file consists of the the number of web pages
// first line is the URL of the web site
// links to other pages are in the form <A HREF="url">
// last line of website is </HTML>
// following the websites are a series of url pairs ending with
// "The End".

// Output links from A to B
// Statements if you can surf from 1st to 2nd url in the pairs.


import java.awt.*;
import hsa.*;

public class J5Surfin
{
    static Console c;           
    public static String [] url = new String [100];


    public static void main (String [] args)
    {
	c = new Console ();
	boolean [] [] adj = new boolean [100] [100];
	String line, to, a, b;
	int n, x, y, k;

	TextInputFile fi = new TextInputFile ("surf.in2");
	TextOutputFile fo = new TextOutputFile ("surf.ou2");

	for (int i = 0 ; i < 100 ; i++)
	    for (int j = 0 ; j < 100 ; j++)
	    {
		if (i == j)
		    adj [i] [j] = true;
		else
		    adj [i] [j] = false;
	    }

	// get the url's
	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    url [i] = fi.readLine ();
	    line = fi.readLine ();
	    while (!line.equals ("</HTML>"))
		line = fi.readLine ();
	}
	fi.close ();

	// get the links
	fi = new TextInputFile ("surf.in2");
	for (int i = 0 ; i < n ; i++)
	{
	    line = fi.readLine ();
	    line = fi.readLine ();
	    while (!line.equals ("</HTML>"))
	    {
		x = line.indexOf ("<A HREF=\"");
		while (x >= 0)
		{
		    y = line.indexOf ("\">", x + 9);
		    to = line.substring (x + 9, y);
		    fo.println ("Link from " + url [i] + " to " + to);
		    c.println ("Link from " + url [i] + " to " + to);
		    k = find (to, n);
		    adj [i] [k] = true;
		    line = line.substring (y + 2);
		    x = line.indexOf ("<A HREF=\"");
		}
		line = fi.readLine ();
	    }
	}

	// Transitive closure
	warshalls (adj);

	// read and print "can or can't surf"s, using the adjacency matrix
	a = fi.readLine ();
	fo.println ();
	c.println ();
	while (!a.equals ("The End"))
	{
	    b = fi.readLine ();
	    if (adj [find (a, n)] [find (b, n)])
	    {
		fo.println ("Can surf from " + a + " to " + b);
		c.println ("Can surf from " + a + " to " + b);
	    }
	    else
	    {
		fo.println ("Can't surf from " + a + " to " + b);
		c.println ("Can't surf from " + a + " to " + b);
	    }
	    a = fi.readLine ();
	}
	fi.close ();
	fo.close ();
    }


    // linear sreach for s in the url array.
    // this should always be found.
    static public int find (String s, int n)
    {
	int k = 0;
	while (k < n && !url [k].equals (s))
	    k++;
	if (k == n)
	    c.println ("Error: can't find " + s);
	return k;
    }


    // Warshall's classic transitive closure algorithm.
    static public void warshalls (boolean [] [] a)
    {
	for (int y = 0 ; y < 100 ; y++)
	    for (int x = 0 ; x < 100 ; x++)
		if (a [x] [y])
		    for (int j = 0 ; j < 100 ; j++)
			if (a [y] [j])
			    a [x] [j] = true;
    }
}
