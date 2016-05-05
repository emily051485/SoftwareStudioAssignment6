package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import de.looksgood.ani.Ani;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character 
{	
	public float x, y, radius;
	private String name;
	private MainApplet parent;
	private ArrayList<Character> targets = new ArrayList<Character>();
	private HashMap<String, Integer> values = new HashMap<String, Integer>();
	@SuppressWarnings("unused")
	private int order, a, r, g, b;
	private int inCircle = 0;
	
	public Character(MainApplet parent, String name, int order, String color)
	{
		this.parent = parent;
		this.name = name;				// the characters' name
		this.order = order;				// the id of characters, be used to sort them
		this.x = 30 + (order/10)*60;	// sort the position of each nodes
		this.y = 30 + (order%10)*60;
		this.radius = 40;
		a = Integer.valueOf( color.substring( 1, 3 ), 16 );		// decode the argb codes
        r = Integer.valueOf( color.substring( 3, 5 ), 16 );
        g = Integer.valueOf( color.substring( 5, 7 ), 16 );
        b = Integer.valueOf( color.substring( 7, 9 ), 16 );
	}

	public void display()
	{
		this.parent.fill(r, g, b);
		this.parent.strokeWeight(1);
		this.parent.ellipse(x, y, radius, radius);
		this.parent.strokeWeight(3);
	}
	
	public void addTarget(Character target, int value)		// add targets of this characters
	{
		values.put(target.getName(), value);
		targets.add(target);
	}
	
	public ArrayList<Character> getTargets()
	{
		return this.targets;
	}
	
	public String getName()			// return this characters' name
	{
		return this.name;
	}
	
	public int getvalue(Character tar)		// get the target's value and we can set the edges
	{
		return values.get(tar.getName());
	}
	
	public float getX()		// get the x-axis of this characters
	{
		return this.x;
	}
	
	public float getY()		// get the y-axis of this characters
	{
		return this.y;
	}
	
	public void initial()	// return the nodes to the initial position
	{
		Ani.to(this, (float) 0.5, "x", 30+(order/10)*60); 
		Ani.to(this, (float) 0.5, "y", 30+(order%10)*60);
	}
	
	public void setPosition(float x, float y)		// set this node's position
	{
		this.x = x;
		this.y = y;
	}
	
	public void setIncircle()		// mark that this node is in the big circle
	{
		this.inCircle = 1;
	}
	
	public void setNotCircle()		// mark that this node is not in the big circle
	{
		this.inCircle = 0;
	}
	
	public int isInCircle()			// check that this node is in the big circle or not
	{
		return this.inCircle;
	}
	
	public void setRadius(int r)	// set the radius of the nodes
	{
		this.radius = r;
	}
}
