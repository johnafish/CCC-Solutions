// CCC 1996
// Problem C: Pattern Generator

// Given k and n, print all bit patterens of k 1's in bit strings of length n
// in descending order

// Eg: k = 2 n = 5
// 11000
// 10100
// 10010
// 10001
// 01100
// 01010
// 01001
// 00110
// 00101
// 00011

// There is "trick" to this:
// a. create the first string (easy)
// b. for ALL other strings find the LAST "10" and reverse it, AND
//                 reverse the right part of the sring AFTER the "10"
//
import java.awt.*;
import hsa.*;

public class P3bitpattern
{
    static Console c;           // The output console

    public static void main (String [] args)
    {
	c = new Console ();

	TextInputFile fin = new TextInputFile ("pat.in");
	TextOutputFile fout = new TextOutputFile ("pat.out");
	String s;
	StringBuffer b;
	int number, i, k, n, x;

	number = fin.readInt ();
	for (int j = 0 ; j < number ; j++)
	{

	    // read and create the original string
	    n = fin.readInt ();
	    k = fin.readInt ();
	    s = "";
	    for (i = 0 ; i < k ; i++)
		s = s + "1";
	    for (; i < n ; i++)
		s = s + "0";

	    // find the last "10", reverse that AND
	    //       reverse the part to the right of it
	    x = s.lastIndexOf ("10");
	    fout.println ("The bit patterns are: ");
	    c.println ("The bit patterns are: ");
	    while (x >= 0)
	    {
		fout.println (s);
		c.println (s);
		b = new StringBuffer (s.substring (x + 2));
		s = s.substring (0, x) + "01" + b.reverse ();
		x = s.lastIndexOf ("10");
	    }
	    c.println (s);
	    c.println ("\n");
	    fout.println (s);
	    fout.println ("\n");
	}
	fout.close ();
	fin.close ();
    }
}
