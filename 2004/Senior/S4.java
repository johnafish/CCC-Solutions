// CCC 2004
// Problem S4 Space Turtle
//
// This algoritm is due to Richard Peng.
//
// The idea is: given the position of a space turtle and a
// golden shell, determine the closest the turtle comes to the shell.
// (The turtle moves forward than turns left, right up and down.)
//
// Peng's insight is to ALWAYS think of the turtle at 0,0,0.
// This puts the shell at x,y,z.
//
// As an example lets think only in 2d: assume the shell is at (5,2)
// moving forward 2 is the same as moving the shell to (3,2)
// that is, each movement is: x = x - d
//
// Now here's Peng's second great insight: the closest the turtle comes
// to the shell will be at the ends points of the movement, OR if he zooms by
// so to speak, its at x = 0. EG: if the shell is at (5,2) and the turtle moves 8
// and the new shell location is (-3,2) the closest the turtle was, was
// 2. At one point the shell was at (0,2)
// in 3D terms if the new x and x are different signs, the closest distance
// is sqrt(y^2 + z^2) versus the normal sqrt(x^2+ y^2 + z^2)
//
// Turning is a bit of a mind bending exercise, but again not so bad
// if you imagine it in only 2D: if the shell is at (5,2) and
// the turtle turns left the shell will be at (2,-5)
// that is (x,y,z) becomes (y,-x,z) (z is unaffected by a left or right turn)
// DRAW a grid, plot the point, and you'll see!
// similarly right means (x,y,z) becomes (-y,x,z)
//           down  means (x,y,z) becomes (-z,y,x) (y is unaffected by down/up)
//       and up    means (x,y,z) becomes (z,y,-x)

import hsa.*;

public class S4SpaceTurtlePeng
{
    static Console c;
    static double tx, ty, tz;
    static double sx, sy, sz;
    static double x, y, z, newX, t;

    public static void main (String[] args)
    {
	c = new Console ();
	double closest, distance, d;
	char turn;
	String file;

	c.print ("file name: ");
	file = c.readString ();
	TextInputFile fi = new TextInputFile (file);

	tx = fi.readDouble (); // turtle coordinates
	ty = fi.readDouble ();
	tz = fi.readDouble ();

	sx = fi.readDouble (); // shell coordinates
	sy = fi.readDouble ();
	sz = fi.readDouble ();

	x = sx - tx;
	y = sy - ty;
	z = sz - tz;

	closest = x * x + y * y + z * z;
	do
	{
	    distance = fi.readDouble ();
	    turn = fi.readChar ();

	    newX = x - distance;

	    if (newX * x < 0)
		closest = Math.min (closest, y * y + z * z);
	    else
		closest = Math.min (closest, newX * newX + y * y + z * z);
	    x = newX;
	    t = x;
	    if (turn == 'L')
	    {
		x = y;
		y = -t;
	    }
	    else if (turn == 'R')
	    {
		x = -y;
		y = t;
	    }
	    else if (turn == 'U')
	    {
		x = z;
		z = -t;
	    }
	    else
	    {
		x = -z;
		z = t;
	    }
	}
	while (turn != 'E');
	c.println ((int) ((Math.sqrt (closest) * 100) + 0.5) / 100.0);
    }
}
