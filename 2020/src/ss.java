
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ss {
   static int n;

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());

      int result = fibo(n);
      System.out.println(result);
   }

   public static int fibo(int number) {
      int[] arr = new int[number + 1];
      arr[0] = 0;
      arr[1] = 1;

      int idx = 2;
      while (idx <= number) {
         arr[idx] = arr[idx - 1] + arr[idx - 2];
         idx++;
      }
      return arr[number];
   }
}