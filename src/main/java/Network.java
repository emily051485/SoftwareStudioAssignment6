package main.java;

import java.awt.font.OpenType;
import java.time.Year;
import java.util.ArrayList;

import javax.management.openmbean.OpenDataException;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private ArrayList<Character> characters = new ArrayList<Character>();
	private float bigcircle_x = 700;
	private float bigcircle_y = 300;
	private float radius = 200;
	
	public Network(PApplet parent)
	{
		this.parent = parent;	
	}

	public void display()
	{
		this.parent.noFill();
		this.parent.ellipse(bigcircle_x, bigcircle_y, 2*radius, 2*radius);
	}
	
	public void updatelocation()
	{
		for(int i=0;i<characters.size();i++)
		{
			float x = bigcircle_x + radius*(float)Math.cos(i*2*Math.PI/characters.size());
			float y = bigcircle_y + radius*(float)Math.sin(i*2*Math.PI/characters.size());
			characters.get(i).setPosition(x,y);
		}
	}
	
	public void addch(Character ch)
	{
		characters.add(ch);
		updatelocation();
	}
	
	public void removech(Character ch)
	{
		characters.remove(ch);
		updatelocation();
		drawline();
	}
	
	public int checkMember(Character ch)
	{
		for(int i=0;i<characters.size();i++)
		{
			if(ch.getName().equals(characters.get(i).getName()))
			{
				return 1;
			}
		}
		return 0;
	}
	
	public void clean() 
	{
		characters.clear();
	}
	
	public void drawline()
	{
		this.parent.noFill();
		this.parent.arc(50.f, 50.f, 50.f, 50.f, 0.f, (float)(0.5*Math.PI));
		
	}
}
