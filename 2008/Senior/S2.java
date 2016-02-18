// CCC 2008
//
// S2: Pennies in the Ring
//
// This is a relatively simple geometry problem.
//
// Think of the cirles as four quadrants, plus the centre.
// Calculate the pennies in the 1st quadrant, then the answer
// is 4 times that, plus the central penny on the origin.
//
// to calculate the pennies in a quadrant I calculate the 
// pennies above the point x=1, then above x=2, ...
// the # pennies is just the integer value of the height 
// of the circle at that point.
// One trick: don't forget to add the penny on the x-axis
// (hence the +1 inside the loop)
//
// I ensure all calculations are done in the double world
// and only converted to integers at the last moment.
//

import java.awt.*;
import hsa.*;

public class CCC2008S2PenniesInTheRing
{
    static Console c;

    public static void main (String[] args)
    {

	int radius, pennies;
	double r2;
	c = new Console ();
	TextInputFile f = new TextInputFile ("s2.5.in");

	radius = f.readInt ();
	while (radius != 0)
	{
	    pennies = 0;
	    r2 = (double) (radius) * radius;
	    for (int x = 1 ; x <= radius ; x++)
		pennies += (int) Math.sqrt (r2 - (double) x * x) + 1;
	    pennies = 4 * pennies + 1;
	    c.println (pennies);
	    radius = f.readInt ();
	}
    }
}
