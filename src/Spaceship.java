import processing.core.PApplet;

public class Spaceship extends Obj{
	private boolean up,down,left,right;
	final float SPEED=10;
	Vector speed;
	
	public Spaceship(PApplet p){
		super(p, p.width/2, p.height/2 ,0);
		
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
		
		if(up){
			if(left) speed.setVector(-diag,-diag);
		    else if(right) speed.setVector(diag,-diag);
		    else if(down) speed.setVector(0,0);
		    else speed.setVector(0,-SPEED);
		}
		else if(down){
			if(left) speed.setVector(-diag,diag);
		    else if(right) speed.setVector(diag,diag);
		    else speed.setVector(0,SPEED);
		}
		else if(left){
			if(right) speed.setVector(0,0);
		    else speed.setVector(-SPEED,0);
		}
		else if(right){
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
		p.box(50);
		p.popMatrix();
	}
}
