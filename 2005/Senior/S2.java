// The "CCC2005s2MouseMoves" class.
//
// Basic structures: watch your variables and loops
// but otherwise very simple and straight forward.

import java.awt.*;
import hsa.*;

public class CCC2005s2MouseMoves
{
    static Console cc;

    public static void main (String[] args)
    {
	cc = new Console ();
	TextInputFile f = new TextInputFile ("s2.5.in");
	int c, r, x, y, a, b;

	c = f.readInt ();
	r = f.readInt ();
	a = f.readInt ();
	b = f.readInt ();
	x = 0;
	y = 0;
	while (!(a == 0 && b == 0))
	{
	    x += a;
	    y += b;
	    if (x < 0)
		x = 0;
	    else if (x > c)
		x = c;
	    if (y < 0)
		y = 0;
	    else if (y > r)
		y = r;
	    cc.println (x + " " + y);
	    a = f.readInt ();
	    b = f.readInt ();
	}
    }
}
