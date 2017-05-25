import java.util.ArrayList;

import processing.core.PApplet;

public class Meteors extends PApplet{
	public static void main(String[] args) {	//Run as processing PApplet
		PApplet.main("Meteors");
    }
	
	
	
	//Processing code:
	Spaceship ship;
	ArrayList<Meteor> meteors;
	ArrayList<Bullet> bullets;
	ArrayList<StarLine> starLines;
	
	public void settings(){
		size(1080,720,P3D);
	}
	
	public void setup(){
		frameRate(60);
		
		ship=new Spaceship(this);
		meteors=new ArrayList<Meteor>();
		bullets=new ArrayList<Bullet>();
		starLines=new ArrayList<StarLine>();
	}
	
	public void draw(){
		background(0);
		lights();
		createMeteors();
		createStarLines();
		
		//Render Ship
		ship.render();
		
		//Render and remove Meteors
		for(int i=meteors.size()-1; i>=0; i--){
			Meteor meteor=meteors.get(i);
			if(meteor.getZ()>-meteor.SCALE){
				meteors.remove(i).render();
			}
			else meteor.render();
		}
		
		//Render and remove Bullets
		for(int i=bullets.size()-1; i>=0; i--){
			Bullet bullet=bullets.get(i);
			if(bullet.getZ()<-2048){
				bullets.remove(i).render();
			}
			else bullet.render();
		}
		
		//Render and remove Star Lines
		for(int i=starLines.size()-1; i>=0; i--){
			StarLine star=starLines.get(i);
			if(star.getZ()>200){
				starLines.remove(i).render();
			}
			else star.render();
		}
		
	}
	
	public void createMeteors(){
		if(frameCount%60==0)
			meteors.add(new Meteor(this, random(50,width-50), random(50,height-50)));
	}
	
	public void createStarLines(){
		starLines.add(new StarLine(this));
	}
	
	public void fireBullet(){
		bullets.add(ship.fire());
	}

	public void keyPressed(){
		if(keyCode==UP || key=='w') ship.moveUp();
		if(keyCode==DOWN || key=='s') ship.moveDown();
		if(keyCode==LEFT || key=='a') ship.moveLeft();
		if(keyCode==RIGHT || key=='d') ship.moveRight();
		if(keyCode==ENTER || key==' ') fireBullet();  
	}
	
	public void keyReleased(){
		if(keyCode==UP || key=='w') ship.haltUp();
		if(keyCode==DOWN || key=='s') ship.haltDown();
		if(keyCode==LEFT || key=='a') ship.haltLeft();
		if(keyCode==RIGHT || key=='d') ship.haltRight();
	}
}