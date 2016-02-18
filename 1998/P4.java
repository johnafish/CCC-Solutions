// CCC
// 1998: Problem D Lottery
//
// Add () according to bedmas in expression involving X, +, - only
// Example: "123 + 456 X 789 - 876" becomes "(123 + (456 X 789)) - 876"
//
// Ugly, no tricks. Find the operator, then find the operand to left
// and right (which may be a number or a bracketed expression)
// and add the '(' or ')'
//
// file input consists of the number of expressions
// followed by the expressions themselves

import java.awt.*;
import hsa.*;

public class P4Lottery
{
    static Console c;

    public static void main (String [] args)
    {
	c = new Console ();

	TextInputFile fi = new TextInputFile ("lottery.in");
	TextOutputFile fo = new TextOutputFile ("lottery.out");
	int n, x;
	String s;

	n = fi.readInt ();
	for (int i = 1 ; i <= n ; i++)
	{
	    s = fi.readLine ();

	    // find each 'X' and bracket it
	    x = 0;
	    while (x < s.length ())
	    {
		while (x < s.length () && s.charAt (x) != 'X')
		    x++;
		if (x < s.length ())
		{
		    s = left (s, x);
		    s = right (s, x + 1);
		}
		x = x + 2;    // 1 for the '(' and 1 to move
	    }

	    // find each '+' or '-' and bracket those
	    x = 0;
	    while (x < s.length ())
	    {
		while (x < s.length () && !(s.charAt (x) == '+' || s.charAt (x) == '-'))
		    x++;
		if (x < s.length ())
		{
		    s = left (s, x);
		    s = right (s, x + 1);
		}
		x = x + 2;   // 1 for the '(' and 1 to move
	    }
	    c.println (s.substring (1, s.length () - 1));
	    c.println ("");
	    fo.println (s.substring (1, s.length () - 1));
	    fo.println ("");
	}
	fi.close ();
	fo.close ();
    }


    // puts in the '(' to the left
    // given a string and the place of the operator
    // back off 2 places
    //     if a ')', find the matching '('
    //     else find a space (or beginning of string)
    // add in the '('
    // return the string so it can be updated in main.
    public static String left (String s, int x)
    {
	x = x - 2;
	if (s.charAt (x) == ')')
	{
	    int count = 1;
	    x--;
	    while (count != 0)
	    {
		if (s.charAt (x) == ')')
		    count++;
		else if (s.charAt (x) == '(')
		    count--;
		x--;
	    }
	}
	else
	{
	    while (x >= 0 && s.charAt (x) != ' ')
		x--;
	}
	if (x == -1)
	    s = "(" + s;
	else
	    s = s.substring (0, x + 1) + "(" + s.substring (x + 1);
	return s;
    }


    // puts in the ')' to the right
    // given a string and the place of the operator
    // go ahead 2 places
    //     if a '(', find the matching ')'
    //     else find a space (or end of string)
    // add in the ')'
    // return the string so it can be updated in main.
    public static String right (String s, int x)
    {
	x = x + 2;
	if (s.charAt (x) == '(')
	{
	    int count = 1;
	    x++;
	    while (count != 0)
	    {
		if (s.charAt (x) == '(')
		    count++;
		else if (s.charAt (x) == ')')
		    count--;
		x++;
	    }
	}
	else
	{
	    while (x < s.length () && s.charAt (x) != ' ')
		x++;
	}
	if (x == s.length ())
	    s = s + ")";
	else
	    s = s.substring (0, x) + ")" + s.substring (x);
	return s;
    }
}
