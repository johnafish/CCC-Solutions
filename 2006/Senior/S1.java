// CCC2006s1Maternity
//
// given a mothers and fathers genes, determine if a baby may
// be theirs. (dominant/recessive gene ideas)
//
// Basic string handling and basic structures

import java.awt.*;
import hsa.*;

public class CCC2006s1Maternity
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s1.3.in");
	int x;
	String mother, father, baby;

	mother = f.readString ();
	father = f.readString ();
	x = f.readInt ();
	for (int i = 1 ; i <= x ; i++)
	{
	    baby = f.readString ();
	    if (possibleBaby (mother, father, baby))
		c.println ("Possible baby.");
	    else
		c.println ("Not their baby!");
	}
    }


    // if baby has Capital, one parent needs to have one Capital
    // if baby has lower, both parents need a lower
    public static boolean possibleBaby (String m, String f, String b)
    {
	boolean okay = true;
	for (int i = 0 ; i <= 4 && okay ; i++)
	    if (b.charAt (i) >= 'A' && b.charAt (i) <= 'E')
		okay = (m.charAt (i * 2) >= 'A' && m.charAt (i * 2) <= 'E') ||
		    (m.charAt (i * 2 + 1) >= 'A' && m.charAt (i * 2 + 1) <= 'E') ||
		    (f.charAt (i * 2) >= 'A' && f.charAt (i * 2) <= 'E') ||
		    (f.charAt (i * 2 + 1) >= 'A' && f.charAt (i * 2 + 1) <= 'E');
	    else
		okay = ((m.charAt (i * 2) >= 'a' && m.charAt (i * 2) <= 'e') ||
			(m.charAt (i * 2 + 1) >= 'a' && m.charAt (i * 2 + 1) <= 'e')) &&
		    ((f.charAt (i * 2) >= 'a' && f.charAt (i * 2) <= 'e') ||
			(f.charAt (i * 2 + 1) >= 'a' && f.charAt (i * 2 + 1) <= 'e'));
	return okay;
    }
}


