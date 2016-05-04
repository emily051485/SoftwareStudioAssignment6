package main.java;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String[] file = 
	{
			"starwars-episode-1-interactions.json",
			"starwars-episode-2-interactions.json",
			"starwars-episode-3-interactions.json",
			"starwars-episode-4-interactions.json",
			"starwars-episode-5-interactions.json",
			"starwars-episode-6-interactions.json",
			"starwars-episode-7-interactions.json"
	};
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private Network network = new Network(this);
	private Character curCh;
	private Character hoveringCh;
	private int whichfile = 0;
	private final static int width = 1200, height = 650;
	
	public void setup() 
	{	
		Ani.init(this);
		characters = new ArrayList<Character>();
		size(width, height);
		smooth();
		loadData();
	}

	public void draw() 
	{
		background(255);
		/*
		for(int i=0;i<characters.size();i++){
			for(int j=0;j<characters.get(i).getTargets().size();j++){
				line(characters.get(i).x,characters.get(i).y,characters.get(i).getTargets().get(j).x,characters.get(i).getTargets().get(j).y);
			}
		}
		*/
		network.display();
		for(int i=0;i<characters.size();i++)
		{
			characters.get(i).display();
		}
		
		if(hoveringCh != null)
		{
			hoveringCh.showName();
		}
	}

	public void mousePressed()
	{
		for(int i=0;i<characters.size();i++)
		{
			if(mouseX >= characters.get(i).getX()-30 && mouseX <= characters.get(i).getX()+30)
			{
				if(mouseY >= characters.get(i).getY()-30 && mouseY <= characters.get(i).getY()+30)
				{
					curCh = characters.get(i);
				}
			}
		}
	}
	
	public void mouseDragged()
	{
		if(curCh != null)
		{
			curCh.setPosition(mouseX,  mouseY);
		}
	}
	
	public void mouseReleased()				//  700 300 400 400
	{
		if(curCh != null)
		{	
			if( (curCh.getX()-700)*(curCh.getX()-700)+(curCh.getY()-300)*(curCh.getY()-300) < 40000)
			{
				network.addch(curCh);
			}
			else
			{
				network.removech(curCh);
				curCh.initial();
				curCh = null;
			}
		}
	}
	
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode()>=49 && arg0.getKeyCode() < 56)
		{
			whichfile = arg0.getKeyCode()-49;
			network.clean();
			characters.clear();
			loadData();
		}
	}

	public void mouseMoved()
	{
		for(int i=0;i<characters.size();i++)
		{
			if( (characters.get(i).getX()-mouseX)*(characters.get(i).getX()-mouseX)+(characters.get(i).getY()-mouseY)*(characters.get(i).getY()-mouseY) < 1600)
			{
				hoveringCh = characters.get(i);
			}
			else
			{
				if(mouseX >= 250)
					hoveringCh = null;
			}
		}
	}
	
	private void loadData()
	{
		data = loadJSONObject(this.path + this.file[whichfile]);
		nodes = data.getJSONArray("nodes");
		
		for (int i = 0; i < nodes.size(); i++) 
		{
			JSONObject character = nodes.getJSONObject(i);
			String name = character.getString("name");
			String color = character.getString("colour");
			//int value = character.getInt("value");
			Character ch = new Character(this,name,i,color);
			characters.add(ch);
		}
		
		links = data.getJSONArray("links");
		
		for (int i = 0; i < links.size(); i++) 
		{
			JSONObject character = links.getJSONObject(i);
			int source = character.getInt("source");
			int target = character.getInt("target");
			int value = character.getInt("value");
			characters.get(source).addTarget(characters.get(target),value);
		}
	}
}
