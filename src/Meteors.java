import java.util.ArrayList;

import processing.core.PApplet;

public class Meteors extends PApplet{
	public static void main(String[] args) {	//Run as processing PApplet
		PApplet.main("Meteors");
    }
	
	
	
	//Processing code:
	Spaceship ship;
	ArrayList<Obj> objects;
	
	public void settings(){
		size(1080,720,P3D);
	}
	
	public void setup(){
		frameRate(60);
		
		ship=new Spaceship(this);
		objects=new ArrayList<Obj>();
		objects.add(ship);
	}
	
	public void draw(){
		background(0);
		lights();
		createMeteors();
		createStarLines();
		
		for(int i=objects.size()-1; i>=0; i--){
			Obj obj=objects.get(i);
			//Render objects
			obj.render();
			
			//Remove Bullets
			if(obj instanceof Bullet){
				if(obj.getZ()<-1024){
					objects.remove(i);
				}
			}
			
			//Remove Meteors
			if(obj instanceof Meteor){
				if(obj.getZ()>-100){
					objects.remove(i);
				}
			}
			//Remove StarLines
			if(obj instanceof StarLine){
				if(obj.getZ()>200){
					objects.remove(i);
				}
			}
		}
	}
	
	public void createMeteors(){
		if(frameCount%60==0)
			objects.add(new Meteor(this, random(50,width-50), random(50,height-50)));
	}
	
	public void createStarLines(){
		if(frameCount%5==0)
			objects.add(new StarLine(this));
	}
	
	public void fireBullet(){
		objects.add(ship.fire());
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
