// CCC 1999
// Problem 4: A Knightly Pursuit

// This is a Dynamic programming approach. Given the starting location
// of the knight each square will be given a number indicating the minimum
// moves needed to get there. (if a square can be reached in k moves
// it can also be reached in k + 2x moves, x = 1,2,3...)

// -1 indicates the knight can't get there.

// file input and output.
// input:  number of games, r,c,pr,pc,kr,kr (size of board, pawn/knight pos)

import java.awt.*;
import hsa.*;

public class P4KnightDP
{
    static Console cc;
    static int maxr, maxc;
    static int m, ps, nps;
    static int [] [] b; // board holds min number of moves to get here
    static Point [] p, np; // list of squares to use for next pass (& new list)

    public static void main (String [] args)
    {
	cc = new Console ();
	TextInputFile fi = new TextInputFile ("knight.in");
	TextOutputFile fo = new TextOutputFile ("knight.out");
	int n, hpr, pc, kr, kc;
	boolean win, stalemate;

	n = fi.readInt ();
	for (int i = 1 ; i <= n ; i++)
	{
	    maxr = fi.readInt ();
	    maxc = fi.readInt ();
	    hpr = fi.readInt () - 1;
	    pc = fi.readInt () - 1;
	    kr = fi.readInt () - 1;
	    kc = fi.readInt () - 1;

	    // initialize board and p array
	    b = new int [maxr] [maxc];
	    for (int r = 0 ; r < maxr ; r++)
		for (int c = 0 ; c < maxc ; c++)
		    b [r] [c] = -1;
	    b [kr] [kc] = 0;

	    p = new Point [maxr * maxc];
	    np = new Point [maxr * maxc];
	    for (int r = 0 ; r < maxr * maxc ; r++)
	    {
		p [r] = new Point ();
		np [r] = new Point ();
	    }
	    ps = 1;
	    p [0].r = kr;
	    p [0].c = kc;

	    // fill board (while p has points to consider.)
	    while (ps > 0)
	    {
		nps = 0;
		for (int j = 0 ; j < ps ; j++)
		{
		    newPoint (p [j].r + 1, p [j].c + 2, p [j].r, p [j].c);
		    newPoint (p [j].r - 1, p [j].c + 2, p [j].r, p [j].c);
		    newPoint (p [j].r - 2, p [j].c + 1, p [j].r, p [j].c);
		    newPoint (p [j].r - 2, p [j].c - 1, p [j].r, p [j].c);
		    newPoint (p [j].r - 1, p [j].c - 2, p [j].r, p [j].c);
		    newPoint (p [j].r + 1, p [j].c - 2, p [j].r, p [j].c);
		    newPoint (p [j].r + 2, p [j].c - 1, p [j].r, p [j].c);
		    newPoint (p [j].r + 2, p [j].c + 1, p [j].r, p [j].c);
		}
		for (int j = 0 ; j < nps ; j++)
		{
		    p [j].r = np [j].r;
		    p [j].c = np [j].c;
		}
		ps = nps;
	    }

	    // check for win
	    // win if knight can get to the same spot as pawn
	    // eg. a win is if the board shows 3 and the pawn
	    //     gets there in 3, 5 or 7 moves.
	    // m is the move number. pawn moves first
	    m = 1;
	    win = false;
	    // go < maxr-1 because if p gets to maxr-1 knight loses
	    for (int pr = hpr + 1 ; pr < maxr - 1 && !win ; pr++)
	    {
		if (m >= b [pr] [pc] && b [pr] [pc] >= 0 && (m - b [pr] [pc]) % 2 == 0)
		{
		    win = true;
		    fo.println ("Win in " + m + " knight moves(s).");
		    cc.println ("Win in " + m + " knight moves(s).");
		}
		m++;
	    }

	    if (!win)
	    {
		// check for stalemate
		// this is where the knight can get to the square 
		// above the pawn. 
		// eg. board shows 3 above and pawn gets to the
		// square below in 3, 5 or 7 moves
		// if knight starts above pawn, its stalemate
		// in 0 moves, hence m starts at 0.
		m = 0;
		stalemate = false;
		for (int pr = hpr ; pr < maxr - 1 && !stalemate ; pr++)
		{
		    if (m >= b [pr + 1] [pc] && b [pr + 1] [pc] >= 0 && (m - b [pr + 1] [pc]) % 2 == 0)
		    {
			stalemate = true;
			fo.println ("Stalemate in " + m + " knight moves(s).");
			cc.println ("Stalemate in " + m + " knight moves(s).");
		    }
		    m++;
		}
		if (!stalemate)
		{
		    fo.println ("Loss in " + (maxr - hpr - 2) + " knight moves(s).");
		    cc.println ("Loss in " + (maxr - hpr - 2) + " knight moves(s).");
		}
	    }
	}
	fo.close ();
	fi.close ();
    }


    public static void newPoint (int r, int c, int or, int oc)
    {
	if (r >= 0 && r < maxr && c >= 0 && c < maxc && b [r] [c] == -1)
	{
	    b [r] [c] = b [or] [oc] + 1;
	    np [nps].r = r;
	    np [nps].c = c;
	    nps++;
	}
    }
}

class Point
{
    public int r, c;
}


