// CCC 2003
// Problem S3J5 Floor Plan
//
// given a grid with walls and an amount of flooring,
// flooring the larger rooms first, determine the flooring reamining
// and the number of rooms floored.
//
// 1. read the floor plan (rooms are 0, walls are -1)
// 2. use recusion to determine which squares belong to a single room
//    (number the first room 1, second 2, etc.)
// 3. determine the size of each room (add up all the 1's, 2's etc.)
// 4. find largest room, and floor it, set its value to -1, repeat!

// file input: amount of flooring, row, col and grid given
// output the # rooms floored and the remianing flooring

import java.awt.*;
import hsa.*;

public class S3J5Floor
{
    static Console cc;
    static int [] [] house;
    static int r, c;

    public static void main (String [] args)
    {
	cc = new Console ();

	String line;
	int n, k;
	int [] room;
	int count, largest;
	boolean done;

	TextInputFile fi = new TextInputFile ("floor5.in");
	TextOutputFile fo = new TextOutputFile ("floor5.out");

	// read grid
	// put -1 for walls, 0 for room
	n = fi.readInt ();
	r = fi.readInt ();
	c = fi.readInt ();
	house = new int [r] [c];
	for (int i = 0 ; i < r ; i++)
	{
	    line = fi.readLine ();
	    for (int j = 0 ; j < c ; j++)
		if (line.charAt (j) == 'I')
		    house [i] [j] = -1;
		else
		    house [i] [j] = 0;
	}

	//number the rooms
	k = 1;
	for (int i = 0 ; i < r ; i++)
	    for (int j = 0 ; j < c ; j++)
		if (house [i] [j] == 0)
		{
		    check (i, j, k);
		    k++;
		}

	// determine area of rooms
	room = new int [500];
	for (int i = 0 ; i < r ; i++)
	    for (int j = 0 ; j < c ; j++)
		if (house [i] [j] > 0)
		    room [house [i] [j]]++;

	// get next largest room and floor it
	count = 0;
	done = false;
	while (!done && n > 0)
	{
	    largest = 0;
	    for (int i = 0 ; i < 500 ; i++)
		if (room [i] > room [largest])
		    largest = i;
	    if (room [largest] > 0)
	    {
		if (room [largest] <= n)
		{
		    n = n - room [largest];
		    room [largest] = -1;
		    count++;
		}
		else
		    done = true;
	    }
	    else
		done = true;
	}

	fo.println (count + " rooms, " + n + " square metre(s) left over");

	fi.close ();
	fo.close ();
    }


    // sets the house grid are current location to k, and recursively
    // sets all connected square to k as well.
    public static void check (int i, int j, int k)
    {
	if (i >= 0 && i < r && j >= 0 && j < c && house [i] [j] == 0)
	{
	    house [i] [j] = k;
	    check (i - 1, j, k);
	    check (i + 1, j, k);
	    check (i, j + 1, k);
	    check (i, j - 1, k);
	}
    }
}


