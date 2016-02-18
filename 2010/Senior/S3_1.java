// S3 2010
//
// This algorithm is by Vincent Siao
// of St George's School
//
// this uses a linked list of houses and tries hoses of length 1, 2, 3 ...
// until the correct length is found.
// processing begins after largest gap between houses.
//

import java.util.*;
import java.io.*;
import hsa.*;

public class s32010VS
{
    public static int h, k;
    public static int[] house;


    public static void main (String[] args)
    {
	TextInputFile s = new TextInputFile ("s3.8.in");


	h = s.readInt ();

	house = new int [h];
	for (int i = 0 ; i < h ; i++)
	    house [i] = s.readInt ();

	k = s.readInt ();
	s.close ();

	//sort addresses
	sort ();

	// insert into linked list
	Gap e = new Gap ();
	Gap start = e;

	// marks first gap for processing after the longest gap
	// to eliminate circular wrap issues
	Gap firstGap = e;
	int max = 0;
	for (int i = 1 ; i < h ; i++)
	{
	    e.length = house [i] - house [i - 1];
	    e.next = new Gap ();
	    if (e.length > max)
	    {
		firstGap = e.next;
		max = e.length;
	    }
	    e = e.next;
	}
	e.length = 1000000 + house [0] - house [h - 1];

	// makes the list circular
	e.next = start;

	// sets the first gap to be AFTER the longest gap.
	// (This gap will never be covered.)
	if (e.length > max)
	    firstGap = e.next;

	// The hose length loops from 0 to the maximum length.
	// For each loop, hydrants are 'placed' one at a time,
	// distance covered can be as much as twice the hose length.
	// If the pointer e is back to the start of the list,
	// after k hydrants, all the houses are covered.
	// (If the hose was too short, e would not have made it back
	// to the start.)

	int hoseLength = -1;
	boolean found = false;
	do
	{
	    hoseLength++;
	    int distance;
	    e = firstGap;
	    for (int j = 0 ; j < k && !found ; j++)
	    {
		distance = e.length;
		e = e.next;
		while (distance <= 2 * hoseLength && e != firstGap)
		{
		    distance += e.length;
		    e = e.next;
		}
		found = e == firstGap;
	    }
	}
	while (!found);
	System.out.println (hoseLength);
    }


    public static void sort ()
    {
	int hold, j;
	for (int i = 1 ; i < h ; i++)
	{
	    hold = house [i];
	    j = i - 1;
	    while (j >= 0 && hold < house [j])
	    {
		house [j + 1] = house [j];
		j--;
	    }
	    house [j + 1] = hold;
	}
    }
}

// A Node of a circular linked list
class Gap
{
    int length;
    Gap next;

    public Gap (int y, Gap x)
    {
	length = y;
	next = x;
    }


    public Gap ()
    {
	length = 0;
	next = null;
    }
}
