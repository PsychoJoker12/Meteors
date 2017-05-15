import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

public class Meteor extends Obj {
	PShape meteor;
	final float ROT;
	
	public Meteor(PApplet p, float x, float y) {
		super(p, x, y, -1024);
		meteor=p.loadShape("models\\meteors\\Meteor1.obj");
		
		ROT=p.random(-PConstants.PI/64,PConstants.PI/64);
		
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
		
		p.fill(64);
		p.stroke(32);
		
		p.pushMatrix();
		p.translate(getX(), getY(), getZ());
		p.rotateX(getXRot());
		p.rotateY(getYRot());
		p.rotateZ(getZRot());
		p.scale(100);
		p.shape(meteor);
		p.popMatrix();		
	}

}
