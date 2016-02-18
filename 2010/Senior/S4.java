// S4 2010 Animal Farm
//
// 2D arrays, graphs and Prim's algorithm
// to find the Minimal Spanning Tree (MST)
//
// this is a huge input processing problem
// The input has to be converted to a proper
// weighted graph, with the vertices being the
// ANIMALS themselves. (The last vertix is
// the OUTSIDE.)
//
// once this is done you need calculate the
// MST for the graph, TWICE. Once without considering
// the outside, and one considering the outside.
//
// the version of Prim's I'm using came form
// "Algorithms" by Robert Sedgewick, 2nd Edition
//

import java.awt.*;
import hsa.*;

public class S42010
{

    public static int[] [] graph;


    public static void main (String[] args)
    {
	TextInputFile c;
	c = new TextInputFile ("s4.1.in");
	int n;
	Info[] [] input;

	int numEdges;
	int[] corner = new int [1001];
	int[] cost = new int [1001];

	n = c.readInt ();

	// create and initialize graph
	graph = new int [n + 1] [n + 1];
	for (int i = 0 ; i < n + 1 ; i++)
	    for (int j = 0 ; j < n + 1 ; j++)
		graph [i] [j] = 999999999;
	for (int j = 0 ; j < n + 1 ; j++)
	    graph [j] [j] = 0;

	// initialize the input grid
	input = new Info [1001] [1001];
	for (int i = 0 ; i < 1001 ; i++)
	    for (int j = 0 ; j < 1001 ; j++)
		input [i] [j] = new Info (-1, 0);

	// input section
	for (int i = 0 ; i < n ; i++)
	{
	    numEdges = c.readInt ();
	    for (int k = 0 ; k < numEdges ; k++)
		corner [k] = c.readInt ();
	    for (int k = 0 ; k < numEdges ; k++)
		cost [k] = c.readInt ();

	    for (int k = 0 ; k < numEdges ; k++)
	    {
		int j = (k + 1) % numEdges;

		// and old fence
		if (input [corner [k]] [corner [j]].cost > 0)
		{

		    // this if is needed for test data 3,4 and 5. In these cases
		    // there are TWO or more fences between the same two pens with
		    // different costs. Take only the smaller one.
		    if (graph [i] [input [corner [k]] [corner [j]].animal] > cost [k])
		    {
			graph [i] [input [corner [k]] [corner [j]].animal] = cost [k];
			graph [input [corner [k]] [corner [j]].animal] [i] = cost [k];
		    }
		    input [corner [k]] [corner [j]].animal = -1;
		    input [corner [j]] [corner [k]].animal = -1;
		}

		// a new fence
		else
		{
		    input [corner [k]] [corner [j]].cost = cost [k];
		    input [corner [k]] [corner [j]].animal = i;
		    input [corner [j]] [corner [k]].cost = cost [k];
		    input [corner [j]] [corner [k]].animal = i;
		}
	    }
	}

	// at this point, only outside edges in input array
	// have >0 in the animal field.
	// Need to get the cost for each animal to outside
	// (that is, to animal[n])
	for (int i = 0 ; i < 1001 ; i++)
	    for (int j = 0 ; j < 1001 ; j++)
		if (input [i] [j].animal >= 0)
		{
		    if (graph [input [i] [j].animal] [n] > input [i] [j].cost)
		    {
			graph [input [i] [j].animal] [n] = input [i] [j].cost;
			graph [n] [input [i] [j].animal] = input [i] [j].cost;
		    }
		}

	// do Prim's for all animals (excluding outside)
	int answer1 = prims (n);

	// do Prim's for all animals (including outside)
	int answer2 = prims (n + 1);

	System.out.println (Math.min (answer1, answer2));
    }


    public static int prims (int n)
    {
	int unseen = 99999999;
	int[] val = new int [n + 1];
	for (int k = 1 ; k <= n ; k++)
	    val [k] = -unseen;
	val [0] = -(unseen + 1);
	int min = 1;
	int k;
	do
	{
	    k = min;
	    val [k] = -val [k];
	    min = 0;
	    if (val [k] == unseen)
		val [k] = 0;
	    for (int t = 1 ; t <= n ; t++)
	    {
		if (val [t] < 0)
		{
		    if (graph [k - 1] [t - 1] != 999999999 && val [t] < -(graph [k - 1] [t - 1]))
			val [t] = -(graph [k - 1] [t - 1]);
		    if (val [t] > val [min])
			min = t;
		}
	    }
	}
	while (min != 0);

	// val contains all the values in the MST
	// add them up for the total value of trampled fences
	int answer = 0;
	for (int i = 1 ; i < n + 1 ; i++)
	    answer = answer + val [i];
	return answer;
    }
}


class Info
{
    public int animal;
    public int cost;

    public Info (int a, int c)
    {
	animal = a;
	cost = c;
    }
}


