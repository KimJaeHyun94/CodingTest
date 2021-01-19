import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ_11047_동전0_13반_김재현 {
	private static int [] coins;
	private static Map<Integer, Integer> map = new HashMap<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int K=sc.nextInt();
		
		coins=new int[N];
		for (int i = 0; i < N; i++) {
			coins[i]=sc.nextInt();
		}
		changeMoneyLoop(K,0);
	}
	
	public static void changeMoneyLoop(int target, int from) {
		int change = 0;
		int sum=0;
		for (int i = coins.length-1; i >= from; i--) {

			// 1.해 선택
			change += coins[i];
			handleMap(coins[i],1);
			// 2. 실행 가능성 확인
			if (change > target) {
				change -= coins[i]; // 동전을 회수
				handleMap(coins[i],-1);
				continue;
			}

			// 해 검사
			if (change < target) {
				i++; // 현재 반복을 다시 돌게 끔
			} else {
				for (int key : map.keySet()) {
					sum+=map.get(key);
				}
				break;
			}
		}
		System.out.println(sum);
	}
	public static void handleMap(Integer key, Integer val) {
		// 처음이면 신규 추가, 아니면 업데이트
		if (map.containsKey(key)) {
			map.put(key, map.get(key) + val);
		} else {
			map.put(key, val);
		}
	}
}