package Class;

public class Squar {
	int x = 0, y = 0;
	
	public Squar() {
		this.x = 0;
		this.y = 0;
	}
	
	public Squar(int X, int Y) {
		this.x = X;
		this.y = Y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	public Squar add(Squar SQ) {
		return new Squar(this.x + SQ.getX(), this.y + SQ.getY());
	}
	
	public Squar sub(Squar SQ) {
		return new Squar(this.getX() - SQ.getX(), this.getY() - SQ.getY());
	}
	
	public Squar turnR() {
		return new Squar(this.y, -this.x);
	}
	
	public Squar turnL() {
		return new Squar(-this.y, this.x);
	}
	
	public void display() {
		System.out.println("(" + this.x + " " + this.y + ")");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
