package main.java;

import java.util.ArrayList;

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
	private String file = "starwars-episode-1-interactions.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private final static int width = 1200, height = 650;
	
	public void setup() {
		
		characters = new ArrayList<Character>();
		size(width, height);
		smooth();
		loadData();
		
	}

	public void draw() {

	}

	private void loadData(){
		data = loadJSONObject(this.path+this.file);
		nodes = data.getJSONArray("nodes");
		
		for (int i = 0; i < nodes.size(); i++) {
			
			
			JSONObject character = nodes.getJSONObject(i);
			
			String name = character.getString("name");
			String color = character.getString("colour");
			//int value = character.getInt("value");
			//MainApplet parent, String name, float x, float y, float radius
			Character ch = new Character(this,name,i,color);
			characters.add(ch);
			//System.out.println(id + ", " + species + ", " + name);
		}
		
		
		
		links = data.getJSONArray("links");
		
		for (int i = 0; i < links.size(); i++) {
			
			
			JSONObject character = links.getJSONObject(i);
			
			int source = character.getInt("source");
			int target = character.getInt("target");
			int value = character.getInt("value");
			
			characters.get(source).addTarget(characters.get(target),value);
			//System.out.println(id + ", " + species + ", " + name);
		}
	}

}
