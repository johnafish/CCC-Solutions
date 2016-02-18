// CCC 2009
//
// S4: Ship and Shop   Dijkstra's Algorithm approach
// 
// this is algorithm was given to me by both
//    Amlesh Jayakumar of Waterloo Collegiate Institute
//    Wen-Hao Lue of Bayview Glen Private School
//
// I found an excellent explaination of Dijkstra's Algorithm on
// http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/GraphAlgor/dijkstraAlgor.htm
//

import java.awt.*;
import hsa.*;

public class CCC2009S4ShipAndShopDijkstra
{
    public static final char INFINITY = 65535;

    public static int n;             // number of cities
    public static char[] shipping;   // shipping contains shippping cost
				     // to that city from the destination city
				     // char arrays are used to save space
				     // 5000x5000 ints is a too much for
				     // my computer :-)
    public static char[] [] edges;
    public static char[] cost;       // contains the cost of the pencils

    public static void main (String[] args)
    {
	TextInputFile f = new TextInputFile ("s4.1.in");
	n = f.readInt ();

	edges = new char [n + 1] [n + 1];
	shipping = new char [n + 1];
	char[] cost = new char [n + 1];

	for (int i = 1 ; i <= n ; i++)
	{
	    cost [i] = INFINITY;
	    for (int j = 1 ; j <= n ; j++)
		edges [i] [j] = 0;
	}

	// Store trade routes (edges)
	int t = f.readInt ();
	for (int i = 1 ; i <= t ; i++)
	{
	    int x = f.readInt ();
	    int y = f.readInt ();
	    char c = (char) f.readInt ();
	    if (x >= 1 && x <= n && y >= 1 && y <= n)  //strangely some data files have invalid cities??
	    {
		edges [x] [y] = c;
		edges [y] [x] = c;
	    }
	}

	// Store cities that sell pencils
	int k = f.readInt ();
	char hold1, hold2;
	for (int i = 0 ; i < k ; i++)
	{
	    hold1 = (char) f.readInt ();
	    hold2 = (char) f.readInt ();
	    if (hold1 >= 1 && hold1 <= n)
		cost [hold1] = hold2;
	}

	int destination = f.readInt ();

	Dijkstra (destination);
 
	// Dijkstra has given the minimum shipping costs from every city
	// to the destination. So the cheapest pencil we can get at that
	// city is from the city with the lowest shipping cost + pencil cost.
	int min = INFINITY;
	int totalCost = 0;

	for (int i = 1 ; i <= n ; i++)
	{
	    totalCost = cost [i] + shipping [i];
	    if (totalCost < min)
		min = totalCost;
	}
	System.out.println (min);
    }


    // Use Dijkstra's algorithm to find the cheapest path from the destination
    // to all other points in O(n^2) time.
    // In other words it fills the shipping array with the minimum shipping
    // cost from that city to the destination city.
    public static void Dijkstra (int start)
    {
	boolean[] used = new boolean [n + 1];
	int city, small, count;

	//step 1: fill all shipping values with a large value except the destination
	shipping = new char [n + 1];
	for (int i = 1 ; i <= n ; i++)
	{
	    used [i] = false;
	    shipping [i] = INFINITY;
	}
	shipping [start] = 0;
	count = 0;
	while (count < n)
	{
	    // Step 2: Choose the next city:
	    // the one with the smallest shipping that hasn't been used.
	    small = INFINITY;
	    city = 1;
	    for (int i = 1 ; i <= n ; i++)
	    {
		if (!used [i] && shipping [i] < small)
		{
		    small = shipping [i];
		    city = i;
		}
	    }
	    
	    // Step 3: for all cities (i) connected to the city in question,
	    //         update their distance to city. It is the the distance
	    //         to the city plus the distance along the edge from
	    //         the ith city to the city, if that is smaller than
	    //         what is already stored in shipping for that city.
	    used [city] = true;
	    count++;
	    for (int i = 1 ; i <= n ; i++)
		if (edges [city] [i] > 0 && !used [i])
		    if (shipping [i] > shipping [city] + edges [city] [i])
			shipping [i] = (char) (shipping [city] + edges [city] [i]);
	}
    }
}

