// The "CCC2005s5PinballRankingMerge" class.
//
// this solution is due to Sean Henderson of University of Toronto
//
// the insight is that rank for score x is essentially
// the number of inversions from 0 to x (plus 1). An inversion
// is when a[x] > a[y] when x < y.
// so if the total inversions for the entire array is I
// the average rankings is (I + N) / N
//
// The number of inversions of the array can be determined
// by using merge sort. The idea is if you are merging
// a[x,y) with a[y,z) then the number of inversions is the total
// of the number of elements in a[x,y) each time one is taken
// from a[y,z).
//
// For example suppose you were sorting:
//  5 8 2 5 7 1
//
// after 1 pass it would be
//  5 8 2 5 1 7 with a total = 1 because there was 1 case
//              where the 7 and 1 were out of order
//
// on the second pass 5 8 and 2 5 are merged together
// in this case the total = 2 (5 8 versus 2) plus 1
// (for the 8 versus 5)
// total inversions so far is 4, and the array looks like
//  2 5 5 8 1 7
//
// on the last pass the total = 4 (2558 vs 1) plus 1
// (for the 8 vs 7)
// giving a grand total inversions of 9.
//
// the average rankings is then (9+6)/6 = 2.5
//
// this is correct because the rankings found normally are:
// (1 + 1 + 3 + 2 + 2 + 6) / 6 = 2.5

import java.awt.*;
import hsa.*;

public class CCC2005s5PinballRankingMerge
{
    static Console c;
    static double I;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s5.9.in");
	I = 0;
	int[] a = new int [100001];

	int n;
	n = f.readInt ();
	for (int x = 0 ; x < n ; x++)
	    a [x] = f.readInt ();
	mergeSort (a, 0, n - 1);
	c.println ((I + n) / n, 0, 2);
    }


    // this is an inclusive a[x,z] merge sort
    public static void mergeSort (int[] a, int x, int z)
    {
	if (x < z)
	{
	    int y = (x + z) / 2;
	    mergeSort (a, x, y);
	    mergeSort (a, y + 1, z);
	    I += merge (a, x, y, z);
	}
    }


    public static double merge (int[] a, int x, int y, int z)
    {
	int[] newa = new int [z - x + 1];
	int xx = x;
	int yy = y + 1;
	int k = 0;
	double total = 0;
	while (xx <= y && yy <= z)
	    if (a [xx] <= a [yy])
		newa [k++] = a [xx++];
	    else
	    {
		newa [k++] = a [yy++];
		total = total + (y + 1 - xx);
	    }
	while (xx <= y)
	    newa [k++] = a [xx++];
	while (yy <= z)
	    newa [k++] = a [yy++];
	for (xx = x ; xx <= z ; xx++)
	    a [xx] = newa [xx - x];
	return total;
    }
}
