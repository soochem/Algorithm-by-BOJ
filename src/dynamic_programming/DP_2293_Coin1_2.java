package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// ����1

public class DP_2293_Coin1_2 {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input;
		int i, j;
		int[] map = new int[101];
		int[] dp = new int[10001];  // ����� �� ����
		
		// �Է�
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		for (i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		// DP
		dp[0] = 1;
		for (i = 0; i < N; i++) {  // �Ʒ��� ���� ����� �� ä���
			for (j = 1; j <= K; j++) { // ���� ��쿡�ٰ� ���ο� ���ξ���
				if (j < map[i]) continue;
				dp[j] += dp[j-map[i]];
				System.out.println(dp[j]+" "+j);
			}
		}
		
		System.out.println(dp[K]);
	}
}
