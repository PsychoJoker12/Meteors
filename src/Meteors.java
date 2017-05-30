import java.util.ArrayList;

import processing.core.PApplet;

public class Meteors extends PApplet{
	public static void main(String[] args) {	//Run as processing PApplet
		PApplet.main("Meteors");
    }
	
	
	
	/*********************
	 ** Processing code **
	 *********************/
	Spaceship ship;
	ArrayList<Meteor> meteors;
	ArrayList<Bullet> bullets;
	ArrayList<StarLine> starLines;
	boolean paused;
	int score=0;
	int highScore=0;
	
	public void settings(){
		size(1080,720,P3D);
	}
	
	public void setup(){
		frameRate(60);
		
		paused=false;
		score=0;
		ship=new Spaceship(this);
		meteors=new ArrayList<Meteor>();
		bullets=new ArrayList<Bullet>();
		starLines=new ArrayList<StarLine>();
	}
	
	public void draw(){
		if(!paused){
			background(0);
			lights();
			createMeteors();
			createStarLines();
			
			//Render Ship
			ship.render();
			
			cycleMeteors();
			renderStarLines();
			cycleBullets();
		}
	}
	
	//Adds a new Meteor object to the ArrayList
	public void createMeteors(){
		if(frameCount<900){
			if(frameCount%60==0){
				meteors.add(new Meteor(this, random(50,width-50), random(50,height-50)));
			}
		}
		else if(frameCount<1800){
			if(frameCount%45==0){
				meteors.add(new Meteor(this, random(50,width-50), random(50,height-50)));
			}
		}
		else if(frameCount<2700){
			if(frameCount%30==0){
				meteors.add(new Meteor(this, random(50,width-50), random(50,height-50)));
			}
		}
		else{
			if(frameCount%30==0){
				meteors.add(new Meteor(this, random(50,width-50), random(50,height-50)));
			}
		}
	}
	
	//Removes meteor and adds to the score
	public void removeMeteor(int index){
		meteors.remove(index);
		score+=10;
	}
	
	//Adds a new StarLine object to the ArrayList
	public void createStarLines(){
		starLines.add(new StarLine(this));
	}
	
	//Adds a new Bullet object to the ArrayList
	public void fireBullet(){
		bullets.add(ship.fire());
	}
	
	//Cycles through the Meteors ArrayList and does various actions
	public void cycleMeteors(){
		for(int i=meteors.size()-1; i>=0; i--){
			Meteor meteor = meteors.get(i);
			
			//Render Meteors
			meteor.render();
			
			//End the game if the meteor hits the screen
			if(meteor.getZ()>0-ship.SCALE) endGame();
		}
	}
	
	//Renders and removes StarLines once past the screen
	public void renderStarLines(){
		for(int i=starLines.size()-1; i>=0; i--){
			StarLine star=starLines.get(i);
			if(star.getZ()>200){
				starLines.remove(i).render();
			}
			else star.render();
		}
	}
	
	//Cycles through Bullets ArrayList and does various actions
	public void cycleBullets(){
		for(int i=bullets.size()-1; i>=0; i--){
			Bullet bullet=bullets.get(i);
			int size=bullets.size();
			
			//Remove and render Bullets
			if(bullet.getZ()<-2048){
				bullets.remove(i).render();
			}
			else bullet.render();
			
			//Remove Meteors and bullet if colliding 
			for(int j=0; j<meteors.size(); j++){
				if(size==bullets.size()){
					if(bullet.checkCollision(meteors.get(j))){
						removeMeteor(j);
						j--;
						bullets.remove(i);
					}
				}
			}
		}
	}
	
	//Ends the game
	void endGame(){
		//TODO to be implemented
	}

	public void keyPressed(){
		if(!paused){
			if(keyCode==UP || key=='w') ship.moveUp();
			if(keyCode==DOWN || key=='s') ship.moveDown();
			if(keyCode==LEFT || key=='a') ship.moveLeft();
			if(keyCode==RIGHT || key=='d') ship.moveRight();
			if(keyCode==ENTER || key==' ') fireBullet(); 
		}
	}
	
	public void keyReleased(){
		if(!paused){
			if(keyCode==UP || key=='w') ship.haltUp();
			if(keyCode==DOWN || key=='s') ship.haltDown();
			if(keyCode==LEFT || key=='a') ship.haltLeft();
			if(keyCode==RIGHT || key=='d') ship.haltRight();
		}
	}
	
	public void mousePressed(){
		if(!paused) fireBullet();
	}
}