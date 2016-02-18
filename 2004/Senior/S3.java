// CCC 2004
// Problem S3 Spreadsheet
//
// 2d array of strings do the trick.
// Repeatedly traversing the array until no changes
// handles the calculations
// StringTokenizers seperate formulas.
//
// There are in fact two 2D arrays: one of strings (formula)
// and the other of ints (values)

import java.awt.*;
import java.util.*;
import hsa.*;

public class S3SpreadSheet
{
    static Console c;
    static String [] [] formula = new String [10] [9];
    static int [] [] value = new int [10] [9];

    public static void main (String [] args)
    {
	c = new Console ();
	String file;

	c.print ("file name: ");
	file = c.readString ();
	TextInputFile fi = new TextInputFile (file);

	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int j = 0 ; j < 9 ; j++)
	    {
		formula [i] [j] = fi.readString ();
		value [i] [j] = -1;
	    }
	}

	boolean change = true;
	while (change)
	{
	    change = false;
	    for (int i = 0 ; i < 10 ; i++)
		for (int j = 0 ; j < 9 ; j++)
		{
		    StringTokenizer t = new StringTokenizer (formula [i] [j], "+");
		    int v = 0;
		    int x = 0;
		    while (t.hasMoreTokens () && x != -1)
		    {
			x = Value (t.nextToken ());
			if (x == -1)
			    v = -1;
			else
			    v += x;
		    }
		    if (value [i] [j] != v)
		    {
			change = true;
			value [i] [j] = v;
		    }
		}
	}
	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int j = 0 ; j < 9 ; j++)
		if (value [i] [j] == -1)
		    c.print ("* ");
		else
		    c.print (value [i] [j] + " ");
	    c.println ();
	}

    }


    public static int Value (String s)
    {
	int v;
	if (s.charAt (0) >= 'A' && s.charAt (0) <= 'J')
	    v = value [s.charAt (0) - 'A'] [s.charAt (1) - '1'];
	else
	    v = Integer.parseInt (s);
	return v;
    }
}

