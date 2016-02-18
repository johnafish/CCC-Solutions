// CCC2006s2CipherTextsv2
//
// given a plain and cipher text lines
// decode a second ciphertext line.
// (simple substitution encryption used)
//
// Basic string handling
//
// There is a catch however: if 26 or the 27 characters
// are defined, the 27th is to be inferred. Thus special
// decoding strings need to be used.


import java.awt.*;
import hsa.*;

public class CCC2006s2CipherTextsv2
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	TextInputFile f = new TextInputFile ("s2.5.in");
	String plain, c1, c2;
	String decode = "...........................";
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	int x;
	int count, dot;

	plain = f.readLine ();
	c1 = f.readLine ();
	c2 = f.readLine ();
	
	// fill decode
	for (int i = 0 ; i < c1.length () ; i++)
	{
	    x = characters.indexOf (c1.charAt (i));
	    decode = decode.substring (0, x) + plain.charAt (i) + decode.substring (x + 1);
	}
	// if only one '.' in decode, its value can be inferred
	count = 0;
	for (int i = 0 ; i < decode.length () ; i++)
	    if (decode.charAt (i) == '.')
		count++;
	if (count == 1)
	{
	    dot = decode.indexOf ('.');
	    for (int i = 0 ; i < 27 ; i++)
	    {
		x = decode.indexOf (characters.charAt (i));
		if (x == -1)
		    decode = decode.substring (0, dot) + characters.charAt (i) + decode.substring (dot + 1);
	    }
	}
	
	// decrypt the ciphertext
	for (int i = 0 ; i < c2.length () ; i++)
	{
	    x = characters.indexOf (c2.charAt (i));
	    c.print (decode.charAt (x));
	}
    }
}


