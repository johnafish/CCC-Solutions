// CCC 2008
//
// S4: TwentyFour
//
// Written by Konstantin Lopyrev
// (Hubert Tong, independently, sent me an almost identical version to this)
//
// This uses recursion, stacks and RPN to evaluate all the permutations
//
// The heart is the "go" method.
// i tracks the numbers used, when its 4, you've used them all
// s is a stack of the numbers used so far.
//
// if the stack has only one value and i = 4, we're done and
// using that final answer of this permutation of numbers and operations
// update answer.
//
// if i < 4, there are more numbers to add. Add'em!
//
// If there are more than 2 numbers on the stack, do all the possible
// operations.
//
// What is certainly confusing is the fact that each time we generate
// new stacks (hence all the cloning) from the input stack for the
// recursion to work.
//
// this might help: if the original numbers were 12, 3,9, 12
// these are the "go" calls:
//  1.  i=0  Stack =              original call
//  2.  i=1  Stack = 12           1st number added
//  3.  i=2  Stack = 12 3         2nd number added
//  4.  i=3  Stack = 12 3 9       3rd number added
//  5.  i=4  Stack = 12 3 9 12    4th number added
//  6.  i=4  Stack = 12 3 21      add 3 4
//  7.  i=4  Stack = 12 24        add 2 3
//  8.  i=4  Stack = 36           add 1 2  (answer is still 0)
//  9.  i=4  Stack = -12          we're back to 7. subtract 1 2
//  10. i=4  Stack = 288          we're back to 7. multiply 1 2 (divide is a no go)
//  11. i=4  Stack = 12 -18       we're back to 6. and subtract 2 3 
//  12. i=4  Stack = -6           add 1 and 2   (answer is still 0 )
//  13. i=4  Stack = 30           we're back to 11. subtract 1 2
//  14. i=4  Stack = -216         we're back to 11. multiply 1 2 (divide is a no go)
//  15. i=4  Stack = 12 63        we're back to 6. multiply 2 3
//  ...
//  21. i=4  Stack = 12           answer gets its first update to 12! :-)
//  ...
//  eventually we get things like  i=3  Stack = 12 12 which is back to 4 and we add 2 3
//  ...
//  it goes and goes and goes till all permutations are done and we finish the original call
//
// 

import java.awt.*;
import hsa.*;

public class CCC2008S4TwentyFourRPN
{
    static Console c;
    static int answer;
    static boolean[] used;
    static int[] card;


    public static void go (int i, S4Stack s) throws CloneNotSupportedException
    {
	//System.out.println ("i=" + i + "  Stack = " + s);


	// We're done!
	if (s.size () == 1 && i == 4)
	{
	    if (s.top () > answer && s.top () <= 24)
		answer = s.top ();
	    return;
	}

	// add more numbers to the stack if possible
	if (i < 4)
	{
	    for (int j = 0 ; j < 4 ; j++)
		if (!used [j])
		{
		    used [j] = true;
		    S4Stack s2 = (S4Stack) s.clone ();
		    s2.push (card [j]);
		    go (i + 1, s2);
		    used [j] = false;
		}
	}

	// do an operation if more than 1 number on stack
	if (s.size () >= 2)
	{
	    S4Stack t1 = (S4Stack) s.clone ();
	    int b = t1.pop ();
	    int a = t1.pop ();

	    S4Stack t2 = (S4Stack) t1.clone ();
	    t2.push (a + b);
	    go (i, t2);

	    t2 = (S4Stack) t1.clone ();
	    t2.push (a - b);
	    go (i, t2);

	    t2 = (S4Stack) t1.clone ();
	    t2.push (a * b);
	    go (i, t2);

	    if (b != 0 && a % b == 0)
	    {
		t2 = (S4Stack) t1.clone ();
		t2.push (a / b);
		go (i, t2);
	    }
	}
    }


    public static void main (String[] args) throws CloneNotSupportedException
    {
	int n;
	c = new Console ();
	TextInputFile f = new TextInputFile ("s4.5.in");
	n = f.readInt ();
	card = new int [4];
	used = new boolean [4];
	for (int i = 0 ; i < n ; i++)
	{
	    answer = 0;
	    for (int j = 0 ; j < 4 ; j++)
	    {
		card [j] = f.readInt ();
		used [j] = false;
	    }
	    go (0, new S4Stack ());
	    c.println (answer);
	}

    }
}


class S4Stack implements Cloneable
{
    protected int[] stack;
    protected int top;

    public S4Stack ()
    {
	top = 0;
	stack = new int [4];
    }


    public String toString ()
    {
	String s = "";
	for (int i = 0 ; i < top ; i++)
	    s += stack [i] + " ";
	return s;
    }


    public Object clone () throws CloneNotSupportedException
    {
	S4Stack newObject =
	    (S4Stack) super.clone ();
	newObject.stack =
	    (int[]) stack.clone ();
	return newObject;
    }


    public boolean empty ()
    {
	return top == 0;
    }


    public int size ()
    {
	return top;
    }


    public void push (int x)
    {
	stack [top++] = x;
    }


    public int pop ()
    {
	if (!empty ())
	    return stack [--top];
	else
	    return -9999999;
    }


    public int top ()
    {
	if (!empty ())
	    return stack [top - 1];
	else
	    return -9999999;
    }
}


