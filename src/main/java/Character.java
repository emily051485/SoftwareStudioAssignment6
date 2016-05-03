package main.java;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;

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
	private int order, r, g, b;
	
	public Character(MainApplet parent, String name, int order, String color)
	{
		this.parent = parent;
		this.name = name;
		this.order = order;
		this.x = 10 + (order%10)*10;
		this.y = 10 + order*10;
		this.radius = 10;

		r = Integer.valueOf( color.substring( 0, 1 ), 16 );
        g = Integer.valueOf( color.substring( 2, 3 ), 16 );
        b = Integer.valueOf( color.substring( 4, 5 ), 16 );
        System.out.println(r + " " + g + " " + b);
	}

	public void display()
	{
		//this.parent.fill();
		this.parent.ellipse(x, y, radius, radius);;
		this.parent.fill(0);
		this.parent.text(name, x+10, y+10);
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
		return values.get(tar);
	}
}
