package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Class.Cubes;
import Class.Squar;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class AI_ClassicTetris extends JFrame implements KeyListener{
	Container cn;
	JPanel pn;
	Timer timer = new Timer(100, null);
	int M = 22, N = 10;
	int delay = 1;
	int index = delay;
	int score = 0;
	int line = 0;
	String timeStar = "";
	Vector <String> str = new Vector<>();
	boolean die = false;
	JButton bt[][] = new JButton[M + 5][N + 9];
	boolean b[][] = new boolean[M + 5][N + 9];
	int preCl = 0;
	Color cl[] = {Color.black, Color.blue, Color.cyan, Color.green, Color.magenta, Color.orange, Color.red, Color.yellow};
	Cubes p = new Cubes();
	Cubes [] Que = new Cubes[5];
	public AI_ClassicTetris() {
		super("HaiZuka");
		cn = init();
		timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (index == 0) {
				if (true) {
					do {
						p.down();
					} while (p.check(b));
					printArray(b);
//					p.display();
					if (!p.check(b)) {
						p.up();
						Vector<Squar>  pp = p.getV();
						for (int i = 0; i < pp.size(); i++) {
							Squar sq = pp.elementAt(i);
							b[sq.getX()][sq.getY()] = false;
						}
						update();
						newPuzz();
					}
					else {
						try {
							sound(4);
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					update();
					index = delay;
				} else 
					index --;
//				System.out.println(delay);
			}
		});
		timer.start();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		LocalDateTime myDateObj = LocalDateTime.now();
//	    System.out.println("Before formatting: " + myDateObj);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	    timeStar = myDateObj.format(myFormatObj);
		
//		for (int i = 0; i < M + 3; i++)
//			for (int j = 3; j < N + 3; j++)
//				t[i][j] = true;
		
		pn = new JPanel();
		pn.setLayout(new GridLayout(M, N + 6));
		
		for (int i = 0; i < Que.length; i++) {
			Que[i] = new Cubes();
			Que[i].setIc(Que[i].initIc(preCl));
			preCl = Que[i].getIc();
		}
		
		for (int i = 0; i < M + 5; i++)
			for (int j = 0; j < N + 7; j++) {
				b[i][j]  = false;
			}
		
		for (int i = 0; i <= M + 3; i++)
			for (int j = 0; j <= N + 8; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addKeyListener(this);
				bt[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
			}
		for (int i = 0; i <= M + 3; i++)
			for (int j = N + 3; j <= N + 8; j++) {
				bt[i][j].setBorder(null);
			}
		
		for (int i = 0; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++) {
				b[i][j] = true;
			}
		
		for (int i = 3; i < M + 3; i++)
			for (int j = 3; j < N + 9; j++) {
				bt[i][j].setBackground(cl[0]);
				pn.add(bt[i][j]);
			}
		cn.add(pn);
		newPuzz();
		this.setVisible(true);
		this.setSize(500, 700);
		this.setLocationRelativeTo(null);
		setResizable(false);
		setTextScoreLineSpeed();
		
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
	}
	
	public void sound(int index) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		return;
//		try {
//			File file = new File("src/Sound/" + index + ".wav");
//			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//			Clip clip = AudioSystem.getClip();
//			clip.open(audioStream);
//			String response = "";
//			clip.start();
//		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}
	
	public int sub(int N) {
		int a[] = {20,	 5,	 	10,		5,		10,		5,		15,		5,		25,		5, 		20, 	5,		30,		5, 		15,		5, 		35, 	5, 		20, 	5, 		20,		5};
		int b[] = {0,	 1,		 0,		1,		0,		1,		0,		1,		0,		1,		0,		1,		0,		1, 		0,		1, 		0, 		1,		0,		1,		0,		1};
		int t = 0;
		for (int i = 0; i < a.length; i++) {
			t += a[i];
			if (N <= t)
				return b[i];
		}
			
		return 0;
	}
	
	public void setTextScoreLineSpeed() {
		int temp = 33;
		for (int j = N + 7; j >= N + 4; j--) {
			bt[M][j].setIcon(getIcon("" + temp));
			temp--;
		}
		temp = 22;
		for (int j = N + 6; j >= N + 5; j--) {
			bt[M - 2][j].setIcon(getIcon("" + temp));
			temp--;
		}
		
		temp = 13;
		for (int j = N + 7; j >= N + 4; j--) {
			bt[M - 4][j].setIcon(getIcon("" + temp));
			temp--;
		}
	}
	
	public void printArray(boolean b[][]) {
		for (int i = 0; i < M + 3; i++) {
			for (int j = 3; j < N + 3; j++)
				if (b[i][j])
					System.out.print("1 ");
				else 
					System.out.print("0 ");
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public int[] AI(boolean b[][], Cubes p, int doSau) {
		boolean t[][] = new boolean[M + 5][N + 9];
		int thaotac[] = new int[3];
		thaotac[2] = -10000000;
		
		for (int i = 0; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++)
				t[i][j] = b[i][j];
		for (int tt1 = 0; tt1 <= 3; tt1++) {
			Cubes t_cb2 = new Cubes(p);
			for (int i = 0; i < tt1; i++)
				t_cb2.turnRight();
			for (int tt2 = -5; tt2 <= 5; tt2 ++) {
				Cubes t_cb = new Cubes(t_cb2);
				t_cb.down();
				if (tt2 < 0) {
					for (int i = 0; i < -tt2; i++)
						t_cb.left();
					if (!t_cb.check(b)) {
						continue;
					}
						
				} else {
					for (int i = 0; i < tt2; i++)
						t_cb.right();
					if (!t_cb.check(b)) {
						continue;
					}
				}
				
				while (t_cb.check(t))
					t_cb.down();
				t_cb.up();
				Vector<Squar> pp = t_cb.getV();
				for (int i = 0; i < pp.size(); i++) {
					Squar sq = pp.elementAt(i);
					t[sq.getX()][sq.getY()] = false;
				}
//				if (doSau == 2)
//					printArray(t);
				int ck = checkScore(t);
				int ck2 = 0;
//				if (doSau == 1) {
//					ck2 = AI(t, Que[0], 2)[2];
//				}
//				System.out.println("Ck2 ------------------ " + ck2);
//				System.out.println("Score " + tt1 + " " + tt2 + " " + ck);
				if (ck  + ck2 > thaotac[2]) {
					thaotac[2] = ck;
					thaotac[0] = tt1;
					thaotac[1] = tt2;
				}
				for (int i = 0; i < M + 3; i++)
					for (int j = 3; j < N + 3; j++)
						t[i][j] = b[i][j];
			}
		}
		
		
		return thaotac;
	}
	
	int checkScore(boolean t[][]) {
		int d = 0, leng = 0, ho = 0, countR = 0;
		for (int j = 3; j < N + 3; j++) {
			int i = 3;
			int l = 0;
			while (i < M + 3 && t[i][j]) {
				d++;
				i++;
				l++;
			}
			leng += (22 - l) * (22 - l);
		}
		for (int i = 3; i < M + 3 - 1; i++)
			for (int j = 3; j < N + 3; j++)
				if (!t[i][j]) {
					int I = i + 1;
					int ho2 = 0;
					int sl = 1;
					while (I < M + 3 && t[I][j]) {
						I++;
						ho2++;
					}
					I = i - 1;
					while (I >= 0 && !t[I][j]) {
						I--;
						sl++;
					}
//					ho += ho2 * Math.sqrt(sl);
					ho += ho2 * 1;
				}
		for (int i = 3; i < M + 3; i++) {
			boolean kt = true;
			for (int j = 3; j < N + 3; j++)
				if (t[i][j] == true)
					kt = false;
			if (kt)
				countR++;
		}
		switch (countR) {
		case 1:
			d += 400;
			break;
		case 2:
			d += 600;
			break;
		case 3:
			d += 900;
			break;
		case 4:
			d += 3000;
			break;
		}
		return d - leng - ho * 450;
	}
	
	public void newPuzz() {
		try {
			sound(2);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		p.up();
//		Vector<Squar>  pp = p.getV();
//		for (int i = 0; i < pp.size(); i++) {
//			Squar sq = pp.elementAt(i);
//			b[sq.getX()][sq.getY()] = false;
//		}
//		for (int i = 0; i < M + 3; i++)
//			for (int j = 3; j < N + 3; j++)
//				b[i][j] = t[i][j];
		updateQue();
		
		int a[] = AI(b, p, 1);
//		System.out.println(a[0] + " " + a[1]);
		
		Cubes t_cb = new Cubes(p);
		for (int i = 0; i < a[0]; i++)
			t_cb.turnRight();
		if (a[1] < 0) {
			for (int i = 0; i < -a[1]; i++)
				t_cb.left();
		} else {
			for (int i = 0; i < a[1]; i++)
				t_cb.right();
		}
//		while (t_cb.check(t))
//			t_cb.down();
//		t_cb.up();
//		
//		Vector<Squar> pp = t_cb.getV();
//		for (int i = 0; i < pp.size(); i++) {
//			Squar sq = pp.elementAt(i);
//			t[sq.getX()][sq.getY()] = false;
//		}
//		printArray(t);
		for (int i = 0; i < a[0]; i++)
			p.turnRight();
		if (a[1] < 0) {
			for (int i = 0; i < -a[1]; i++)
				p.left();
		} else {
			for (int i = 0; i < a[1]; i++)
				p.right();
		}
//		while (p.check(b))
//			p.down();
//		p.up();
	}
	
	public void updateScore() {
		int temp = score;
		for (int j = N + 7; j >= N + 4; j--) {
			int k = temp % 10;
			temp /= 10;
			bt[M + 1][j].setIcon(getIcon("" + k));
		}
		
		temp = line;
		for (int j = N + 7; j >= N + 4; j--) {
			int k = temp % 10;
			temp /= 10;
			bt[M - 1][j].setIcon(getIcon("" + k));
		}
		
		temp = 61 - delay;
		for (int j = N + 7; j >= N + 4; j--) {
			int k = temp % 10;
			temp /= 10;
			bt[M - 3][j].setIcon(getIcon("" + k));
		}
	}
	
	public void updateQue() {
		for (int i = 0; i < M - 4; i++)
			for (int j = N + 3; j <= N + 8; j++) {
				bt[i][j].setBackground(Color.black);
				bt[i][j].setBorder(null);
			}
				
		p = Que[0];
		p.setTt(new Squar(2, 8));
		p.setV(p.ininV());
		if (!p.check(b)) {
			timer.stop();
			die = true;
		}
		for (int i = 0; i < Que.length - 1; i++)
			Que[i] = Que[i + 1];
		Que[Que.length - 1] = new Cubes();
		Que[Que.length - 1].setIc(Que[Que.length - 1].initIc(preCl));
		preCl = Que[Que.length - 1].getIc();
		int H = 3;
		for (int i = 0; i < Que.length; i++) {
			Que[i].setTt(new Squar(H + Que[i].getTop() + 1, N + 5));
//			if (Que[i].getType() == 2 || Que[i].getType() == 4 || Que[i].getType() == 5)
//				Que[i].setTt(new Squar(H + Que[i].getTop() + 1, N + 5));
			Que[i].setV(Que[i].ininV());
			for (int J = 0; J < Que[i].getV().size(); J++) {
				Squar sq = Que[i].getV().elementAt(J);
				bt[sq.getX()][sq.getY()].setBackground(cl[Que[i].getIc()]);
				bt[sq.getX()][sq.getY()].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
			}
			H += Que[i].getBot() + Que[i].getTop() + 2;
		}
		updateScore();
	}
	
	public void update() {
		if (die){
//			JOptionPane.showMessageDialog(null, "Your Score: " + score + "\n" + "Line: " + line);
			printScore();
//			System.exit(0);
			new AI_ClassicTetris();
			timer.stop();
			this.dispose();
		}
		int countR = 0;
		for (int i = M + 2; i >= 1; i--) {
			boolean kt = true;
			for (int j = 3; j < N + 3; j++)
				if (b[i][j] == true)
					kt = false;
			if (kt) {
				try {
					sound(3);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				countR++;
				line++;
				delay -= sub(line);
				if (delay < 1)
					delay = 1;
				updateScore();
				for (int h = i; h >= 1; h--)
					for (int j = 3; j < N + 3; j++) {
						b[h][j] = b[h - 1][j];
						bt[h][j].setBackground(bt[h - 1][j].getBackground());
					}
				for (int j = 3; j < N + 3; j++)
					b[0][j] = true;
				i++;
			}
		}
		
		//Score: 760  2700  5700  22800
		
		switch (countR) {
			case 1:
				score += 1;
				break;
			case 2:
				score += 4;
				break;
			case 3:
				score += 9;
				break;
			case 4:
				score += 30;
				break;
		}
		updateScore();
		
		for (int i = 3; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++)
				if (b[i][j])
					bt[i][j].setBackground(cl[0]);
		
		Vector<Squar> vP = p.getV();
		for (int i = 0; i < vP.size(); i++) {
			Squar sq = vP.elementAt(i);
			bt[sq.getX()][sq.getY()].setBackground(cl[p.getIc()]);
		}
	}
	
	public Icon getIcon(String index) {
		int w = 30;
		int h = 30;
		Image image = new ImageIcon(getClass().getResource("/Icons/" + index + ".png")).getImage();
		Icon ic = new ImageIcon(image.getScaledInstance(w, h, image.SCALE_SMOOTH));
		return ic;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (die) 
			return;
		if (e.getKeyCode() == e.VK_UP) {
			p.turnRight();
			if (!p.check(b)) {
				p.turnLeft();
			} else {
				try {
					sound(1);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			update();
		} else if (e.getKeyCode() == e.VK_DOWN) {
			p.down();
			if (!p.check(b)) {
				p.up();
				Vector<Squar>  pp = p.getV();
				for (int i = 0; i < pp.size(); i++) {
					Squar sq = pp.elementAt(i);
					b[sq.getX()][sq.getY()] = false;
				}
				newPuzz();
			} else {
				try {
					sound(4);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			update();
		} else if (e.getKeyCode() == e.VK_LEFT) {
			p.left();
			if (!p.check(b)) {
				p.right();
			} else {
				try {
					sound(1);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			update();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			p.right();
			if (!p.check(b)) {
				p.left();
			} else {
				try {
					sound(1);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			update();
		}
	}

	public void printScore() {
		readScore();
		File file = new File("Test/Score.txt");
		try (FileOutputStream fos = new FileOutputStream(file);
	             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
	             BufferedWriter writer = new BufferedWriter(osw)
	        ) {
			for (int i = 0; i < str.size(); i++) {
				writer.append(str.elementAt(i));
	            writer.newLine();
			}
			
			LocalDateTime myDateObj = LocalDateTime.now();
//		    System.out.println("Before formatting: " + myDateObj);
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		    String formattedDate = myDateObj.format(myFormatObj);
//		    System.out.println("After formatting: " + formattedDate);
		    
            writer.append((str.size() + 1) + "| \t Score: " + score + "\tLine: " + line + "\t TimeBegin: " + timeStar + "\t TimeEnd: " + formattedDate);
            writer.newLine();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void readScore() {
		String FILE_URL = "Test/Score.txt";
    	File file = new File(FILE_URL);
        InputStream inputStream;
        try (
        		FileInputStream fis = new FileInputStream(file);
        		InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        		BufferedReader reader = new BufferedReader(isr)
        	){
				String line;
				while ((line = reader.readLine()) != null) {
					str.add(line);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		new AI_ClassicTetris();
	}
}
