// CCC 2007
//
// S4: Waterpark
//
// Using the hint given in the problem, work upwards
// recording the paths as you go. 
// The answer will be in paths[1] at the end. 
// (This is a dynamic programming problem.)
//
// paths is an array of int recording the number of
//       paths from path[i] to path[n]. Initially 
//       all set to zero.
// l is an array of linked lists. l[i] is the 
//       list of all points directly connnected to i.
//
// so the process is to:
//   first set all the paths[i] to 1
//       that lead directly to point n. 
//       (Use l[n] to get those points)
//   process all the rest, going upwards
//       The idea is that if from point 5 there
//       are 3 paths down to n, and point 5 is 
//       connected to point 3, which has already,
//       (without considering point 5), 2 paths to n,
//       then point 3 REALLY has 3+2=5 paths to n.
//       Draw a picture and you'll agree :-) 
//  
// Running time for this is a surprising <1 sec 
// for all test cases.

import hsa.*;

public class CCC2007S4Waterpark
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	int n, x, y, k;
	int[] paths;
	List[] l;
	TextInputFile f = new TextInputFile ("s4.2.in");

	// get n and initialize paths and lists
	n = f.readInt ();
	paths = new int [n + 1];
	l = new List [n + 1];
	for (int i = 1 ; i <= n ; i++)
	{
	    paths [i] = 0;
	    l [i] = new List ();
	}

	// get the connections
	x = f.readInt ();
	y = f.readInt ();
	while (!(x == 0 && y == 0))
	{
	    l [y].add (x);
	    x = f.readInt ();
	    y = f.readInt ();
	}

	// deal with the paths from point n
	while (!l [n].empty ())
	    paths [l [n].remove ()] = 1;

	// process all the other paths, working upwards
	for (int i = n - 1 ; i >= 1 ; i--)
	{
	    while (!l [i].empty ())
	    {
		k = l [i].remove ();
		paths [k] = paths [i] + paths [k];
	    }
	}

	// print answer found in paths[1]
	c.println (paths [1]);
    }
}

// Node class used by the List class
class Node
{
    public int p;
    public Node next;

    public Node (int x)
    {
	p = x;
	next = null;
    }
}

// A simple singly linked, linear list. 
// Nodes are added to front of list only.
class List
{
    Node l;

    public List ()
    {
	l = null;
    }


    public boolean empty ()
    {
	return l == null;
    }


    public void add (int x)
    {
	Node n = new Node (x);
	n.next = l;
	l = n;
    }


    public int remove ()
    {
	int x = l.p;
	l = l.next;
	return x;
    }
}
