package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// ����

public class DP_9084_Coin {
	static int N, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input;
		int i, j;
		int[] map = new int[21];
		int[] dp = new int[10001];  // ����� �� ����
		
		// �Է�
		int tc = Integer.parseInt(br.readLine());
		while (--tc >= 0) {
			Arrays.fill(map, 0);
			Arrays.fill(dp, 0);
			
			N = Integer.parseInt(br.readLine());
			
			input = br.readLine().split(" ");			
			for (i = 0; i < N; i++) {
				map[i] = Integer.parseInt(input[i]);
			}
			K= Integer.parseInt(br.readLine());
			
			// DP
			dp[0] = 1;
			for (i = 0; i < N; i++) {  // �Ʒ��� ���� ����� �� ä���
				for (j = 1; j <= K; j++) { // ���� ��쿡�ٰ� ���ο� ���ξ���
					if (j < map[i]) continue;
					dp[j] += dp[j-map[i]];
				}
			}
			
			System.out.println(dp[K]);
		}
	}
}
