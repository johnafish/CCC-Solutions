// CCC 2008
//
// S5: Nukit
//
// This is Dynamic Programming approach
//
// Four people, independently, sent me this same algorithm
// Konstantin Lopyrev, Amlesh Jayakumar, Matthew Lai and one other. 
// This is Konstantin's version.
//
// The idea is to have a 4D array representing all
// possible positions, and determine if they are winning
// positions or not. This is created first. Then when  
// a starting position is read, you can just look up the
// answer. 
//
// The array is called winningPosition and is filled with 
// false to begin with. 
// SO, (and this is key.) 
//       We assume all positions are loosing ones.
// (that's the first set of loops in main)
//
// All that remains to to find the winning ones. 
// and that's easy:
//       a winning Position is one which has a move
//                        which leads to a loosingPosition.
// (that's the second set of loops in the main)
//
// That's it, except for the method loosingPosition.
// This method was tough to wrap my head around.
// "A loosing position is one which is NOT a winning one." 
// (and by the way, if the given position is an impossible position
// its not a loosing position.) 
//
// The problem I have with this is: I'm trying to determine the value
// of winningPosition, which calls the loosingPosition method
// and it uses winningPosition to determine that...
// That's circular reasoning!!! But its not.   
// As in all DP methods, we work from the ground up so to speak. 
// 
// Look at it another way. The lowest positions like 0,0,0,0 are 
// loosingPositions, Because if you apply any rule to it, it gives
// negatives and the loosingPosition method for that will return false and 
// the value of winningPosition[0][0][0][0] retains unchanged, at false.
// So the "can't move" positions are essentially taken care of with
// the if in loosingPosition() and the initial assumption
// that all positions are loosing ones. 
//
// Later on as you move up to a bigger position, that has moves to be made,
// if you are looking at some move a,b,c,d then the call to loosingPosition
// will involve SMALLER values, (a lower position), that is, one that
// we already figured out, for sure, is a winning or loosing position,
// so we CAN reliably return a value that we can use to establish what
// the original a,b,c,d is. QED.
//
// if that still doesn't make sense, sorry.  I tried :-)
// (This DOES work!)
//

import java.awt.*;
import hsa.*;

public class CCC2008S5NukitDP
{
    static Console cc;

    static boolean[] [] [] [] winningPosition;
    static int[] [] moves = {{2, 1, 0, 2}, {1, 1, 1, 1}, {0, 0, 2, 1}, {0, 3, 0, 0}, {1, 0, 0, 1}};


    // its a loosing position if its NOT a winning position :-)
    // The "if" is there to handle the "can't move" situations, the false it
    // returns is not to be taken as "this is a winning position",
    // but as "the position that lead to this, IS a loosing position."
    static boolean loosingPosition (int a, int b, int c, int d)
    {
	if (a < 0 || b < 0 || c < 0 || d < 0)
	    return false;
	else
	    return !winningPosition [a] [b] [c] [d];
    }


    public static void main (String[] args)
    {
	int n, a, b, c, d;
	cc = new Console ();
	TextInputFile f = new TextInputFile ("s5.4.in");

	winningPosition = new boolean [31] [31] [31] [31];
	
	// Step 1: Assume all positions are loosing
	for (int i = 0 ; i < 31 ; i++)
	    for (int j = 0 ; j < 31 ; j++)
		for (int k = 0 ; k < 31 ; k++)
		    for (int l = 0 ; l < 31 ; l++)
			winningPosition [i] [j] [k] [l] = false;

	// Identify all winning positions. A winning position is
	// one which has at least one move which leads to a
	// loosing position.
	for (int i = 0 ; i < 31 ; i++)
	    for (int j = 0 ; j < 31 ; j++)
		for (int k = 0 ; k < 31 ; k++)
		    for (int l = 0 ; l < 31 ; l++)
			for (int m = 0 ; m < 5 ; m++)
			    if (loosingPosition (i - moves [m] [0], j - moves [m] [1], k - moves [m] [2], l - moves [m] [3]))
				winningPosition [i] [j] [k] [l] = true;

	n = f.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    a = f.readInt ();
	    b = f.readInt ();
	    c = f.readInt ();
	    d = f.readInt ();
	    if (winningPosition [a] [b] [c] [d])
		cc.println ("Patrick");
	    else
		cc.println ("Roland");
	}
    }
}



