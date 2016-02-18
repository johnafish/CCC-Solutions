// CCC 2007
//
// S2: Boxes
//
// given  a set of boxes (l,w,h)
// find the smallest a given box will fit into
// (smallest in terms of volume)
//
// This is a double sorting exercise.
// The l,w,h for everything must be sorted.
// Also the set of boxes needs to be sorted by volume.
//
// After that its just a loop to find the smallest (first)
// into which a given box will fit.
//
// An extra box class is used to store and
// sort the l, w, h, and volume for each box

import java.awt.*;
import hsa.*;

public class CCC2007S2Boxes
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s2.1.in");
	int l, w, h, n, j, m;
	Box[] b;
	Box hold;

	// get the boxes
	n = f.readInt ();
	b = new Box [n];
	for (int i = 0 ; i < n ; i++)
	{
	    l = f.readInt ();
	    w = f.readInt ();
	    h = f.readInt ();
	    b [i] = new Box (l, w, h);
	}

	// sort the boxes by volume (insertion sort)
	for (int i = 1 ; i < n ; i++)
	{
	    j = i - 1;
	    hold = b [i];
	    while (j >= 0 && b [j].volume > hold.volume)
	    {
		b [j + 1] = b [j];
		j--;
	    }
	    b [j + 1] = hold;
	}

	// get the boxes to test, and find the first (ie smallest)
	// in set that holds it.
	m = f.readInt ();
	for (int i = 0 ; i < m ; i++)
	{
	    l = f.readInt ();
	    w = f.readInt ();
	    h = f.readInt ();
	    hold = new Box (l, w, h);
	    j = 0;
	    while (j < n && (hold.l > b [j].l ||
			hold.w > b [j].w ||
			hold.h > b [j].h))
		j++;
	    if (j < n)
		c.println (b [j].volume);
	    else
		c.println ("Item does not fit.");
	}
    }
}

class Box
{
    public int l, w, h;
    public long volume;

    public Box (int a, int b, int c)
    {
	int t;
	l = a;
	w = b;
	h = c;
	if (l > w)
	{
	    t = l;
	    l = w;
	    w = t;
	}
	if (w > h)
	{
	    t = w;
	    w = h;
	    h = t;
	}
	if (l > w)
	{
	    t = l;
	    l = w;
	    w = t;
	}
	volume = l * w * h;
    }
}

