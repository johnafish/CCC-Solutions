// CCC 2008
//
// S1: It's Cold Here
//
// this is the classic "find smallest" number problem
// (The trick is to set the 1st one to the smallest, or coldest)
//

import java.awt.*;
import hsa.*;

public class CCC2008S1ItsColdHere
{
    static Console c;

    public static void main (String[] args)
    {
	String city, coldestCity;
	int temp, coldest;
	c = new Console ();
	TextInputFile f = new TextInputFile ("s1.3.in");

	city = f.readString ();
	coldestCity = city;
	coldest = f.readInt ();
	while (!city.equals ("Waterloo"))
	{
	    city = f.readString ();
	    temp = f.readInt ();
	    if (temp < coldest)
	    {
		coldestCity = city;
		coldest = temp;
	    }
	}
	c.println (coldestCity);
    }
}
