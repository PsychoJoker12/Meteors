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
import processing.core.PConstants;
import processing.core.PShape;

public class Meteor extends Obj {
	PShape meteor;
	final float ROT;
	final float SCALE;
	
	public Meteor(PApplet p, float x, float y) {
		super(p, x, y, -1024);
		meteor=p.loadShape("models\\meteors\\Meteor1.obj");
		
		ROT=p.random(-PConstants.PI/64,PConstants.PI/64);
		SCALE=100;
		
		setXRot(p.random(-PConstants.PI/2,PConstants.PI/2));
		setYRot(p.random(-PConstants.PI/2,PConstants.PI/2));
		setZRot(p.random(-PConstants.PI/2,PConstants.PI/2));
	}

	private void move(){
		setZ(getZ()+1);
	}
	
	private void rotate(){
		setZRot(getZRot()+ROT);
	}
		
	@Override
	public void render() {
		move();
		rotate();
		
		p.pushMatrix();
		p.translate(getX(), getY(), getZ());
		p.rotateX(getXRot());
		p.rotateY(getYRot());
		p.rotateZ(getZRot());
		p.scale(SCALE);
		p.shape(meteor);
		p.popMatrix();		
	}

}
