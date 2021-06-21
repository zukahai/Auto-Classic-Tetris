package Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Class.Cubes;

public class Test extends JFrame{
	Container cn;
	public Test() {
		// TODO Auto-generated constructor stub
		super("Thong ke");
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		cn.add(new JLabel(readScore()));
		this.setVisible(true);
		this.setSize(500, 80);
		this.setLocationRelativeTo(null);
		setResizable(false);
		
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
	}
	
	public String readScore() {
		String FILE_URL = "Test/Score.txt";
    	File file = new File(FILE_URL);
        InputStream inputStream;
        int N = 0;
        int N2 = 0;
        int sum = 0;
        int sum2 = 0;
        try (
        		FileInputStream fis = new FileInputStream(file);
        		InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        		BufferedReader reader = new BufferedReader(isr)
        	){
				String line;
				while ((line = reader.readLine()) != null) {
					sum += Integer.parseInt(line.split("\t")[2].split(": ")[1]);
					sum2 += Integer.parseInt(line.split("\t")[1].split(": ")[1]);
					N++;
					N2++;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
        return "Score: " + sum2 / N2 + "\t Line: " + sum / N + " \t   | " + N;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test();
	}

}
