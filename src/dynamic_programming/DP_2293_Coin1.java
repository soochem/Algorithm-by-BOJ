package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// ����1

public class DP_2293_Coin1 {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input;
		int i, j, tmp, tot;
		int[] map = new int[100];
		int[] dp = new int[100];  // ����� �� ����
		
		// �Է�
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		tot = 0;
		
		for (i = 0; i < N; i++) {
			tmp = Integer.parseInt(br.readLine());
			map[i] = tmp;
			dp[tmp] = 1;
			tot += tmp;
		}
		
		// dp
		// ���� �ִ� ���ڵ鿡 map�� ������ ���ϱ�
		for (i = 0; i < N; i++) {
			for (j = tot; j >= 0; j--) {  // �Ųٷ� �������鼭 ���ϱ�
				if (dp[j] == 0) continue;
				dp[dp[j]+map[i]] += 1;
			}
		}
		
		System.out.println(dp[K]);
	}
}
