// CCC 2000
// Problem S4 Golf
//
// This is a Breadth First Search algorithm
// by Aaron Voelker, of Bell High School (Ottawa)
//
// For example if there were clubs 2,4,5 and the total distance was 12
// then it would take 3 clubs (2, 5 and 5)
//
// the tree would be:
//                       0
//             2         4          5
//           6  7      8  9        10
//          11   12 
//            STOP at the first 12 (the answer and note it's on level 3!)            
//
//  It's hard to show without lines, but the idea is that from the root
//  0, you can go to 2, 4, or 5. 
//  from the 2 you can go to 4,6,7 (the 4 isn't in the tree as it's a dup)
//  from the 4 you can go to 6,8,9 (the 6 isn't in the tree as it's a dup)
//  ...
//  etc. 
//
//  when the distance shows up, you find the level and return it.
//  if you can't make any more nodes without dups or going over stop
//  and return -1.
//
//  Aaron's links within the tree are very cool. Instead of the standard
//  links to children, each child has a link back to the parent and each 
//  node is linked in breadth order via a next link. 
//
//  though not part of the problem, it would be simple to add a bit of code 
//  to return the clubs used as well as the number of clubs.
//
// file consists of distance, number of clubs and then the clubs.
// output is a statement about number of strokes, or "defeat"

import java.awt.*;
import hsa.*;


public class S4GolfBFS
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	int club[] = new int [32];
	int dis, n, ans;
	BreadthFirstTree tree = new BreadthFirstTree ();

	TextInputFile fi = new TextInputFile ("golf.in3");
	TextOutputFile fo = new TextOutputFile ("golf.ou3");

	dis = fi.readInt ();
	n = fi.readInt ();
	for (int i = 0 ; i < n ; i++)
	    club [i] = fi.readInt ();
	ans = tree.solve (dis, club, n);
	if (ans == -1)
	{
	    fo.println ("Roberta acknowledges defeat.");
	    c.println ("Roberta acknowledges defeat.");
	}
	else
	{
	    fo.println ("Roberta wins in " + ans + " strokes.");
	    c.println ("Roberta wins in " + ans + " strokes.");
	}
    }
}

class Node
{
    public int value;
    public Node next;
    public Node parent;

    public Node (int x, Node p)
    {
	value = x;
	next = null;
	parent = p;
    }
}

class BreadthFirstTree
{
    protected boolean[] used;
    protected Node root;
    protected Node last;

    public BreadthFirstTree ()
    {
	used = new boolean [5281];   // problem states max distance is 5280
	for (int i = 0 ; i < 5281 ; i++)
	    used [i] = false;
	root = null;
	last = null;
    }


    public boolean empty ()
    {
	return root == null;
    }


    public void add (int x, Node parent)
    {
	Node newNode = new Node (x, parent);
	used [x] = true;
	if (empty ())
	    root = newNode;
	else
	    last.next = newNode;
	last = newNode;
    }


    // the number of strokes is essentially the level of the node
    // the level of any node is one more than its parent, and
    // the level of null is -1; (root is at level 0)
    public int strokes (Node n)
    {
	if (n == null)
	    return -1;
	else
	    return strokes (n.parent) + 1;
    }


    public int solve (int distance, int[] club, int n)
    {
	Node c;
	int shot;
	
	add (0, null);
	c = root;
	while (c != null)
	{
	    for (int i = 0 ; i < n ; i++)
	    {
		shot = club [i] + c.value;
		if (!used [shot] && shot <= distance)
		    add (shot, c);
		if (shot == distance)
		    return strokes (last);
	    }
	    c = c.next;
	}
	return -1;
    }
}

