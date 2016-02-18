// CCC 2009
//
// S2: The Lights Going On and Off
//
// Relatively straightforward array processing exercise
//
// Working with a row and the row below, create all the
// possible rows, remebering all the different ones.
// On the next iteration, you have to use all the different 
// rows generated before, with the current row. 
//
// Rows are stored as a string og 0's and 1's. 
//
// "Above" is the array of different rows above
// "Below" is the array of different rows created from the 
// above rows and the given row at a given level.
//
// In this way "above" becomes "below" on each loop. 
//
// the final number of different "below" rows is the answer
// 
import java.awt.*;
import hsa.*;

public class CCC2009S2Lights
{

    public static void main (String[] args)
    {
	Console c;
	String[] rows;
	String[] above = new String [256];
	int aboveSize;
	String[] below = new String [256];
	int belowSize;
	int r, l, k;
	String newrow;

	c = new Console ();
	
	// input
	TextInputFile f = new TextInputFile ("s2.5.in");
	r = f.readInt ();
	l = f.readInt ();
	rows = new String [r];
	for (int i = 0 ; i < r ; i++)
	{
	    rows [i] = "";
	    for (int j = 0 ; j < l ; j++)
		rows [i] = rows [i] + f.readString ();
	}

	// main processing loop
	above [0] = rows [0];
	aboveSize = 1;
	belowSize = 1;
	for (int i = 1 ; i < r ; i++)
	{
	    below [0] = rows [i];
	    belowSize = 1;
	    for (int j = 0 ; j < aboveSize ; j++)
	    {
		newrow = pushButton (above [j], below [0]);

		// add newrow to below array if not already there
		k = 0;
		while (k < belowSize && !below [k].equals (newrow))
		    k++;
		if (k >= belowSize)
		{
		    below [belowSize] = newrow;
		    belowSize++;
		}
	    }
	    
	    // transfer below into above for next iteration
	    for (int j = 0; j < belowSize; j++)
		above[j] = below[j];
	    aboveSize = belowSize;
	}
	c.println (belowSize);
    }


    // this does the "exclusive or" on two strings of 0 and 1's
    // assumes s and t are the same length and only have 0 or 1's
    public static String pushButton (String s, String t)
    {
	String x;
	x = "";
	for (int i = 0 ; i < s.length () ; i++)
	    if (s.charAt (i) == t.charAt (i))
		x = x + "0";
	    else
		x = x + "1";
	return x;
    }
}

