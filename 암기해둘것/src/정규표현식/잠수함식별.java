package 정규표현식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잠수함식별 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String pattern = "(100+1+|01)+";
		
		if(str.matches(pattern)) {
			System.out.println("SUBMARINE");
		}else
			System.out.println("NOISE");
	}

}
