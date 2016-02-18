// CCC 2007
//
// S3: Friends
//
// this improved version is due to Aaron Voelker, of Bell High School (Ottawa)
//
// This is a relatively straight forward array processing exercise,
//
// friend is an array of friends :-)
//     (that is friend[5] is the friend of student 5)
// visited is the array keeping track of loops
//     (visited starts out all at 0.
//      Each time a student is 'visited' it gets a non zero value.
//      THUS there is a loop if you hit a non zero visited value,
//      and you can stop.)
//
// processing is fairly straight forward:
// start at student x, go to his friend, friend [x]
// i.e. x = friend[x]
// continue until x gets to y or
//         you revisit a number (ie go into a loop)
// If x==y you win, so to speak, and print the distance.

import java.awt.*;
import hsa.*;

public class CCC2007S3FriendsV2
{
    static Console c;
    static int n, i, distance, x, y;
    static int[] friend;
    static boolean[] visited;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s3.2.in");

	// get the friend relationships
	n = f.readInt ();
	friend = new int [10000];
	visited = new boolean [10000];
	for (int i = 0 ; i < 10000 ; i++)
	    friend [i] = 0;
	for (int i = 0 ; i < n ; i++)
	    friend [f.readInt ()] = f.readInt ();

	// get the student pairs and determine distance between them
	x = f.readInt ();
	y = f.readInt ();
	while (!(x == 0 && y == 0))
	{
	    for (int i = 0 ; i < 10000 ; i++)
		visited [i] = false;
	    distance = -1;
	    while (!visited [x] && x != y)
	    {
		visited [x] = true;
		distance++;
		x = friend [x];
	    }
	    if (x == y)
		c.println ("Yes " + distance);
	    else
		c.println ("No");
	    x = f.readInt ();
	    y = f.readInt ();
	}
    }
}
