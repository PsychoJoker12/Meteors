import processing.core.PApplet;

public class Spaceship extends Obj{
	private boolean up,down,left,right;
	final float SPEED=10;
	
	final float SCALE;
	
	Vector speed;
	
	public Spaceship(PApplet p){
		super(p, p.width/2, p.height/2 ,0);
		SCALE=50;
		
		speed=new Vector();
		
		up=false;
		down=false;
		left=false;
		right=false;
	}
	
	public void moveUp(){
		up=true;
	}
	public void moveDown(){
		down=true;
	}
	public void moveLeft(){
		left=true;
	}
	public void moveRight(){
		right=true;
	}
	
	public void haltUp(){
		up=false;
	}
	public void haltDown(){
		down=false;
	}
	public void haltLeft(){
		left=false;
	}
	public void haltRight(){
		right=false;
	}
	
	private void setMovement(){
		float diag=(float)(SPEED/Math.sqrt(2));
		float yMax=p.height-SCALE, xMax=p.width-SCALE;
		float yMin=0+SCALE, xMin=0+SCALE;
		
		if(up && getY()>yMin){
			if(left && getX()>xMin) speed.setVector(-diag,-diag);
		    else if(right && getX()<xMax) speed.setVector(diag,-diag);
		    else if(down && getY()<yMax) speed.setVector(0,0);
		    else speed.setVector(0,-SPEED);
		}
		else if(down && getY()<yMax){
			if(left && getX()>xMin) speed.setVector(-diag,diag);
		    else if(right && getX()<xMax) speed.setVector(diag,diag);
		    else speed.setVector(0,SPEED);
		}
		else if(left && getX()>xMin){
			if(right && getX()<xMax) speed.setVector(0,0);
		    else speed.setVector(-SPEED,0);
		}
		else if(right && getX()<xMax){
			speed.setVector(SPEED,0);
		}
		else{
			speed.setVector(0,0);
		}
		
		setX(getX()+speed.getU());
		setY(getY()+speed.getV());
	}
	
	public Bullet fire(){
		return new Bullet(p, this);
	}
	
	@Override
	public void render(){
		setMovement();
		
		p.stroke(255);
		p.noFill();
		p.pushMatrix();
		p.translate(getX(),getY(),getZ());
		p.box(SCALE);
		p.popMatrix();
	}
}
