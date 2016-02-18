// CCC2006s5OriginofLife
//
// This is the "super tough" problem of the year. :-)
// (It's taken me 4 days to come up with this one!)
//
// My approach is based on ideas from Jason Jackson and Rich Hong.
//
// Contrary to common sense, instead of starting with what is given,
// and working back, I take EVERY possible configuration of life
// and find out what the next generation is. I store ALL these results
// and then more or less just get the answer directly.
//
// NO recursion involved and it only takes a few seconds for any of the cases.
//
// In more detail: every configuration of the life 2D array
// can be represented as a number between 0 and 2^(m*n). Thanks Jason and Rich :-)
//
// Step1: let s go thru every number 0 to 2^(m*n)
//    convert s to life1 (a 2D array)
//    generate the next genaration, life2, and convert it back to a number (t)
//    in an array x (of queues) add s to x at position t
//
// for example suppose 0 leads to 3, 1 -> 4, 2 -> 4, 3 -> 2, 4 -> 3, 5 -> 0
// then x looks like:
//     values:  5  | - | 3 | 0,4 | 1,2 | - | ...
//      index:  0  | 1 | 2 |  3  |  4  | 5 | ...
//
// so if the inital generation was 3 how many steps back are there?
//
// using the x array:
// 3 comes from 0 or 4 and these in turn come from 5 or 1 or 2 and
// 5 comes from... oops it comes from nothing: thus its a "Garden of Eden"
// and that was a total of 2 steps. QED.
//
// how do you get a -1 for an answer? I cheat: I don't bother to look for loops
// but if there was one the above process would not end and so I just
// quit after a number of steps: give up and call it a day.
//

import java.awt.*;
import hsa.*;

public class CCC2006s5OriginofLife
{
    static Console console;
    static int m, n, a, b, c, size;
    static Queue[] x;
    static byte[] [] life1, life2;

    public static void main (String[] args)
    {
	console = new Console ();
	TextInputFile f = new TextInputFile ("s5.1.in");
	String s;
	int original;

	// input values
	m = f.readInt ();
	n = f.readInt ();
	a = f.readInt ();
	b = f.readInt ();
	c = f.readInt ();
	life1 = new byte [m + 2] [n + 2];
	life2 = new byte [m + 2] [n + 2];
	size = (int) Math.pow (2, m * n);
	x = new Queue [size];
	for (int i = 0 ; i < size ; i++)
	    x [i] = new Queue ();

	// put a border around so bounds checking is eliminated
	for (int i = 0 ; i < m + 2 ; i++)
	    for (int j = 0 ; j < n + 2 ; j++)
	    {
		life1 [i] [j] = 0;
		life2 [i] [j] = 0;
	    }
	for (int i = 1 ; i <= m ; i++)
	{
	    s = f.readLine ();
	    for (int j = 1 ; j <= n ; j++)
		if (s.charAt (j - 1) == '.')
		    life2 [i] [j] = 0;
		else
		    life2 [i] [j] = 1;
	}
	original = toInt ();
	fillx ();
	console.println ("" + breadthOrderSearchofX (original));
    }


    // for all possible life combos,
    // generate the next gen. Store results in the array of queues, x
    public static void fillx ()
    {
	for (int i = 0 ; i < size ; i++)
	{
	    to2D (i);
	    x [nextGen ()].add (i);
	}

    }


    // generates life2 from life1
    // it returns the "number" for life2.
    public static int nextGen ()
    {
	boolean ok = true;
	int up, down, left, right, sum, value;
	for (int i = 1 ; i <= m ; i++)
	    for (int j = 1 ; j <= n ; j++)
	    {
		up = i - 1;
		down = i + 1;
		left = j - 1;
		right = j + 1;
		sum = life1 [up] [left] + life1 [up] [j] + life1 [up] [right] +
		    life1 [i] [left] + life1 [i] [right] +
		    life1 [down] [left] + life1 [down] [j] + life1 [down] [right];
		if (life1 [i] [j] == 1)
		    if (sum < a || sum > b)
			life2 [i] [j] = 0;
		    else
			life2 [i] [j] = 1;
		else
		    if (sum > c)
			life2 [i] [j] = 1;
		    else
			life2 [i] [j] = 0;
	    }
	return toInt ();
    }


    // convert life2 (2D) into an integer
    public static int toInt ()
    {
	int out = 0;
	int power = 1;
	for (int i = 1 ; i <= m ; i++)
	    for (int j = 1 ; j <= n ; j++)
	    {
		out += life2 [i] [j] * power;
		power *= 2;
	    }
	return out;
    }


    // convert an integer into life1 (2D)
    public static void to2D (int in)
    {
	for (int i = 1 ; i <= m ; i++)
	    for (int j = 1 ; j <= n ; j++)
	    {
		life1 [i] [j] = (byte) (in % 2);
		in = in / 2;
	    }
    }


    // this does a breadthOrderSearch thru x, starting at start
    // stopping when any value has a emtpy x value
    // (counting the steps as we go. the number os steps is the result.)
    // after 50 steps, there must be a loop, quit and return -1.
    public static int breadthOrderSearchofX (int start)
    {
	Queue q1 = new Queue ();
	Queue q2 = new Queue ();
	boolean keepgoing = true;
	int count = 0;
	int k;
	int h;
	q1.add (start);
	while (keepgoing && count < 50)
	{
	    while (!q1.empty () && keepgoing)
	    {
		h = q1.remove ();
		if (x [h].empty ())
		    keepgoing = false;
		else
		{
		    x [h].reset ();
		    while (!x [h].emptied ())
		    {
			k = x [h].getNext ();
			q2.add (k);
		    }
		}
	    }
	    if (keepgoing)
	    {
		q1 = q2;
		q2 = new Queue ();
		count++;
	    }
	}
	if (count < 50)
	    return count;
	else
	    return -1;
    }
}

class Node
{
    public int data;
    Node next;

    public Node (int x)
    {
	data = x;
	next = null;
    }
}

class Queue
{
    Node front, back, ptr;

    public Queue ()
    {
	front = null;
	ptr = null;
	back = null;
    }


    public boolean empty ()
    {
	return front == null;
    }


    public boolean emptied ()
    {
	return ptr == null;
    }



    public void add (int x)
    {
	Node nn = new Node (x);
	if (empty ())
	{
	    front = nn;
	    ptr = nn;
	}
	else
	    back.next = nn;
	back = nn;
    }


    public int remove ()
    {
	if (empty ())
	    return -1;
	else
	{
	    int hold = front.data;
	    front = front.next;
	    ptr = front;
	    return hold;
	}
    }


    public int getNext ()
    {
	if (emptied ())
	    return -1;
	else
	{
	    int hold = ptr.data;
	    ptr = ptr.next;
	    return hold;
	}
    }


    public void reset ()
    {
	ptr = front;
    }
}


