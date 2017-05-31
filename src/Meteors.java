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
import processing.core.PFont;

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
	int gameState;
	int score=0;
	int highScore=0;
	PFont helvetica;
	
	public void settings(){
		size(1080,720,P3D);
	}
	
	public void setup(){
		frameRate(60);
		helvetica=createFont("Helvetica",72,true);
		
		gameState=-1;
		score=0;
		ship=new Spaceship(this);
		meteors=new ArrayList<Meteor>();
		bullets=new ArrayList<Bullet>();
		starLines=new ArrayList<StarLine>();
		
		
	}
	
	public void draw(){
		if(gameState==-1){	//Menu Screen
			menu();
		}
		else if(gameState==0){	//Pause Screen
			pause();
		}
		else if(gameState==1){	//Game running
			background(0);
			lights();
			createMeteors();
			createStarLines();
			
			//Render Ship
			ship.render();
			
			cycleMeteors();
			renderStarLines();
			cycleBullets();
			
			displayScore();
		}
		else if(gameState==2){	//End game
			endGame();
		}
	}
	
	public void menu(){
		background(0);
		lights();
		createStarLines();
		renderStarLines();
		
		textFont(helvetica);
		fill(255);
		
		textAlign(CENTER);
		text("METEORS",width/2,3*height/8,0);
		
		playButton();
	}
	
	public void playButton(){
		playButtonColor();
		textFont(helvetica,48);
		text("Play",width/2,height/2,0);
	}
	
	public String playButtonColor(){
		if(mouseX<width/2+96 && mouseX>width/2-96 && mouseY<height/2+12 && mouseY>height/2+12-48){
			fill(255,255,0);
			return "Yellow";
		}
		else{
			fill(255);
			return "White";
		}
	}
	
	public void pause(){
		rectMode(CENTER);
		fill(255);
		stroke(0);
		
		pushMatrix();
		translate(width/2-48,height/2,0);
		rect(0,0,24,96);
		translate(72,0,0);
		rect(0,0,24,96);
		popMatrix();
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
		else if(frameCount<3600){
			if(frameCount%20==0){
				meteors.add(new Meteor(this, random(50,width-50), random(50,height-50)));
			}
		}
		else{
			if(frameCount%10==0){
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
			if(meteor.getZ()>0-ship.SCALE) gameState=2;
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
	
	public void displayScore(){
		
	}
	
	//Ends the game
	void endGame(){
		//TODO to be implemented
	}

	public void keyPressed(){
		if(gameState==1){
			if(keyCode==UP || key=='w') ship.moveUp();
			if(keyCode==DOWN || key=='s') ship.moveDown();
			if(keyCode==LEFT || key=='a') ship.moveLeft();
			if(keyCode==RIGHT || key=='d') ship.moveRight();
			if(keyCode==ENTER || key==' ') fireBullet();
			if(key==ESC){
				key=0;
				if(gameState==1) gameState=0;
				else if(gameState==0) gameState=1;
			}
		}
	}
	
	public void keyReleased(){
		if(gameState==1){
			if(keyCode==UP || key=='w') ship.haltUp();
			if(keyCode==DOWN || key=='s') ship.haltDown();
			if(keyCode==LEFT || key=='a') ship.haltLeft();
			if(keyCode==RIGHT || key=='d') ship.haltRight();
			if(key==ESC) key=0;
		}
	}
	
	public void mousePressed(){
		if(gameState==-1 && playButtonColor().equals("Yellow")) gameState=1;	//Press the play button
		if(gameState==1) fireBullet();
	}
}