// CCC2006s3TinCanTelephoneVector
//
// this vector solution is by Jason Jackson of Aurora high School

// Explaination:  (if the reader has a better explaination, please tell me)
// In vector speak:
//   a-b is a vector from b to a
//   a.b is the (length of the projection of a onto b) * (length of b)
//
//   wrt the dot product, it is its sign which is important:
//   if the two vectors a and b are placed end to end, the dot product of 0
//   indicates they are at right angles and if its positive they form
//   an acute angle. (REMEMBER that!)
//
// The problem is to see if a series of corners
// defining a building intersect the line  segment from r to j.
//
// so r and j are the main endpoints r-j (or j-r) is the main vector
// c is a corner of the building
// w is vector going at right angles to c from the projection of c
//         onto the r-j line. (got that: w goes AWAY from the main line)
//
// So where is c with regard to the main segment?
// there are 3 choices: its ON the line or "inside" or "outside".
// "Inside" means that the vectors FROM r to c and j are acute as well
// as the vectors FROM J to c and r. Or more simply,
// the projection of c onto the line rj actually hits BETWEEN r and j

import java.awt.*;
import hsa.*;

public class CCC2006s3TinCanTelephoneVector
{
    static Console console;

    public static void main (String[] args)
    {
	console = new Console ();
	TextInputFile f = new TextInputFile ("s3.1.in");

	Vector r, j, c, w, l, csj;
	int n, corners, touching;
	boolean outside, collision;

	r = new Vector (f.readDouble (), f.readDouble ());
	j = new Vector (f.readDouble (), f.readDouble ());

	n = f.readInt ();
	touching = 0;
	for (int i = 0 ; i < n ; i++)
	{
	    outside = true;
	    collision = false;
	    corners = f.readInt ();
	    l = new Vector ();
	    for (int k = 0 ; k < corners ; k++)
	    {
		c = new Vector (f.readDouble (), f.readDouble ());
		csj = c.subtract (j);
		w = csj.subtract (csj.projection (r.subtract (j)));

		// is the projection of c onto the line rj, BETWEEN r and j?
		// if yes, its inside (ie not outside)
		if (r.subtract (j).dot (csj) > 0 && j.subtract (r).dot (c.subtract (r)) > 0)
		    outside = false;

		// w is the vector perpendicular FROM the rj line to c
		// if its magnitude is zero its ON the line
		// or if the old w (l) and w form an obtuse angle that building's
		// side has crossed the rj line.
		if (w.magnitude () < 0.001 || w.dot (l) < 0)
		    collision = true;
		l = w;
	    }

	    // if any point was inside and there was any collision at all
	    // the building interferred with the tin can telephone
	    if (!outside && collision)
		touching++;
	}
	console.println (touching);
    }
}

class Vector
{
    protected double x, y;

    public Vector ()
    {
	x = 0;
	y = 0;
    }

    public Vector (double x, double y)
    {
	this.x = x;
	this.y = y;
    }

    public String toString ()
    {
	return "(" + x + "," + y + ")";
    }

    public Vector add (Vector a)
    {
	return new Vector (x + a.x, y + a.y);
    }

    public Vector subtract (Vector a)
    {
	return new Vector (x - a.x, y - a.y);
    }

    public Vector multiply (double a)
    {
	return new Vector (a * x, a * y);
    }

    public double magnitude ()
    {
	return Math.sqrt (x * x + y * y);
    }

    public double dot (Vector a)
    {
	return x * a.x + y * a.y;
    }

    public Vector projection (Vector a)
    {
	return a.multiply (dot (a) / (a.magnitude () * a.magnitude ()));
    }
}


