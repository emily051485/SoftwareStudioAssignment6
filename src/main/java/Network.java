package main.java;

import java.util.ArrayList;
import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private MainApplet main;
	private ArrayList<Character> characters = new ArrayList<Character>();
	private float bigcircle_x = 700;
	private float bigcircle_y = 300;
	private float radius = 200;
	
	public Network(PApplet parent, MainApplet main)
	{
		this.parent = parent;	
		this.main = main;
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
		
		if(main.getIsClear() == 0)
		{
			for(int i=0;i<characters.size();i++)
			{
				for(int j=0;j<characters.get(i).getTargets().size();j++)
				{
					for(int k=0;k<characters.size();k++)
					{
						if(characters.get(i).getTargets().get(j).getName().equals(characters.get(k).getName()))
						{
							this.parent.strokeWeight(characters.get(i).getvalue(characters.get(i).getTargets().get(j))/5+1);
							this.parent.bezier(characters.get(i).getX(), characters.get(i).getY(), 700, 300, 700, 300, characters.get(i).getTargets().get(j).getX(), characters.get(i).getTargets().get(j).getY());
							this.parent.strokeWeight(1);
						}
					}
				}
			}
		}
	}
}
