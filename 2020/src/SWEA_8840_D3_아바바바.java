import java.math.BigInteger;
import java.util.Scanner;

public class SWEA_8840_D3_아바바바 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int T=sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			BigInteger N=sc.nextBigInteger();
			BigInteger P=N.divide(BigInteger.valueOf(2)).pow(2);
			System.out.println("#"+tc+" "+P);
		}
	}
}