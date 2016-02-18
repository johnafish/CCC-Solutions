// S2 2010: Huffman Encoding
//
// arrays, searching and string handling
// quite straight forward.
//

import java.awt.*;
import hsa.*;

public class S22010
{

    public static void main (String[] args)
    {
	TextInputFile c;
	c = new TextInputFile ("s2.6.in");

	int k, i;
	char[] letter = new char [20];
	String[] code = new String [20];
	String binary;
	String answer;

	// input section
	k = c.readInt ();
	for (i = 0 ; i < k ; i++)
	{
	    letter [i] = c.readChar ();
	    code [i] = c.readString ();
	}
	binary = c.readString ();

	//Translate
	answer = "";
	while (binary.length () > 0)
	{
	    // simple search as one code MUST be found
	    i = 0;
	    while (!binary.startsWith (code [i]))
		i++;

	    // add letter to answer and remove that code from binary
	    answer = answer + letter [i];
	    binary = binary.substring (code [i].length ());
	}

	System.out.println (answer);
    }
}
