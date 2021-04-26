package M0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 통계학 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		int count[] = new int[8001];

		int sum = 0;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
			count[num + 4000]++;
			sum += arr[i];
		}
		Arrays.sort(arr);

		int min = Integer.MIN_VALUE;
		for (int i = 0; i < 8001; i++) {
			if (min < count[i]) {
				min = count[i];
			}
		}

		ArrayList<Integer> list= new ArrayList<>();
		int idx = 0;
		for (int i = 0; i < 8001; i++) {
			if(list.size()==2) 
				break;
			if (count[i] == min) {
				list.add(i);
			}
		}
		
		int pung = (int) Math.round((double) sum / N);
		int mid = arr[arr.length / 2];
		int round = arr[arr.length - 1] - arr[0];
		System.out.println(pung);
		System.out.println(mid);
		if (list.size()==2) {
			System.out.println(list.get(1) - 4000);
		} else
			System.out.println(list.get(0) - 4000);
		System.out.println(round);

	}
}
