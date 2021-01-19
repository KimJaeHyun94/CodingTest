package M0226;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15953상금헌터 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int sum=0;
			
			if(a==0)
				sum+=0;
			else if(a==1)
				sum+=5000000;
			else if(a<=3)
				sum+=3000000;
			else if(a<=6)
				sum+=2000000;
			else if(a<=10)
				sum+=500000;
			else if(a<=15)
				sum+=300000;
			else if(a<=21)
				sum+=100000;
			else
				sum+=0;
			
			if(b==0)
				sum+=0;
			else if(b==1)
				sum+=5120000;
			else if(b<=3)
				sum+=2560000;
			else if(b<=7)
				sum+=1280000;
			else if(b<=15)
				sum+=640000;
			else if(b<=31)
				sum+=320000;
			else
				sum+=0;
			
			System.out.println(sum);
		}
	}
}
