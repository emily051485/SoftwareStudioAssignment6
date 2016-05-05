package main.java;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import controlP5.ControlP5;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PFont;
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
	private Character curCh;
	private int whichfile = 0;
	private final static int width = 1200, height = 650;
	private float bigcircle_x = 600;
	private float bigcircle_y = 400;
	private float radius = 200;
	private int setThick = 0;
	private ControlP5 cp5;
	private String title = "Star Wars " + (whichfile+1);
	Minim minim; 
	AudioPlayer song; 
	
	public void setup() 
	{	
		Ani.init(this);
		this.setLayout(null);
		characters = new ArrayList<Character>();
		size(width, height);
		smooth();
		loadData();
		cp5 = new ControlP5(this);
		PFont p = createFont("Consolas", 25);
		cp5.setFont(p);
		cp5.addButton("buttonA").setLabel("Add All").setPosition(width-240, 100).setSize(200, 50); 
		cp5.addButton("buttonB").setLabel("Clear").setPosition(width-240, 180).setSize(200, 50);
		minim = new Minim(this);
		song = minim.loadFile("song.mp3"); 
		song.play();
		
	}
	
	public void buttonA()
	{
		for(int i=0;i<characters.size();i++)
		{
			if(characters.get(i).isInCircle() == 0)
			{
				characters.get(i).setIncircle();
			}
		}
		updatelocation();
	}
	
	public void buttonB()
	{
		for(int i=0;i<characters.size();i++)
		{
			if(characters.get(i).isInCircle() == 1)
			{
				characters.get(i).initial();
				characters.get(i).setNotCircle();
			}
		}
	}

	public void draw()
	{
		background(255);
		
		display();
		drawline();
		for(int i=0;i<characters.size();i++)
		{
			characters.get(i).display();
		}
		
		textSize(30);
		text(title, 515, 80);
		textSize(20);
		
		for(int i=0;i<characters.size();i++)
		{
			if( (characters.get(i).getX()-mouseX)*(characters.get(i).getX()-mouseX)+(characters.get(i).getY()-mouseY)*(characters.get(i).getY()-mouseY) < 400)
			{
				fill(0, 200, 0);
				strokeWeight(1);
				rect(characters.get(i).getX()+30, characters.get(i).getY()-25, characters.get(i).getName().length()*16, 35);
				fill(255);
				text(characters.get(i).getName(), characters.get(i).getX()+35, characters.get(i).getY());
				strokeWeight(3);
			}
		}
		
		for(int i=0;i<characters.size();i++)
		{
			if( (characters.get(i).getX()-mouseX)*(characters.get(i).getX()-mouseX)+(characters.get(i).getY()-mouseY)*(characters.get(i).getY()-mouseY) < 400)
			{
				characters.get(i).setRadius(50);
			}
			else
			{
				characters.get(i).setRadius(40);
			}
		}
		
		
	}
	
	public void drawline()
	{
		for(int i=0;i<characters.size();i++)
		{
			if(characters.get(i).isInCircle() == 1)
			{
				for(int j=0;j<characters.get(i).getTargets().size();j++)
				{
					if(characters.get(i).getTargets().get(j).isInCircle() == 1)
					{
						strokeWeight(characters.get(i).getvalue(characters.get(i).getTargets().get(j))/5+1);
						bezier(characters.get(i).getX(), characters.get(i).getY(), 600, 400, 600, 400, characters.get(i).getTargets().get(j).getX(), characters.get(i).getTargets().get(j).getY());
						strokeWeight(1);
					}
				}
			}
		}
	}
	
	public void display()
	{
		noFill();
		stroke(100, 100, 255);
		if(setThick == 1)
		{
			strokeWeight(8);
		}
		ellipse(bigcircle_x, bigcircle_y, 2*radius, 2*radius);
		stroke(0, 0, 0);
		strokeWeight(3);
	}
	
	public void updatelocation()
	{
		int j = 0;
		for(int i=0;i<characters.size();i++)
		{
			if(characters.get(i).isInCircle() == 1)
			{
				float x = bigcircle_x + radius*(float)Math.cos(j*2*Math.PI/numberInCircle());
				float y = bigcircle_y + radius*(float)Math.sin(j*2*Math.PI/numberInCircle());
				characters.get(i).setPosition(x,y);
				j++;
			}
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
			if((curCh.getX()-700)*(curCh.getX()-700)+(curCh.getY()-300)*(curCh.getY()-300) < 40000)
			{
				this.setThick = 1;
			}
			else
			{
				this.setThick = 0;
			}
		}
	}
	
	public void mouseReleased()				//  700 300 400 400
	{
		if(curCh != null)
		{	
			if( (curCh.getX()-600)*(curCh.getX()-600)+(curCh.getY()-400)*(curCh.getY()-400) < 40000)
			{
				if(curCh.isInCircle() == 0)
				{
					curCh.setIncircle();
					updatelocation();
					this.setThick = 0;
					curCh = null;
				}
				else
				{
					updatelocation();
					this.setThick = 0;
					curCh = null;
				}
			}
			else
			{
				curCh.setNotCircle();
				curCh.initial();
				updatelocation();
				curCh = null;
			}
		}
	}
	
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() >= 49 && arg0.getKeyCode() < 56)
		{
			whichfile = arg0.getKeyCode()-49;
			for(int i=0;i<characters.size();i++)
			{
				characters.get(i).setNotCircle();
			}
			loadData();
			title = "Star Wars " + (whichfile+1);
		}
	}
	
	public int numberInCircle()
	{
		int numbersInCircle = 0;
		for(int i=0;i<characters.size();i++)
		{
			if(characters.get(i).isInCircle() == 1)
			{
				numbersInCircle += 1;
			}
		}
		return numbersInCircle;
	}
	
	private void loadData()
	{
		characters.clear();
		data = loadJSONObject(this.path + this.file[whichfile]);
		nodes = data.getJSONArray("nodes");
		
		for (int i = 0; i < nodes.size(); i++) 
		{
			JSONObject character = nodes.getJSONObject(i);
			String name = character.getString("name");
			String color = character.getString("colour");
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
