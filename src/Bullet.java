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

public class Bullet extends Obj{
	final float SPEED=100;
	
	public Bullet(PApplet p, Spaceship ship){
		super(p, ship.getX(), ship.getY(), ship.getZ());
	}
	
	private void move(){
		setZ(getZ()-SPEED);
	}
	
	public boolean checkCollision(Meteor m){
		if((getZ()<m.getZ()+m.SCALE) && (getX()>m.getX()-m.SCALE && getX()<m.getX()+m.SCALE) && (getY()>m.getY()-m.SCALE && getY()<m.getY()+m.SCALE)){
			return true;
		}
		return false;
	}
	
	@Override
	public void render(){
		move();
		
		p.stroke(200,0,0);
		p.fill(200);
		p.pushMatrix();
		p.translate(getX(),getY(),getZ());
		p.box(5,5,20);
		p.popMatrix();
	}
}
