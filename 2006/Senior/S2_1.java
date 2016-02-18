// CCC2006s2CipherTexts
//
// given a plain and cipher text lines
// decode a second ciphertext line.
// (simple substitution encryption used)
//
// Basic string handling

import java.awt.*;
import hsa.*;

public class CCC2006s2CipherTexts
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s2.5.in");
	String plain, c1,  c2;
	int x;

	plain = f.readLine ();
	c1 = f.readLine ();
	c2 = f.readLine ();
	for (int i = 0 ; i < c2.length() ; i++)
	{
	    x = c1.indexOf(c2.charAt(i));
	    if (x == -1)
		c.print (".");
	    else
		c.print (plain.charAt(x));
	}
    }
 
}


