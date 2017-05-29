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
		
		//RenderMeteors
		for(int i=meteors.size()-1; i>=0; i--){
			meteors.get(i).render();
		}
		
		//Cycling though Bullets
		for(int i=bullets.size()-1; i>=0; i--){
			Bullet bullet=bullets.get(i);
			int size=bullets.size();
			
			//Remove and render Bullets
			if(bullet.getZ()<-2048){
				bullets.remove(i).render();
			}
			else bullet.render();
			
			//Remove Meteors
			for(int j=0; j<meteors.size(); j++){
				if(size==bullets.size()){
					if(bullet.checkCollision(meteors.get(j))){
						meteors.remove(j);
						j--;
						bullets.remove(i);
					}
				}
			}
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