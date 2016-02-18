// CCC 2002
// Problem S5: bouncing ball

// fixed previous bug: Jan 2005

// given the width and height
// starting position startx,0 and first bounce width,starty
// the slope of the line is starty / (width-startx)
// the thus y = slope * (x-startx) is the equation of the line

// if you project out this line and look where
// multiples(k) of width or height hit the line,
// test if they are within 5 of a multiple of the other

// finally the # bounces are the number of widths and heights
// that divide into this point.

import java.awt.*;
import hsa.*;

public class S5Bounce
{

    public static void main (String[] args)
    {
	double width, height, startx, starty, x, y, a, b;
	double slope;
	Console c = new Console ();

	TextInputFile fi = new TextInputFile ("ball5.in");
	TextOutputFile fo = new TextOutputFile ("ball5.out");
	int numwidth, numheight, k, bounce;
	boolean done;

	// read info;
	width = fi.readLong ();
	height = fi.readLong ();
	startx = fi.readLong ();
	starty = fi.readLong ();

	done = false;
	slope = starty / (width - startx);
	for (k = 1 ; k <= 1000000 && !done ; k++)
	{
	    // calculate where the line hits multiples of the width
	    y = slope * ((k * width) - startx);
	    // calculate where the line hits multiples of the height
	    x = (k * height) / slope + startx;

	    // calculate the number of heights, closest to y
	    numheight = (int) ((y - (height / 2)) / height) + 1;
	    // calculate the number of widths, closest to x
	    numwidth = (int) ((x - (width / 2)) / width) + 1;
	    a = numheight * height;
	    b = numwidth * width;

	    if ((Math.abs (a - y) < 5) || (Math.abs (b - x) < 5))
	    {
		// if sunk on multiple of width, bounces = 1 less than
		// numbers of widths(k), plus the number of heights in this y value
		if (Math.abs (a - y) < 5)
		    if (a != y)
			bounce = k - 1 + (int) (y / height);
		    else
			// if it hit precisely, don't count last one
			bounce = k - 1 + (int) (y / height) - 1;

		// if sunk on multiple of height, bounces = 1 less than
		// numbers of heights(k), plus the number of widths in this x value
		else
		    if (b != x)
			bounce = k - 1 + (int) (x / width);
		    else
			// if it hit precisely, don't count last one
			bounce = k - 1 + (int) (x / width) - 1;

		fo.println (bounce);
		c.println (bounce);
		done = true;
	    }

	}
	if (!done)
	{
	    fo.println ("0");
	    c.println ("0");
	}
	fi.close ();
	fo.close ();
    }
}


