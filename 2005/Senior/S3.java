// The "CCC2005s3QuantumOperations" class.
//
// A 2d matrix type manipulation exercise
// Not super tough, but you need to keep your wits about you
// during the multiplication phase.

import java.awt.*;
import hsa.*;

public class CCC2005s3QuantumOperations
{
    static Console cc;

    public static void main (String[] args)
    {
	cc = new Console ();
	TextInputFile f = new TextInputFile ("s3.1.in");
	int[] [] product = new int [1024] [1024];
	int[] [] a = new int [1024] [1024];
	int[] [] b = new int [1024] [1024];
	int[] rowSum = new int [1024];
	int[] colSum = new int [1024];
	int maxElement, minElement, maxRowSum, minRowSum, maxColSum, minColSum;

	int n, r, c, pr, pc;

	// b is 1 at start
	pr = 1;
	pc = 1;
	b [0] [0] = 1;

	n = f.readInt ();
	for (int k = 0 ; k < n ; k++)
	{
	    // read next matrix
	    r = f.readInt ();
	    c = f.readInt ();
	    for (int i = 0 ; i < r ; i++)
		for (int j = 0 ; j < c ; j++)
		    a [i] [j] = f.readInt ();

	    // calculate product = a * b
	    for (int p = 0 ; p < r * pr ; p++)
		for (int q = 0 ; q < c * pc ; q++)
		    product [p] [q] = b [p % pr] [q % pc] * a [p / pr] [q / pc];

	    pr = r * pr;
	    pc = c * pc;
	    // b = product
	    for (int p = 0 ; p < pr ; p++)
		for (int q = 0 ; q < pc ; q++)
		    b [p] [q] = product [p] [q];
	}

	// gather stats
	maxElement = product [0] [0];
	minElement = product [0] [0];

	for (int i = 0 ; i < pr ; i++)
	    for (int j = 0 ; j < pc ; j++)
	    {
		rowSum [i] += product [i] [j];
		colSum [j] += product [i] [j];
		if (product [i] [j] > maxElement)
		    maxElement = product [i] [j];
		if (product [i] [j] < minElement)
		    minElement = product [i] [j];
	    }

	maxRowSum = rowSum [0];
	minRowSum = rowSum [0];
	maxColSum = colSum [0];
	minColSum = colSum [0];
	for (int i = 0 ; i < pr ; i++)
	{
	    if (rowSum [i] > maxRowSum)
		maxRowSum = rowSum [i];
	    if (rowSum [i] < minRowSum)
		minRowSum = rowSum [i];
	}
	for (int j = 0 ; j < pc ; j++)
	{
	    if (colSum [j] > maxColSum)
		maxColSum = colSum [j];
	    if (colSum [j] < minColSum)
		minColSum = colSum [j];
	}

	cc.println (maxElement);
	cc.println (minElement);
	cc.println (maxRowSum);
	cc.println (minRowSum);
	cc.println (maxColSum);
	cc.println (minColSum);
    }
}
