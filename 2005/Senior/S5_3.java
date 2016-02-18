// The "CCC2005S5PinBallRankingTree" class.
//
// this binary search tree approach is due to Anton Likhtarov
//
// idea is if you go:
//    right in the tree, add one to the node's rank
//    left in the tree, add one more than the node's rank to the
//         variable that the add method returns
//
// lets work thru the original example: 100, 200, 150, 170, 50
// first 100 is the root. (0 is returned)
// then 200 inserted and the tree becomes;
//      100(1)
//           200(0)     (0 is returned)
// then 150 is inserted:
//      100(2)
//           200(0)
//        150(0)     (1 is returned: 1 more than 200's 0 because we went left)
// then 170 is inserted:
//      100(3)
//           200(0)
//        150(1)
//           170(0) (1 is returned: 1 more than 200's 0 because we went left)
// then 50 is inserted:
//       100(3)
//    50(0)   200(0)
//          150(1)
//            170(0) (4 is returned: 1 more than 100's 3 because we went left)
//
//
// Running time for this on a P4 1.2Ghz is about 7 or 8 sec for the worst case.
// More then 10x faster than the insertion sort approach. :-)

import java.awt.*;
import hsa.*;

public class CCC2005S5PinBallRankingTree
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s5.8.in");
	int t = f.readInt ();
	Tree x = new Tree ();
	long sum = 0;
	for (int k = 1 ; k <= t ; k++)
	    sum += x.add (f.readInt ()) + 1;
	double ans = (double) (sum) / t;
	c.println (ans, 0, 2);
    }
}


class Node
{
    int score;
    int rank;
    Node left, right;

    public Node (int s)
    {
	score = s;
	rank = 0;
	left = null;
	right = null;
    }
}


class Tree
{
    Node root;

    public Tree ()
    {
	root = null;
    }


    public int add (int s)
    {
	int rank = 0;
	if (root == null)
	    root = new Node (s);
	else
	{
	    Node n = root;
	    while (true)  // not infinite: it will insert and exits
		if (s < n.score)
		{
		    rank += n.rank + 1;
		    if (n.left == null)
		    {
			n.left = new Node (s);
			return rank;
		    }
		    else
			n = n.left;
		}
		else
		{
		    n.rank++;
		    if (n.right == null)
		    {
			n.right = new Node (s);
			return rank;
		    }
		    else
			n = n.right;
		}
	}
	return rank; // it never gets here, but Java doesn't know that :-)
    }
}
