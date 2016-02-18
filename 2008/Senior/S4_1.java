// CCC 2008
//
// S4: TwentyFour
//
// This is PURE BRUTE FORCE. I'm sure someone will quickly
// show me a better algorithm.
//
// the idea here is:
//    Generate all possible answers.
//    look for biggest <= 24. done. :-)
//
// to generate all answers I do all possible operations
// for example if the hand is: 2 6 8 10
// then I go thru ALL permutations of two numbers and do
// the possible operations, creating new hands of 3 numbers.
//
// In the above example I'd get:
//  8 8 10   (added 2+6)
//  -4 8 10  (subtracted 2-6)
//  12 8 10  (multiplied 2*6)  (couldn't divide 2/60
//  10 6 10  (added 2+8)
//  -6 6 10  (subtracted 2-8)
//  ...
//
// in this way a hand of 4 become many hands of 3
// then those hands of 3 become hands of 2 and then 1.
// finally it looks for the biggest number <= 24.
//
// I calculated a max possible number of hands as:
//   1 hand of 4    (given)
//  48 hands of 3   ( = 12 perumtations * 4 operations)
// 1152 hands of 2  ( = 48 hands * 6 permutations * 4 operations)
// 9216 hands of 1  ( = 1152 * 2 numbers * 4 operations)
//
// It ain't pretty, but it works! :-)
//


import java.awt.*;
import hsa.*;

public class CCC2008S4TwentyFour
{
    static Console c;


    // this takes a group of handSize numbers and combines 2 of the numbers
    // using all operations in all possible ways, creating a new series
    // of hands, with one fewer numbers in each.
    static int doOperations (int[] hand, int handSize, int[] [] newHand, int k)
    {
	for (int i = 0 ; i < handSize ; i++)
	    for (int j = 0 ; j < handSize ; j++)
		if (i != j)
		{
		    // do +
		    newHand [k] [0] = hand [i] + hand [j];
		    // fill the hand with the other numbers
		    int q = 1;
		    for (int p = 0 ; p < handSize ; p++)
			if (!(p == i || p == j))
			    newHand [k] [q++] = hand [p];
		    k++;

		    // do -
		    newHand [k] [0] = hand [i] - hand [j];
		    // fill the hand with the other numbers
		    q = 1;
		    for (int p = 0 ; p < handSize ; p++)
			if (!(p == i || p == j))
			    newHand [k] [q++] = hand [p];
		    k++;

		    // do *
		    newHand [k] [0] = hand [i] * hand [j];
		    // fill the hand with the other numbers
		    q = 1;
		    for (int p = 0 ; p < handSize ; p++)
			if (!(p == i || p == j))
			    newHand [k] [q++] = hand [p];
		    k++;

		    // do / if you can
		    if (hand [j] != 0 && hand [i] % hand [j] == 0)
		    {
			newHand [k] [0] = hand [i] / hand [j];
			// fill the hand with the other numbers
			q = 1;
			for (int p = 0 ; p < handSize ; p++)
			    if (!(p == i || p == j))
				newHand [k] [q++] = hand [p];
			k++;
		    }
		}
	return k;
    }


    public static void main (String[] args)
    {
	int n, size, newSize;
	int[] [] hand, newHand;
	c = new Console ();
	TextInputFile f = new TextInputFile ("s4.5.in");
	n = f.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    newHand = new int [1] [4];
	    newHand [0] [0] = f.readInt ();
	    newHand [0] [1] = f.readInt ();
	    newHand [0] [2] = f.readInt ();
	    newHand [0] [3] = f.readInt ();
	    newSize = 1;

	    // generate all the hands, size 4 to 3, 3 to 2 and 2 to 1.
	    for (int k = 4 ; k > 1 ; k--)
	    {
		size = newSize;
		hand = newHand;
		newHand = new int [10000] [3];
		newSize = 0;
		for (int j = 0 ; j < size ; j++)
		    newSize = doOperations (hand [j], k, newHand, newSize);
	    }

	    // find the biggest <= 24 and print it.
	    size = newSize;
	    int biggest = 0;
	    for (int j = 0 ; j < size && biggest < 24 ; j++)
		if (newHand [j] [0] > biggest && newHand [j] [0] <= 24)
		    biggest = newHand [j] [0];
	    c.println (biggest);
	}

    }
}



