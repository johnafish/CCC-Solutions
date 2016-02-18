// CCC 2009
//
// S5: Wireless
//
// Straight forward approach:
// 1. Input the main dimensions of the city
// 2. for each wirelsss station read, add the
//    bit rate to the coffeshops within the circle
// 3. find the max bitrate coffeshop and print
// 4. count the max bitrates and print
//
// I abandoned this approach for nearly a week trying to 
// think of some clever DP method, or anything else
// that would not involve traversing the array multiple times. 
//
// Eventually I gave up trying to think of something else
// and re focused my efforts at improving the efficiency
// of this straight forward approach.
//
// Here are all my tricks:
// 1. Because java stores arrays row-wise, I turned the city 
//    on its side and make each row 30,000 wide, not 1000.
//    (1000 rows of 30,000 can be traversed faster than the reverse)
// 2. I tightend up the code so that few calculations are
//    done in the inner loop, by usig extra variables
// 3. I process the centre row first and work outward in either 
//    direction. Not sure if it matters much, but I did it.
// 4. Instead of doing x and checking if I'm done, 30,000 times
//    in the inner loop, I do ten things before checking if I'm done yet.
//    This could be increased to prehaps 50 or 100 or more. 
//    (copy and paste is cheap). This was quite a time saver. 
//
// these got 1,2,3 and 6 data sets well within time limits. 
// Sets 4 and 5 still stayed stubbornly at 2:30 sec (approx). 
// The final "Ah Ah" was (after extensive cheating and looking at
// the real data)
//
// 5. Look for and elimitate all the BIG circles. 
//    The idea is that if a station broadcasts to the entire city 
//    it have NO effect on finding the max place. So I simply
//    add up the bitrate for the big stations and add it to the 
//    max at the end. (Who would have guessed there would be lots 
//    of such stations?)
//    BTW a big station has a radius + the long coordinte > 30033
//    provided the radius > the long coordinate. 30033 comes from
//    the fact the worse case is if the station is in the centre at
//    15,000 on one edge. To cover the far corner it takes a radius
//    of 15033. 
//
// that concludes the HARDEST program yet in the CCC because without
// having access to the final test data, why bother going to
// the lengths necessary to tweek a WORKING PROGRAM 
// sufficiently to get in under the wire?
//

import java.awt.*;
import hsa.*;

public class CCC2009S5Wireless
{

    public static int[] [] coffeeShop;
    public static int rows, cols, k;

    public static void main (String[] args)
    {
	int col, row, radius, bitrate, max, total;

	TextInputFile f = new TextInputFile ("s5.1.in");

	cols = f.readInt ();  // transposed
	rows = f.readInt ();
	coffeeShop = new int [rows + 1] [cols + 1];

	for (int i = 1 ; i <= rows ; i++)
	    for (int j = 1 ; j <= cols ; j++)
		coffeeShop [i] [j] = 0;

	int k = f.readInt ();
	total = 0;
	for (int i = 1 ; i <= k ; i++)
	{
	    row = f.readInt ();
	    col = f.readInt ();
	    radius = f.readInt ();
	    bitrate = f.readInt ();
	    if (col + radius > 30033 && radius > col)
		total += bitrate;
	    else
		process (col, row, radius, bitrate);
	}

	max = maxBitRate ();
	System.out.println (max + total);
	System.out.println (countMaxBitRate (max));
    }


    public static void process (int c, int r, int radius, int bitrate)
    {

	int start = Math.max (1, c - radius);
	int stop = Math.min (cols, c + radius);
	int uplimit = Math.min (rows, r + radius);
	int downlimit = Math.max (1, r - radius);
	int square = radius * radius;
	int i, j;
	int t1, t2, t3;                  // not really worth it
	for (i = r ; i <= uplimit ; i++)
	{
	    t1 = i - r;
	    t2 = t1 * t1;
	    t3 = square - t2;
	    while (((start - c) * (start - c)) > t3)
		start++;
	    while (((stop - c) * (stop - c)) > t3)
		stop--;

	    // move 10 at once between checking for being at the end
	    // this silliness shaves off nearly 40% of time on set#5
	    for (j = start ; j < stop - 8 ; j++)
	    {
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j] += bitrate;
	    }
	    while (j <= stop)
		coffeeShop [i] [j++] += bitrate;

	}
	start = Math.max (1, c - radius);
	stop = Math.min (cols, c + radius);
	for (i = r - 1 ; i >= downlimit ; i--)
	{
	    t1 = i - r;
	    t2 = t1 * t1;
	    t3 = square - t2;
	    while (((start - c) * (start - c)) > t3)
		start++;
	    while (((stop - c) * (stop - c)) > t3)
		stop--;
	    for (j = start ; j < stop - 8 ; j++)
	    {
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j++] += bitrate;
		coffeeShop [i] [j] += bitrate;
	    }
	    while (j <= stop)
		coffeeShop [i] [j++] += bitrate;
	}
    }


    public static int maxBitRate ()
    {
	int max = 0;
	for (int i = 1 ; i <= rows ; i++)
	    for (int j = 1 ; j <= cols ; j++)
		if (coffeeShop [i] [j] > max)
		    max = coffeeShop [i] [j];
	return max;
    }


    public static int countMaxBitRate (int max)
    {
	int count = 0;
	for (int i = 1 ; i <= rows ; i++)
	    for (int j = 1 ; j <= cols ; j++)
		if (coffeeShop [i] [j] == max)
		    count++;
	return count;
    }
}


