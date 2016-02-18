// CCC 2008
//
// S3: Maze
//
// This is a BFS approach, thanks to Konstantin Lopyrev of Richmond Hill HS
// 
// Generally its quite straight forward. 
//
// maze keeps the actual maze
// grid keeps the number of intersections, 
//       (initialized to -1, indicating it has not been reached yet.)
//
// Start in the top corner and add it to a queue
// set grid (top corner) = 1
// while the queue is not empty
//    move in the directions you can, given where you are, 
//                  what's in the grid where will move to
//                  and if you haven't been there before
//    if you can move somewhere, 
//             set its grid value to 
//           1 more that the current location 
//           and add that new poin to the grid.
//
// The Breadth First Search is much faster than the resursive approach
// because no spot is ever visited twice. The recursion is essentially 
// a Depth First Search going down in one direction as far as possible, 
// before trying a new one. The BFS fans out across the maze going as 
// quickly as possible to each intersection.
//
// This version is a tad long as I included my own queue class. 
// I still think using built-in classes like vectors and queues is cheating :-) 
//     

import java.awt.*;
import hsa.*;

public class CCC2008S3MazeBFS
{

    public static void main (String[] args)
    {
	Console cc;
	char[] [] maze;
	int[] [] grid;
	int row, col;
	int t;
	cc = new Console ();
	Queue q = new Queue ();
	TextInputFile f = new TextInputFile ("s3.5.in");
	t = f.readInt ();
	for (int k = 0 ; k < t ; k++)
	{
	    row = f.readInt ();
	    col = f.readInt ();
	    maze = new char [row] [col];
	    grid = new int [row] [col];
	    for (int i = 0 ; i < row ; i++)
		for (int j = 0 ; j < col ; j++)
		    maze [i] [j] = f.readChar ();
	    for (int i = 0 ; i < row ; i++)
		for (int j = 0 ; j < col ; j++)
		    grid [i] [j] = -1;

	    //BFS search of the maze
	    grid [0] [0] = 1;
	    q.add (0, 0);
	    while (!q.empty ())
	    {
		QueueNode x = q.remove ();
		int r = x.r;
		int c = x.c;
		// go up if you can
		if ((maze [r] [c] == '+' || maze [r] [c] == '|') && r > 0 && maze [r - 1] [c] != '*' && grid [r - 1] [c] == -1)
		{
		    grid [r - 1] [c] = grid [r] [c] + 1;
		    q.add (r - 1, c);
		}
		// go down if you can
		if ((maze [r] [c] == '+' || maze [r] [c] == '|') && r < row - 1 && maze [r + 1] [c] != '*' && grid [r + 1] [c] == -1)
		{
		    grid [r + 1] [c] = grid [r] [c] + 1;
		    q.add (r + 1, c);
		}
		// go left if you can
		if ((maze [r] [c] == '+' || maze [r] [c] == '-') && c > 0 && maze [r] [c - 1] != '*' && grid [r] [c - 1] == -1)
		{
		    grid [r] [c - 1] = grid [r] [c] + 1;
		    q.add (r, c - 1);
		}
		// go right if you can
		if ((maze [r] [c] == '+' || maze [r] [c] == '-') && c < col - 1 && maze [r] [c + 1] != '*' && grid [r] [c + 1] == -1)
		{
		    grid [r] [c + 1] = grid [r] [c] + 1;
		    q.add (r, c + 1);
		}
	    }
	    cc.println (grid [row - 1] [col - 1]);
	}
    }
}

class QueueNode
{
    public int r, c;
    public QueueNode next;

    public QueueNode (int r, int c)
    {
	this.r = r;
	this.c = c;
	next = null;
    }
}

class Queue
{
    protected QueueNode front, back;

    public Queue ()
    {
	front = null;
	back = null;
    }


    public boolean empty ()
    {
	return front == null;
    }


    public void add (int r, int c)
    {
	QueueNode n = new QueueNode (r, c);
	if (empty ())
	    front = n;
	else
	    back.next = n;
	back = n;
    }


    public QueueNode remove ()
    {
	if (!empty ())
	{
	    QueueNode hold = front;
	    front = front.next;
	    return hold;
	}
	else
	    return null;
    }
}
