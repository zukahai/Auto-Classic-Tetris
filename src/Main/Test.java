package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Test {

	public static void readScore() {
		String FILE_URL = "Score.txt";
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
					sum += Integer.parseInt(line.split("\t")[1].split(": ")[1]);
					sum2 += Integer.parseInt(line.split("\t")[0].split(": ")[1]);
					N++;
					N2++;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
        System.out.println("Score: " + sum2 / N2 + " Line: " + sum / N + "| " + N);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readScore();
	}

}
