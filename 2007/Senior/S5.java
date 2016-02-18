// CCC 2007
// S5: Bowling for Numbers
//
// Algorithm by Konstantin Lopyrev
//
// Although I had thought about dynamic programming
// and moving from one ball to the next, or
// of moving from an array of 1 pin, upwards,
// I never thought of combining them both. This
// was Konstantin's great thought: 2D Dynamic Programming :-)
//
// First you need the sums for the pins
// sum[i] = the sum of the next "width" pins, starting at i
//
// With this array, dynamic programming proceeds
// right to left and top to bottom in a 2D array
// of integers representing the max score for a particular
// combo. That is:
// dp[i][j] is the max score using i balls,
//            with pins j thru number of pins.
//
// With zero balls the max score is zero, so the first row
// is all zero. For the rest the algortithm is;
//      it is the maximum of
//          either the previous result one to the right OR
//          the sum + the maximum score with one less ball,
//                              width places to the right.
//
//
// An example might help: (width = 3 and balls = 3)
// pins =  9  2  8  5  6  7  8  3  2  6  7
// sum  = 19 15 19 18 21 18 13 11 15 13  7

// dp[0]=  0  0  0  0  0  0  0  0  0  0  0
// dp[1]= 21 21 21 21 21 18 15 15 15 13  7
// dp[2]= 37 37 37 36 36 33 26 18 15 13  7
// dp[3]=     * 52 44 39 33 26 18 15 13  7

// On this last row, dp[3] the right nine positions are just
// from the super sum because we can knock down ALL the pins.
//
// However how do we get the *?
// Answer: If we knocked that pin down we'd take three pins
//         to the right with it (that's 15 from sum) + we'd
//         have the max score from dp[2] three to the right
//         of where we are (that's 36) for a total of 51. But wait
//         we could have 52 if we don't knock down that pin! So
//         * is 52.
// What about the last spot? (the final result)
// Answer: If we knocked that pin down we'd take three pins
//         to the right with it (that's 19 from sum) + we'd
//         have the max score from dp[2] three to the right
//         of where we are (that's 36) for a total of 55. And
//         that's better than the 52 if we didn't knock it down!
//
// This is very fast: all solutions within a second or two.


import hsa.*;

public class CCC2007S5BowlingForNumbersDP
{
    static Console c;

    public static void main (String[] args)
    {
	int numberPins, numberBalls, width, s;
	int[] pin;
	int[] sum;
	int[] [] dp;

	c = new Console ();
	TextInputFile f = new TextInputFile ("s5.4.in");

	int tests = f.readInt ();
	for (int t = 0 ; t < tests ; t++)
	{

	    // get input
	    numberPins = f.readInt ();
	    numberBalls = f.readInt ();
	    width = f.readInt ();
	    pin = new int [numberPins];
	    for (int i = 0 ; i < numberPins ; i++)
		pin [i] = f.readInt ();


	    // load sum:
	    // sum[i] = sum of "width" pins, starting at i
	    sum = new int [numberPins];
	    s = 0;
	    for (int i = 0 ; i < width ; i++)
		s += pin [i];
	    sum [0] = s;
	    for (int i = 1 ; i < numberPins - width + 1 ; i++)
	    {
		s = s - pin [i - 1] + pin [i + width - 1];
		sum [i] = s;
	    }
	    for (int i = numberPins - width + 1 ; i < numberPins ; i++)
	    {
		s = s - pin [i - 1];
		sum [i] = s;
	    }

	    // intialize dp
	    dp = new int [numberBalls + 1] [numberPins];
	    for (int j = 1 ; j < numberPins ; j++)
		dp [0] [j] = 0;
	    for (int i = 1 ; i <= numberBalls ; i++)
		for (int j = 1 ; j < numberPins ; j++)
		    dp [i] [j] = -1;

	    // fill dp, working right to left and down
	    for (int ball = 1 ; ball <= numberBalls ; ball++)
		for (int i = numberPins - 1 ; i >= 0 ; i--)
		{
		    if (i >= numberPins - width)
			dp [ball] [i] = sum [i];
		    else
			dp [ball] [i] = Math.max (dp [ball - 1] [i + width] + sum [i],
				dp [ball] [i + 1]);
		}
	    c.println (dp [numberBalls] [0]);
	}
    }
}



