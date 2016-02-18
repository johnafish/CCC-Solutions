// CCC2006s3TinCanTelephone
//
// this is a geometry problem which can be simplified to:
// are two line segments touching or crossing?
// the answer is yes if their point of intersection is on
// both lines.
//
// you need to consider 4 cases in total
// parallel
//    both vertical
//    both angled
// non-parallel
//    one vertical
//    both angled
//

import java.awt.*;
import hsa.*;

public class CCC2006s3TinCanTelephone
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s3.1.in");
	int xr, yr, xj, yj;
	int n, corners;
	int x0, y0, x1, y1, x2, y2;
	boolean touching;
	int touch = 0;

	xr = f.readInt ();
	yr = f.readInt ();
	xj = f.readInt ();
	yj = f.readInt ();

	n = f.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    touching = false;
	    corners = f.readInt ();
	    x0 = f.readInt ();
	    y0 = f.readInt ();
	    x1 = x0;
	    y1 = y0;
	    for (int j = 1 ; j < corners ; j++)
	    {
		x2 = f.readInt ();
		y2 = f.readInt ();
		touching = touching || touchingSegments (xr, yr, xj, yj, x1, y1, x2, y2);
		x1 = x2;
		y1 = y2;
	    }
	    touching = touching || touchingSegments (xr, yr, xj, yj, x1, y1, x0, y0);
	    if (touching)
		touch++;
	}
	c.println (touch);
    }


    public static boolean touchingSegments (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
    {
	double m1, m2, b1, b2, xi, yi;
	if (x1 == x2)
	{
	    m1 = Double.MAX_VALUE;
	    b1 = 0;
	}
	else
	{
	    m1 = (y1 - y2) / (x1 - x2);
	    b1 = -m1 * x2 + y2;
	}
	if (x3 == x4)
	{
	    m2 = Double.MAX_VALUE;
	    b2 = 0;
	}
	else
	{
	    m2 = (y3 - y4) / (x3 - x4);
	    b2 = -m2 * x4 + y4;
	}

	// segments are parallel
	if (m1 == m2)
	{
	    // angled lines
	    // they touch if their y-intercepts are the same and
	    // one point of one segment is within the other segment
	    if (m1 != Double.MAX_VALUE && m2 != Double.MAX_VALUE)
		return b1 == b2 && (between (x3, x1, x2) || between (x4, x1, x2));

	    // lines are both vertical
	    // they touch if their x's are the same and y's overlap
	    else
		return x1 == x3 && (between (y3, y1, y2) || between (y4, y1, y2));
	}
	else
	{
	    // normal angled lines,
	    // touch if point of intersection is on both lines
	    if (m1 != Double.MAX_VALUE && m2 != Double.MAX_VALUE)
	    {
		xi = (b2 - b1) / (m1 - m2);
		yi = m1 * xi + b1;
		return between (xi, x1, x2) && between (yi, y1, y2) && between (xi, x3, x4) && between (yi, y3, y4);
	    }

	    // one line is a vertical
	    // touch if point of intersection is on both lines
	    else
	    {
		if (m1 == Double.MAX_VALUE)
		{
		    xi = x1;
		    yi = m2 * xi + b2;
		}
		else
		{
		    xi = x3;
		    yi = m1 * xi + b1;
		}
		return between (xi, x1, x2) && between (yi, y1, y2) && between (xi, x3, x4) && between (yi, y3, y4);
	    }
	}
    }


    // returns true if x is between a and b
    public static boolean between (double x, int a, int b)
    {
	return (x >= a && x <= b) || (x <= a && x >= b);
    }
}
