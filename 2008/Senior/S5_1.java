// CCC 2008
//
// S5: Nukit
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
// The change here, from the Junior, is to speed up the process by
// remembering combos and not try them a second time.
// That's it. Nothing more complex. 
//

import java.awt.*;
import hsa.*;

public class CCC2008S5NukitRecursion
{

    static char[] used;


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
	int x = a * 27000 + b * 900 + c * 30 + d;
	if (used [x] != '?')
	    return used [x] == 'l';
	if (!canMove (a, b, c, d))
	{
	    used [x] = 'l';
	    return true;
	}
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
	    if (temp)
	    {
		used [x] = 'l';
		return true;
	    }
	    else
	    {
		used [x] = 'w';
		return false;
	    }
	}
    }


    // win if at least one rule creates a losing combo
    public static boolean winningCombo (int a, int b, int c, int d)
    {
	int x = a * 27000 + b * 900 + c * 30 + d;
	if (used [x] != '?')
	    return used [x] == 'w';
	if (canDoAABDD (a, b, c, d) && losingCombo (a - 2, b - 1, c, d - 2))
	{
	    used [x] = 'w';
	    return true;
	}
	else if (canDoABCD (a, b, c, d) && losingCombo (a - 1, b - 1, c - 1, d - 1))
	{
	    used [x] = 'w';
	    return true;
	}
	else if (canDoCCD (a, b, c, d) && losingCombo (a, b, c - 2, d - 1))
	{
	    used [x] = 'w';
	    return true;
	}
	else if (canDoBBB (a, b, c, d) && losingCombo (a, b - 3, c, d))
	{
	    used [x] = 'w';
	    return true;
	}
	else if (canDoAD (a, b, c, d) && losingCombo (a - 1, b, c, d - 1))
	{
	    used [x] = 'w';
	    return true;
	}
	else
	{
	    used [x] = 'l';
	    return false;
	}

    }


    public static void main (String[] args)
    {
	Console cc = new Console ();

	int a, b, c, d, n;
	used = new char [1000000];
	TextInputFile f = new TextInputFile ("s5.4.in");
	n = f.readInt ();


	for (int i = 0 ; i < n ; i++)
	{
	    for (int j = 0 ; j < 1000000 ; j++)
		used [j] = '?';
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

