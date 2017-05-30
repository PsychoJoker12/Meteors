/* 	Final project "Meteors", AP Computer Science
 * 
 *  Copyright (C) 2017  Robert Ciliberto
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
		
		cycleMeteors();
		renderStarLines();
		cycleBullets();
	}
	
	//Adds a new Meteor object to the ArrayList every second
	public void createMeteors(){
		if(frameCount%60==0)
			meteors.add(new Meteor(this, random(50,width-50), random(50,height-50)));
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
						meteors.remove(j);
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