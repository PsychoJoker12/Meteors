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
public class Vector{
	private float u,v;
	private float magnitude;
	
	public Vector(float u, float v){
		setVector(u,v);
	}
	
	public Vector(){
		this(0,0);
	}
	
	public float getU(){
		return u;
	}
	
	public float getV(){
		return v;
	}
	
	public void setVector(float u, float v){
		this.u=u;
		this.v=v;
		setMagnitude();
	}
	
	public float getMagnitude(){
		return magnitude;
	}
	
	private void setMagnitude(){
		magnitude=(float)Math.sqrt(u*u+v*v);
	}
}
