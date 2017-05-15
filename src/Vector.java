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
