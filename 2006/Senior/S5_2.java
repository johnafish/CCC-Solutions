// CCC2006s5OriginofLife
//
// This is Jason Jackson's algorithm 
//
// In this approach all the Gardens of Eden are found. 
// Then, until the given is reached, each eden is cycled to the
// the next generation. If after 50 generations the given is not found
// quit and return -1.
//
// To find all the edens, each number 0 to 2^(m*n) is converted to a 2D array
// its next generation found and that generation is marked as NOT an eden. 
// then all the edens are collected in a array.
// the original is looked for in the group of edens. If its not there
// cycle all the edens to their next generation. Continue looking and cycling
// until the original is found. Stop after 50 tries and return -1.
//

import java.awt.*;
import hsa.*;

public class CCC2006s5OriginofLifeV2
{
    static Console console;
    static int m, n, a, b, c, size;
    static int[] eden;
    static int edenSize;
    static byte[] [] life1, life2;

    public static void main (String[] args)
    {
	console = new Console ();
	TextInputFile f = new TextInputFile ("s5.5.in");
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
	getEdens ();
	console.println ("" + countFromEden (original));
    }


    // for all possible life combos,
    // any generated grid is NOT a garden of eden
    // save the list of edens in eden array
    public static void getEdens ()
    {
	boolean[] isEden;
	isEden = new boolean [size];
	for (int i = 0 ; i < size ; i++)
	    isEden [i] = true;
	for (int i = 0 ; i < size ; i++)
	    isEden [nextGen (i)] = false;
	edenSize = 0;
	eden = new int [size];
	for (int i = 0 ; i < size ; i++)
	    if (isEden [i])
		eden [edenSize++] = i;
    }


    // generates life2 from life1
    // it returns the "number" for life2.
    public static int nextGen (int x)
    {
	boolean ok = true;
	int up, down, left, right, sum, value;
	to2D (x);

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


    // this counts the steps needed to get from an eden to the given int
    // after 50 steps, there must be a loop, quit and return -1.
    public static int countFromEden (int start)
    {
	boolean found = false;
	int count;
	for (count = 0 ; count < 50 && !found ; count++)
	    for (int i = 0 ; i < edenSize && !found ; i++)
	    {
		if (eden [i] == start)
		    found = true;
		else
		    eden [i] = nextGen (eden [i]);
	    }
	if (!found)
	    return -1;
	else
	    return count - 1;

    }
}


