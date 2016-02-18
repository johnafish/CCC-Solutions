// CCC 2009
//
// S1: Cool Numbers
//
// This is a FASTER method yet, but even MORE tricky.
//
// This approach was suggested to me by the following people:
// Gareth Davies
// Jonathan Collins
// Farnak
// Vincent Siao
// Daniel Galperin
// Amlesh Jayakumar
//
// It turns out that if x = a^3 and x = b^2
// then a^3 = b^2 or a^3/b^2 = 1
//
// That means that if a could be factored into n*m you'd have
// n*m*n*m*n*m/b*b = 1. But wait a minute.... that doesn't work.
// There is no way to factor b to cancel the 3 m's and 3 n's....
// Nope, the only way is if a factors into n*n and b into n*n*n
//
// so that means x = n^6.  QED
//
// SO this problem means: find all the numbers^6 that are in the range
// (Clearly n^6 is both a square and a cube and the above proof
// shows that all cubes that are squares are also of the form n^6)
//
//

import java.awt.*;
import hsa.*;

public class CCC2009S1CoolNumbers6
{

    public static void main (String[] args)
    {
	Console c;
	int a, b;
	int six, power;
	int count = 0;

	c = new Console ();
	TextInputFile f = new TextInputFile ("s1.1.in");
	a = f.readInt ();
	b = f.readInt ();
	six = (int) (Math.pow (a, 1.0 / 6));
	power = six * six * six * six * six * six;
	while (power < a)
	{
	    six++;
	    power = six * six * six * six * six * six;
	}
	while (power <= b)
	{
	    six++;
	    power = six * six * six * six * six * six;
	    count++;
	}
	c.println (count);
    }
}

