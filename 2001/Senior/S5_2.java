// CCC 2001
//
// S5 Post
//
// solution by Hubert Tong, Queen's University
//
// This is a recursive algorithm, building on success!
//
// Essentially the method builds a global aaa and bbb out of the 
// a and b arrays. The Begin parameters of the method track the 
// that part of aaa and bbb that have so far matched. k tracks the 
// number of pieces added on each and the array i remembers which ones.
//
// the main idea is that aaa and bbb should always look something like:
//    aaa = "hellothere" and bbb = "helloth"
// in that the smaller of the two should completely match the first part of the larger
//
// In English:
// if Begins (the part that matches) = Ends (the end) then
//    you have two completely matching strings! We're Done!
//    (k must be > 0 because first time in, with all 0's they match too)
// if at least one End and Begin don't match, that means
//    we don't have a complete match: the smaller should always = 
//    the first part of the larger. So this is no good: return false.
// if k = m - 1 we gone too far because to add another one would mean
//    k = m and that's not allowed. 
// so at this point we have an okay match: 
//    the smaller one matches the first part of the larger
//    so add all the different strings from a and b onto
//    the end of aaa and bbb, move the Begins as far along as you can,
//    keeping things matching and see if any new addition works. 
// Ultimately if no additions work, we're toast.    
//

import java.io.*;
import hsa.*;

public final class S5PostHT
{
    static int m, n, k;
    static String[] a, b;
    static String aaa = "";
    static String bbb = "";
    static int[] i = new int [40];

    static boolean solve (int aBegin, int bBegin, int aEnd, int bEnd, int kk)
    {
	if (kk > 0 && aBegin == aEnd && bBegin == bEnd)
	{
	    k = kk;
	    return true;
	}
	if (aBegin != aEnd && bBegin != bEnd)
	    return false;
	if (kk == m - 1)
	    return false;
	for (int j = 0 ; j < n ; j++)
	{
	    aaa = aaa.substring (0, aEnd) + a [j];
	    bbb = bbb.substring (0, bEnd) + b [j];

	    int aBegin2 = aBegin;
	    int bBegin2 = bBegin;
	    int aEnd2 = aaa.length ();
	    int bEnd2 = bbb.length ();
	    while (aBegin2 < aEnd2 && bBegin2 < bEnd2 && aaa.charAt (aBegin2) == bbb.charAt (bBegin2))
	    {
		aBegin2++;
		bBegin2++;
	    }
	    if (solve (aBegin2, bBegin2, aEnd2, bEnd2, kk + 1))
	    {
		i [kk] = j;
		return true;
	    }
	}
	return false;
    }


    public static void main (String[] args) throws IOException
    {
	TextInputFile fi = new TextInputFile ("post3.in");

	m = fi.readInt ();
	n = fi.readInt ();

	a = new String [n];
	b = new String [n];
	for (int j = 0 ; j < n ; j++)
	    a [j] = fi.readString ();
	for (int j = 0 ; j < n ; j++)
	    b [j] = fi.readString ();

	if (solve (0, 0, 0, 0, 0))
	{
	    System.out.println (k);
	    for (int j = 0 ; j < k ; j++)
		System.out.println (i [j] + 1);
	}
	else
	    System.out.println ("No solution.");
    }
}
