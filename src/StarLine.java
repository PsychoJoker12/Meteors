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
import processing.core.PApplet;

public class StarLine extends Obj{
	
	public StarLine(PApplet p){
		super(p, p.random(-p.width,2*p.width), p.random(-p.height,2*p.height), -2048);
	}
	
	public void move(){
		setZ(getZ()+50);
	}
	
	public void render(){	//TODO make them spawn not in the play area
		move();
		
		p.fill(255);
		p.stroke(255);
		
		p.pushMatrix();
		p.translate(getX(), getY(), getZ());
		p.box(1,1,10);
		p.popMatrix();
	}
}
