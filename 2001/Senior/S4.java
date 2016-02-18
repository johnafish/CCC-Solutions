// The "CCC2001S4Cookie" class.
//
// this problem has been a great source of frustatation. It has taken
// 4 years to get a "proper" solution, thanks to Richard Peng. :-)
//
// The algorithm is to use the formula for finding the radius of a
// circle circumscribing a triangle with sides a, b, c.
// that formula is: r = abc/4*sqrt(s(s-a)(s-b)(s-c) where
// s is the semi perimeter: (a+b+c)/2
//
// The minimum diameter needed to encompass three points is thus
// this circle (if the points make an acute triangle) or the
// length of the longest side in an obtuse triangle.
//
// So from a programming point of view:
// the idea is to take every possible combination of 3 points,
// calculate the diameter of the circle for each
// and the maximum diameter of these is the answer.
//
// File I/O
// Input: n and the locations of n chips
// Ouput: diameter of circle which enclosed them.


import java.awt.*;
import hsa.*;

public class CCC2001S4Cookies
{
    public static void main (String[] args)
    {
	double[] x, y;
	int n;
	x = new double [10];
	y = new double [10];

	double a, b, c, s, d, ans;

	TextInputFile fi = new TextInputFile ("cookie5.in");
	TextOutputFile fo = new TextOutputFile ("cookie5.out");

	// get the chip locations
	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    x [i] = fi.readDouble ();
	    y [i] = fi.readDouble ();
	}

	ans = 0;
	for (int i = 0 ; i < n ; i++)
	    for (int j = i + 1 ; j < n ; j++)
		for (int k = j + 1 ; k < n ; k++)
		{
		    a = Math.sqrt ((x [i] - x [j]) * (x [i] - x [j]) + (y [i] - y [j]) * (y [i] - y [j]));
		    b = Math.sqrt ((x [j] - x [k]) * (x [j] - x [k]) + (y [j] - y [k]) * (y [j] - y [k]));
		    c = Math.sqrt ((x [k] - x [i]) * (x [k] - x [i]) + (y [k] - y [i]) * (y [k] - y [i]));
		    s = (a + b + c) / 2;
		    d = 0;
		    // check for division by zero or obtuse triangle
		    if ((s == 0) || (a * a + b * b - c * c < 0) || (b * b + c * c - a * a < 0) || (c * c + a * a - b * b < 0))
		    {
			if (a > d)
			    d = a;
			if (b > d)
			    d = b;
			if (c > d)
			    d = c;
		    }
		    else
			d = 2 * (a * b * c) / (4 * Math.sqrt (s * (s - a) * (s - b) * (s - c)));
		    if (d > ans)
			ans = d;
		}

	fo.println (ans, 0, 2);

	fo.close ();
	fi.close ();
    }
}

