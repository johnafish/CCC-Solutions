// CCC 2008
//
// J5: Nukit
//
// This is a Recursive approach
//
// in English:
// you win if there is at least one way to create a losing combo.
// you lose if there is no move, OR
//             all possible moves lead to a winning combo
//
// this algorithm is a straight forward implementation of that.
//
// By the way, this is ALSO the solution to S5, data set 1 and 2.
// It fails on data 3 and 4 due to time concerns as the recursion
// is not very efficient.
// However it is easy and a simple Junior solution.
//

import java.awt.*;
import hsa.*;

public class CCC2008J5NukitRecursion
{

    public static boolean canDoAABDD (int a, int b, int c, int d)
    {
	return a >= 2 && b >= 1 && d >= 2;
    }


    public static boolean canDoABCD (int a, int b, int c, int d)
    {
	return a >= 1 && b >= 1 && c >= 1 && d >= 1;
    }


    public static boolean canDoCCD (int a, int b, int c, int d)
    {
	return c >= 2 && d >= 1;
    }


    public static boolean canDoBBB (int a, int b, int c, int d)
    {
	return b >= 3;
    }


    public static boolean canDoAD (int a, int b, int c, int d)
    {
	return a >= 1 && d >= 1;
    }


    public static boolean canMove (int a, int b, int c, int d)
    {
	return canDoAABDD (a, b, c, d) || canDoABCD (a, b, c, d) ||
	    canDoCCD (a, b, c, d) || canDoBBB (a, b, c, d) || canDoAD (a, b, c, d);
    }


    // lose if you can't move or if all possible moves you can do lead to a winning combo
    public static boolean losingCombo (int a, int b, int c, int d)
    {
	if (!canMove (a, b, c, d))
	    return true;
	else
	{
	    boolean temp = true;
	    if (canDoAABDD (a, b, c, d))
		temp = temp && winningCombo (a - 2, b - 1, c, d - 2);
	    if (canDoABCD (a, b, c, d))
		temp = temp && winningCombo (a - 1, b - 1, c - 1, d - 1);
	    if (canDoCCD (a, b, c, d))
		temp = temp && winningCombo (a, b, c - 2, d - 1);
	    if (canDoBBB (a, b, c, d))
		temp = temp && winningCombo (a, b - 3, c, d);
	    if (canDoAD (a, b, c, d))
		temp = temp && winningCombo (a - 1, b, c, d - 1);
	    return temp;
	}
    }


    // win if at least one rule creates a losing combo
    public static boolean winningCombo (int a, int b, int c, int d)
    {
	if (canDoAABDD (a, b, c, d) && losingCombo (a - 2, b - 1, c, d - 2))
	    return true;
	else if (canDoABCD (a, b, c, d) && losingCombo (a - 1, b - 1, c - 1, d - 1))
	    return true;
	else if (canDoCCD (a, b, c, d) && losingCombo (a, b, c - 2, d - 1))
	    return true;
	else if (canDoBBB (a, b, c, d) && losingCombo (a, b - 3, c, d))
	    return true;
	else if (canDoAD (a, b, c, d) && losingCombo (a - 1, b, c, d - 1))
	    return true;
	else
	    return false;

    }


    public static void main (String[] args)
    {
	Console cc = new Console ();
	int a, b, c, d, n;
	TextInputFile f = new TextInputFile ("j5.1.in");
	n = f.readInt ();
	for (int i = 0 ; i < n ; i++)
	{
	    a = f.readInt ();
	    b = f.readInt ();
	    c = f.readInt ();
	    d = f.readInt ();
	    if (winningCombo (a, b, c, d))
		cc.println ("Patrick");
	    else
		cc.println ("Roland");

	}
    }
}

