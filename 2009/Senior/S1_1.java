// CCC 2009
//
// S1: Cool Numbers
//
// For the first question this is TRICKY. Shame on the CCC. :-(
//
// The trick is you can't just try all the numbers from a to b
// to see if they are perfect squares and cubes. ITS TOO SLOW!!!
//
// You need to walk thru the cubes, between a and b, checking
// if any are perfect squares as well. 
// (To do that find the cuberoot of a and walk thru the main loop
// incrementing the cuberoot by 1 until the cube exceeds b.)
// 

import java.awt.*;
import hsa.*;

public class CCC2009S1CoolNumbers
{

    public static void main (String[] args)
    {
	Console c;
	int a, b;
	int cubeRoot, cube, squareRoot, square;
	int count;

	c = new Console ();
	TextInputFile f = new TextInputFile ("s1.4.in");
	a = f.readInt ();
	b = f.readInt ();
	count = 0;
	cubeRoot = (int) (Math.pow (a, 1.0 / 3));
	cube = cubeRoot * cubeRoot * cubeRoot;
	while (cube <= b)
	{
	    if (cube >= a)   // because of truncation, 
			     // I might be starting below a, hence the if
	    {
		squareRoot = (int) (Math.sqrt (cube));
		square = squareRoot * squareRoot;
		if (square == cube)
		    count++;
	    }
	    cubeRoot++;
	    cube = cubeRoot * cubeRoot * cubeRoot;
	}
	c.println (count);
    }
}

