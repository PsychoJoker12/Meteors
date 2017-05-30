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

public abstract class Obj extends ProcessingClass {
	private float x,y,z;
	private float xRot, yRot, zRot;
	
	public Obj(PApplet p, float x, float y, float z){
		super(p);
		this.x=x;
		this.y=y;
		this.z=z;
		
		xRot=0;
		yRot=0;
		zRot=0;
	}
		
	public void setX(float x){
		this.x=x;
	}
	public void setY(float y){
		this.y=y;
	}
	public void setZ(float z){
		this.z=z;
	}
	public void setXRot(float xRot){
		this.xRot=xRot;
	}
	public void setYRot(float yRot){
		this.yRot=yRot;
	}
	public void setZRot(float zRot){
		this.zRot=zRot;
	}
	
	
	public float getX(){
		return x;
	}  
	public float getY(){
		return y;
	}
	public float getZ(){
		return z;
	}
	public float getXRot(){
		return xRot;
	}  
	public float getYRot(){
		return yRot;
	}
	public float getZRot(){
		return zRot;
	}
	
	public abstract void render();
}
