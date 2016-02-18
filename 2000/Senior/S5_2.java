// CCC 2000
// Problem S5 Sheep and Coyotes
//
// This algorithm was developed from insights by Richard Peng.
//
// This uses the perpendicular bisector method:
// The idea is the point of intersection of the PB between 2 sheep
// divides the range into two sections: on left, the leftmost sheep will be
// eaten, on the right the rightmost sheep. In this way you can continue to 
// redefine the range in which the sheep may be eaten, by considering other 
// sheep. 

// Using each sheep in turn, 
// - set the range it can be eaten (0 to 1000)
// - calculate the point of intersection of the x axis
//    and the perpendicular bisector with every other sheep.
//   (if 2 points have the same x value, the higher point is "out")
// - depending whether the other sheep is to the left or right of 
//   the current sheep, adjust the range using this point
// if the range is unexistent (left > right) at the end, 
// set that sheep to be "out" (can't be eaten) 
// and in future don't use that sheep for any calculations
//
// Those sheep not eliminated can be eaten.
//
// file consists of number of sheep and their loactions.
// output is a list of closest sheep

import java.awt.*;
import hsa.*;


public class S5SheepandCoyotesPBMethod
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	double[] x = new double [100];
	double[] y = new double [100];
	boolean[] out = new boolean [100];   // list of "out" sheep
	double xm, ym, s, p;
	int n;

	TextInputFile fi = new TextInputFile ("sheep.in5");
	TextOutputFile fo = new TextOutputFile ("sheep.ou5");

	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    x [i] = fi.readDouble ();
	    y [i] = fi.readDouble ();
	    out [i] = false;
	}
	for (int i = 0 ; i < n ; i++)
	{
	    double left = 0, right = 1000;
	    for (int j = 0 ; j < n ; j++)
	    {
		if (!out [i] && !out [j] && i != j)
		{
		    xm = (x [i] + x [j]) / 2;
		    ym = (y [i] + y [j]) / 2;
		    s = (x [i] - x [j]) / (y [j] - y [i]);
		    if (s == 0)
		    {
			if (y [i] < y [j])
			    out [j] = true;
			else
			    out [i] = true;
		    }
		    else
		    {
			p = -ym / s + xm;
			if (x [j] < x [i])
			    left = Math.max (p, left);
			else
			    right = Math.min (p, right);
		    }
		}
	    }
	    if (left >= right)
		out [i] = true;
	}
	for (int j = 0 ; j < n ; j++)
	{
	    if (!out [j])
	    {
		fo.println ("The sheep at (" + x [j] + ", " + y [j] + ") might be eaten.");
		c.println ("The sheep at (" + x [j] + ", " + y [j] + ") might be eaten.");
	    }
	}
	fi.close ();
	fo.close ();
    }
}


