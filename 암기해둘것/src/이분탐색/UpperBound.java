package 이분탐색;

public class UpperBound {
	static int arr[];

	private static int upperbound(int target) {
		int left = 0;
		int right = arr.length; // 끝점이 주어지지 않았을 경우 체크해두기
		while (left < right) {
			int mid = (left + right) >> 1;
			if (arr[mid] <= target)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}

	private static int upperbound2(int target) {
		int left = 0;
		int right = arr.length - 1; // 끝점이 주어지지 않았을 경우 체크해두기
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (arr[mid] <= target)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return left;
	}
}
