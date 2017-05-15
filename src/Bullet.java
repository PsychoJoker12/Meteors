import processing.core.PApplet;

public class Bullet extends Obj{
	final float SPEED=100;
	
	public Bullet(PApplet p, Spaceship ship){
		super(p, ship.getX(), ship.getY(), ship.getZ());
	}
	
	private void move(){
		setZ(getZ()-SPEED);
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
