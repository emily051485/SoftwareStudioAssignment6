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
		this.name = name;
		this.order = order;
		this.x = 30 + (order/10)*60;
		this.y = 30 + (order%10)*60;
		this.radius = 40;
		a = Integer.valueOf( color.substring( 1, 3 ), 16 );
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
	
	public void addTarget(Character target, int value)
	{
		values.put(target.getName(), value);
		targets.add(target);
	}
	
	public ArrayList<Character> getTargets()
	{
		return this.targets;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getvalue(Character tar)
	{
		return values.get(tar.getName());
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void initial()
	{
		Ani.to(this, (float) 0.5, "x", 30+(order/10)*60); 
		Ani.to(this, (float) 0.5, "y", 30+(order%10)*60);
	}
	
	public void showName()
	{
		this.parent.fill(0, 200, 0);
		this.parent.strokeWeight(1);
		this.parent.rect(x+20, y-15, name.length()*10, 25);
		this.parent.fill(255);
		this.parent.text(name, x+25, y);
		this.parent.strokeWeight(3);
		
	}
	
	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setIncircle()
	{
		this.inCircle = 1;
	}
	
	public void setNotCircle()
	{
		this.inCircle = 0;
	}
	
	public int isInCircle()
	{
		return this.inCircle;
	}
}
