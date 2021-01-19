
import java.util.Scanner;

public class BJ1120_문자열 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		String str2=sc.next();
		int max=Integer.MIN_VALUE;
		int index=0;
		int N=str2.length()-str.length();
	
		for (int i = 0; i <= N; i++) {
			index=0;
			for (int j = 0; j < str.length(); j++) {
				if(str2.charAt(j+i)==str.charAt(j)) {
					index++;
				}
			}
			if(max<index)
				max=index;
		}
		
		System.out.println(str.length()-max);
	}
}