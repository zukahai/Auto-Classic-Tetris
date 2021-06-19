package Class;

import java.awt.Color;
import java.util.Vector;

import GUI.ClassicJigsawPuzzle;

public class Cubes {
	int d[] = {0, 0, 0, 0, 0, 0, 0, 0};
	int c[] = {0, 1, 1, 1, 1, 1, 1, 0};
	int type = 0;
	int top, bot;
	int ic = 0;
	Squar tt = new Squar();
	Vector<Squar> v = new Vector();

	public Cubes() {
		this.tt = new Squar(2, 8);
		int rand = (int) (1000000 * Math.random() % 6 + 1);
		if (rand < 3) {
			if (Math.random() > 0.5)
				rand++;
		} else 
			rand += 2;
		if (rand == 8)
			rand = 7;
		this.type = rand;
		this.ic = (int) (1000000 * Math.random() % 7 + 1);
		this.v = ininV();
		this.bot = c[type];
		this.top = d[type];
	}

	public int getIc() {
		return ic;
	}

	public void setIc(int ic) {
		this.ic = ic;
	}

	public Vector<Squar> ininV() {
		Vector<Squar> vii = new Vector();
		if (this.type == 1) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX(), tt.getY() - 1));
			vii.add(new Squar(tt.getX() + 1, tt.getY() - 1));
		} else if (this.type == 2) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX(), tt.getY() - 1));
			vii.add(new Squar(tt.getX() + 1, tt.getY() + 1));
		} else if (this.type == 3) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() - 1));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY() + 1));
		} else if (this.type == 4) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY() - 1));
		} else if (this.type == 5) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX(), tt.getY() - 1));
		} else if (this.type == 6) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY() + 1));
		} else if (this.type == 7) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() - 1));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX(), tt.getY() + 2));
		}   
		return vii;
	}

	public void turnRight() {
		if (type != 6) {
			Vector<Squar> tV = new Vector<>();
			for (int i = 0; i < this.v.size(); i++) {
				Squar sq = this.v.elementAt(i);
				sq = sq.sub(tt);
				sq = sq.turnR();
				sq = sq.add(tt);
				tV.add(sq);
			}
			this.v = tV;
		}
	}
	
	public void turnLeft() {
		if (type != 6) {
			Vector<Squar> tV = new Vector<>();
			for (int i = 0; i < this.v.size(); i++) {
				Squar sq = this.v.elementAt(i);
				sq = sq.sub(tt);
				sq = sq.turnL();
				sq = sq.add(tt);
				tV.add(sq);
			}
			this.v = tV;
		}
	}
	
	public void down() {
		this.tt = this.tt.add(new Squar(1, 0));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.add(new Squar(1, 0));
			tV.add(sq);
		}
		this.v = tV;
	}
	
	public void up() {
		this.tt = this.tt.sub(new Squar(1, 0));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.sub(new Squar(1, 0));
			tV.add(sq);
		}
		this.v = tV;
	}
	
	public void right() {
		this.tt = this.tt.add(new Squar(0, 1));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.add(new Squar(0, 1));
			tV.add(sq);
		}
		this.v = tV;
	}
	
	public void left() {
		this.tt = this.tt.sub(new Squar(0, 1));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.sub(new Squar(0, 1));
			tV.add(sq);
		}
		this.v = tV;
	}

	public boolean check(boolean b[][]) {
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			if (b[sq.getX()][sq.getY()] == false) {
				return false;
			}
				
		}
		return true;
	}
	
	public void display() {
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			System.out.print("(" + (sq.getX()) + ", " + (sq.getY()) + ") ");
		}
		System.out.println("(" + tt.getX() + ", " + tt.getY() + ") ");
	}
	
	public Cubes(Squar TT) {
		this.tt = TT;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Squar getTt() {
		return tt;
	}

	public void setTt(Squar tt) {
		this.tt = tt;
	}

	public Vector<Squar> getV() {
		return v;
	}

	public void setV(Vector<Squar> v) {
		this.v = v;
	}
	
	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getBot() {
		return bot;
	}

	public void setBot(int bot) {
		this.bot = bot;
	}

	public int initIc(int N) {
		int k = 0;
		do {
			k = (int) (1000000 * Math.random() % 6 + 1);
		} while (k == N);
		return k;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClassicJigsawPuzzle();
	}

}