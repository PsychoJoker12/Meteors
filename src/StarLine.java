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
