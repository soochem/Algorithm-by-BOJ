package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_L11726 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(tile(n));
	}
	private static int tile (int n) {
		int[] memo = new int [1001];
		memo[1] = 1;
		memo[2] = 2;
		for (int i=3; i<=n; i++) {
			memo[i] = memo[i-1] + memo[i-2];
			memo[i] = memo[i]%10007; // bottomup���� ���ϱ� ������ ��� �����ؾ� ������ �信�� ������ ��
		}
		return memo[n];
	}
}
