// S3 2010 V3: Firehose
//
// version 3: arrays, sorting and binary search
//            (works for all)
// version 2: linked lists: start with h hydrants
//            combining the closest two to eliminate one
//            until K reached (worked for 1 to 5)
// version 1: arrays: eliminate the k biggest gaps.
//            (worked for the first couple of data sets)
//
//
// okay, so this uses the following idea. 
// If you'll need a max hose length of say 200, 
// then lets use a 200 hose length EVERYWHERE!
//
// The hose length is a radius. I use a diameter instead
// and to find the correct length hose, I try diameters
// in a binary search fashion to get to the correct length quickly. 
// Before starting on the process I simplify things by sorting
// and shifting the houses so the biggest gap between houses comes 
// after the LAST house. Thus there are no "wrap around" 
// circular issues.
//
// For a given diameter, I start with the first house and see
// which other houses fall within the diameter. That's one. 
// Then I start at the next house, not covered and cover it
// and the next ones till I can't cover any more. That's two.
// Eventually I get to the last house and look at my count.
// If the count is k, I'm done. (almost)
// To be "double sure" I then reduce the diamter by 1 until
// my count goes above k. Then (diameter+1)/2 is the answer. 
//
// This took me 1 solid week of study of the TEST data. 
// One of the toughest problems I've seen so far!  :-)


import java.awt.*;
import hsa.*;

public class S32010v3
{

    public static int h, k;
    public static int[] house;

    public static void main (String[] args)
    {
	TextInputFile c;
	c = new TextInputFile ("s3.1.in");
	int diameter, x, high, low;

	// input section
	h = c.readInt ();
	house = new int [h];
	for (int i = 0 ; i < h ; i++)
	    house [i] = c.readInt ();
	k = c.readInt ();

	if (k >= h)
	    System.out.println (0);
	else
	{
	    sortHouses ();
	    shiftHouses ();
	    
	    // bianary search for correct diameter
	    diameter = 1000000 / k;
	    high = diameter;
	    low = 0;
	    x = check (diameter);
	    while (x != 0)
	    {
		if (x < 0)
		    low = diameter;
		else
		    high = diameter;
		diameter = (low + high) / 2;
		x = check (diameter);
	    }

	    // when you find a diameter that appears to work,
	    // continually reduce it until it doesn't work any more.
	    x = check (diameter);
	    while (x == 0)
	    {
		diameter--;
		x = check (diameter);
	    }
	    System.out.println ((float) (diameter + 1) / 2);
	}
    }


    // returns -1 if diameter is too small
    // returns  0 if diameter is appears okay
    // returns  1 if diameter is too big
    //
    // Starting at the first house, cover it and 
    // all subsequent house within the diameter of the hose
    // that's one. 
    // move to the next house and cover again. 
    // At the end how many coverings did it take?
    // if exactly k, that's the correct diameter
    // (well maybe... need to verify it by checking
    // smaller diameters until the diameter definitely
    // stopps working) 
    public static int check (int diameter)
    {
	int i = 0;
	int start;
	int count, startPlace, houseCount;

	start = 0;
	i = start;
	count = 0;
	while (i < h)
	{
	    startPlace = house [i];
	    while (i < h && startPlace + diameter > house [i])
		i++;
	    count++;
	    if (i < h && startPlace + diameter == house [i])
		i++;
	}
	if (count < k)
	    return 1;
	else if (count > k)
	    return -1;
	else
	    return 0;
    }


    public static void sortHouses ()
    {
	int hold, j;
	for (int i = 1 ; i < h ; i++)
	{
	    hold = house [i];
	    j = i - 1;
	    while (j >= 0 && hold < house [j])
	    {
		house [j + 1] = house [j];
		j--;
	    }
	    house [j + 1] = hold;
	}
    }


    // this finds the largest gap and rotates it to the end.
    // the first house will always be at location 0
    // and there will not be any complex "wraparound" issues later.
    public static void shiftHouses ()
    {
	// find largest gap
	int largestGap = 1000000 - house [h - 1] + house [0];
	int largestI = 0;
	int hold;
	for (int i = 1 ; i < h ; i++)
	    if (house [i] - house [i - 1] > largestGap)
	    {
		largestGap = house [i] - house [i - 1];
		largestI = i;
	    }
	    
	// shift houses
	hold = house [largestI];
	for (int i = 0 ; i < largestI ; i++)
	    house [i] = house [i] + (1000000 - hold);
	for (int i = largestI ; i < h ; i++)
	    house [i] = house [i] - hold;

	// put them back in order.
	sortHouses ();
    }


    public static void print ()
    {
	for (int i = 0 ; i < h ; i++)
	    System.out.println (house [i]);
    }
}

