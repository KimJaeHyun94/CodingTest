
import java.util.Arrays;
import java.util.Scanner;

public class BJ9655_돌게임 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int count=0;
		
		while(true) {
			if(N<3)
				break;
			N-=3;
			count++;
		}
		if(N==0 || N==2) {
			if(count%2==0)
				System.out.println("CY");
			else
				System.out.println("SK");
		}
		else if(N==1) {
			if(count%2==0)
				System.out.println("SK");
			else
				System.out.println("CY");
		}
	}
}