package main.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
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
	private Character curCh;
	private Character hoveringCh;
	private int whichfile = 0;
	private final static int width = 1200, height = 650;
	private Button addall, clear;
	private float bigcircle_x = 700;
	private float bigcircle_y = 300;
	private float radius = 200;
	
	public void setup() 
	{	
		Ani.init(this);
		this.setLayout(null);
		characters = new ArrayList<Character>();
		size(width, height);
		smooth();
		loadData();
		addall = new Button("Add All");
		clear = new Button("Clear");
		addall.setForeground(Color.WHITE);
		addall.setBackground(Color.green);
		addall.setBounds(1000, 100, 100, 50);
		addall.setFont(new Font("Consolas", Font.BOLD, 25));
		addall.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				for(int i=0;i<characters.size();i++)
				{
					if(characters.get(i).isInCircle() == 0)
					{
						characters.get(i).setIncircle();
					}
					else
					{
						//updatelocation();
					}
				}
				updatelocation();
			}
		});
		add(addall);
		clear.setForeground(Color.WHITE);
		clear.setBackground(Color.green);
		clear.setBounds(1000, 200, 100, 50);
		clear.setFont(new Font("Consolas", Font.BOLD, 25));
		clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				for(int i=0;i<characters.size();i++)
				{
					if(characters.get(i).isInCircle() == 1)
					{
						characters.get(i).initial();
						characters.get(i).setNotCircle();
						//network.removech(characters.get(i));
					}
				}
				//updatelocation();
			}
		});
		add(clear);
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
		
		//System.out.println(hoveringCh.getName());
		if(hoveringCh != null)
		{
			hoveringCh.showName();
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
						bezier(characters.get(i).getX(), characters.get(i).getY(), 700, 300, 700, 300, characters.get(i).getTargets().get(j).getX(), characters.get(i).getTargets().get(j).getY());
						strokeWeight(1);
					}
				}
			}
		}
	}
	
	public void display()
	{
		noFill();
		ellipse(bigcircle_x, bigcircle_y, 2*radius, 2*radius);	
	}
	
	public void updatelocation()
	{
		int j = 0;
		System.out.println(numberInCircle());
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
		}
	}
	
	public void mouseReleased()				//  700 300 400 400
	{
		if(curCh != null)
		{	
			if( (curCh.getX()-700)*(curCh.getX()-700)+(curCh.getY()-300)*(curCh.getY()-300) < 40000)
			{
				if(curCh.isInCircle() == 0)
				{
					curCh.setIncircle();
					updatelocation();
				}
				else
				{
					//updatelocation();
				}
			}
			else
			{
				curCh.setNotCircle();
				curCh.initial();
				curCh = null;
			}
		}
	}
	
	public void keyPressed(KeyEvent arg0)
	{
		//System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() >= 49 && arg0.getKeyCode() < 56)
		{
			whichfile = arg0.getKeyCode()-49;
			characters.clear();
			loadData();
		}
	}

	public void mouseMoved()
	{
		for(int i=0;i<characters.size();i++)
		{
			if( (characters.get(i).getX()-mouseX)*(characters.get(i).getX()-mouseX)+(characters.get(i).getY()-mouseY)*(characters.get(i).getY()-mouseY) < 400)
			{
				hoveringCh = characters.get(i);
			}
			else
			{
				hoveringCh = null;
			}
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
