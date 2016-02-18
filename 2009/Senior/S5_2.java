// CCC 2009
//
// S5: Wireless   V2     The better version :-)
//
// This algorithm is by Brian Bi of Woburn Collegiate Institute
//
// Amlesh Jayakumar and Daniel Galperin of Waterloo Collegiate Institute
// also came up with the same idea.
//  
// Here is the idea.
//
// Suppose you have an array x with values:
//      6 3 9 12 13 5 3
// stored in locations 0 to 6
//
// There is an alternate way to store these values: AS DIFFERENCES
// For example the above could be stored as
//      6 -3 6 3 1 -8 -2
// the idea is that element 0 is the normal value
// but for all the rest, the ith value is x[i] - x[i-1]
//
// Why do this? Because its very easy to add a value to
// a subset of the array with ONLY 2 assignments! 
//
// For example in the above, suppose we wanted to add 4 to the values
// of x from element 2 to 5. All we have to do is ADD 4 to element 2
// AND SUBTRACT 4 from element 6. Getting
//      6 -3 10 3 1 -8 -6
//
// To see that this is correct, expand it back out:
//      6 3 13 16 17 9 3
// TA DA! (4 was added to elements 2 thru 5)
//
// So in a sense this method works like my original solution, BUT mine
// took FOREVER to add the bitrate to ALL the values, this improves things 
// HUGE by just adding (or subtracting) the bitrate at the ends.
//
// Amlesh and Daniel described this method as adding the bitrate to the 
// left side of the cirle and subtracting the bitrate from the right side. 
// Exactly the same thing. 
//


import java.awt.*;
import hsa.*;

public class CCC2009S5WirelessV2
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s5.5.in");

	// diff[0][c] holds the running total at row 0 and column c;
	// diff[r][c] where r>0 holds the difference
	// between the running totals at (row r,column c) and (row r-1,column c).
	int[] [] diff;

	int cols, rows, col, row, radius, bitrate, left, right;
	rows = f.readInt ();
	cols = f.readInt ();
	diff = new int [rows+1] [cols+1];

	for (int i = 0 ; i <= rows ; i++)
	    for (int j = 0 ; j <= cols ; j++)
		diff [i] [j] = 0;

	int k = f.readInt ();
	for (int i = 0 ; i < k ; i++)
	{
	    col = f.readInt ();
	    row = f.readInt ();
	    radius = f.readInt ();
	    bitrate = f.readInt ();

	    row--; 
	    col--; //for zero-based indices
	    
	    //range of columns covered: the circle extends a number of columns in each direction equal to its radius,
	    //but if it goes beyond the boundaries, then we don't consider those outside slices.
	    for (int j = Math.max (0, col - radius) ; j <= Math.min (cols - 1, col + radius) ; j++)
	    {
		// from the equation of the circle we calculate 
		// left, the first row in this column covered by this circle
		// right, the last row in this column covered by this circle.
		left = Math.max (0, row - (int) Math.sqrt (radius * radius - (col - j) * (col - j)));
		right = Math.min (rows - 1, row + (int) Math.sqrt (radius * radius - (col - j) * (col - j)));
		diff [left] [j] += bitrate;
		diff [right + 1] [j] -= bitrate;
	    }
	}
	int best = 0;
	int count = 0;
	for (int i = 0 ; i < rows ; i++)
	    for (int j = 0 ; j < cols ; j++)
	    {
		//calculate the actual bitrate at (i,j) by adding
		if (i > 0)
		    diff [i] [j] += diff [i - 1] [j]; 
		if (diff [i] [j] == best)
		    count++;
		if (diff [i] [j] > best)
		{
		    best = diff [i] [j];
		    count = 1;
		}
	    }
	c.println (best + "\n" + count);
    }
}
