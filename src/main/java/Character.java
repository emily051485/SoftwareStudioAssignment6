package main.java;

import java.util.ArrayList;
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
	
	
	public Character(MainApplet parent, String name, float x, float y, float radius)
	{
		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public void display()
	{
		this.parent.fill(100,100,235);
		this.parent.ellipse(x, y, 30, 30);
		this.parent.fill(0);
		this.parent.text(name, x+10, y+10);
	}
	
	public void addTarget(Character target)
	{
		targets.add(target);
	}
	
	public ArrayList<Character> getTargets()
	{
		return this.targets;
	}
}
