// CCC 2010 S5
// by Victor Liu of Dr. Norman Bethune C.I.
//
// The approach to solving this problem is to break the problem
// down recursively.
//
//     root
//      ...
//      ...
//     /   \
//    N    ...
//   / \
//  L   R
//
// N is the current node being inspected, L and R are the left
// and right subtrees of N respectively.
//
// The main method is a recursive method which calculates and stores
// the max nutrients a node can return, given a growth agent amount.
// The method, not only calculates the max nutrients for a single
// growth agent amount, but stores the max nutrients for ALL growth
// agents from 0 to the given amount.
//
// So if such a method exists, how can we use it to calculate the
// max nutrients flowing to  N from L, R? We use two arrays,
// optL and optR and for each growth agent 0 to a given amount,
// we calculate all the combinations of growth agent given to the
// edge and to the node below. We save the max for each growth agent
// amount, 0 to the given amount.  We do that for both sides.
//
// Now it is a simple matter to calculate the max nutrients coming to N
// for all growth agents 0 to the given amount by tring all combos down
// one side and the other using the optL and optR arrays.
//
// The final answer is in the root and it is the max nutrients for the
// total amount of growth agents given.


import java.awt.*;
import hsa.*;

public class S52010VLv2
{
    public static void main (String[] args)
    {
	TextInputFile c;
	c = new TextInputFile ("s5.8.in");
	TreeNode root = createTreeNode (c.readLine ()); //convert String to tree
	int growth = c.readInt (); //growth agent

	// find the maximum amount of transportable nutrients using
	// i = 0 .. growth units of growth agent on root (essentially
	// solving the problem)
	optimize (root, growth);

	System.out.println (root.maxNutrients [growth]);
    }


    public static TreeNode createTreeNode (String s)
    {
	s = s.trim ();
	// leaf node
	if (!s.startsWith ("("))
	    return new TreeNode (Integer.parseInt (s));
	else
	{
	    //drop the outer brackets
	    s = s.substring (1, s.length () - 1).trim ();

	    // split into two parts
	    // the first part is either a number or
	    // a section ending with a ")"
	    int i;
	    if (s.startsWith ("("))
	    {
		// find corresponding ")" by counting
		int count = 1;
		i = 1;
		while (count > 0)
		{
		    if (s.charAt (i) == '(')
			count++;
		    else if (s.charAt (i) == ')')
			count--;
		    i++;
		}
	    }
	    else
		i = s.indexOf (" ");

	    // create the left and right subtrees
	    return new TreeNode (createTreeNode (s.substring (0, i)), createTreeNode (s.substring (i + 1)));
	}
    }


    public static void optimize (TreeNode node, int growth)
    {
	// trivial solution for leaf nodes
	if (node.left == null)
	{
	    TreeNode leaf = node;
	    leaf.maxNutrients = new int [growth + 1];
	    for (int i = 0 ; i <= growth ; i++)
		leaf.maxNutrients [i] = leaf.value + i;
	}
	else
	{
	    TreeNode n = node;
	    int max, tmp;

	    // optimize left subtree
	    optimize (n.left, growth);

	    // optimize left subtree with consideration for left branch/edge thickness
	    int[] optL = new int [growth + 1];
	    for (int i = 0 ; i <= growth ; i++)
	    {
		max = 0;
		for (int j = 0 ; j <= i ; j++)
		{
		    tmp = Math.min ((1 + j) * (1 + j), n.left.maxNutrients [i - j]);
		    if (tmp > max)
			max = tmp;
		}
		optL [i] = max;
	    }

	    // optimize right subtree
	    optimize (n.right, growth);

	    // optimize right subtree with consideration for right branch/edge thickness
	    int[] optR = new int [growth + 1];
	    for (int i = 0 ; i <= growth ; i++)
	    {
		max = 0;
		for (int j = 0 ; j <= i ; j++)
		{
		    tmp = Math.min ((1 + j) * (1 + j), n.right.maxNutrients [i - j]);
		    if (tmp > max)
			max = tmp;
		}
		optR [i] = max;
	    }

	    // optimizes both branches together
	    n.maxNutrients = new int [growth + 1];
	    for (int i = 0 ; i <= growth ; i++)
	    {
		max = 0;
		for (int j = 0 ; j <= i ; j++)
		{
		    tmp = optL [j] + optR [i - j];
		    if (tmp > max)
			max = tmp;
		}
		n.maxNutrients [i] = max;
	    }
	}
    }
}


class TreeNode
{
    public int[] maxNutrients;
    public int value;
    public TreeNode left, right;

    // for a non-leaf node
    public TreeNode (TreeNode l, TreeNode r)
    {
	value = 0;
	left = l;
	right = r;
    }


    // for a leaf
    public TreeNode (int v)
    {
	value = v;
	left = null;
	right = null;
    }
}
