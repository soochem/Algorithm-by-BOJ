// �Ҽ��� ������
// ���� ����
// *** �������� ����, ���� ����
 
package sds_summer.day5_6_math;

import java.io.*;
import java.util.*;

public class SDS_1644 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int n, cnt;
		boolean[] visited;
		ArrayList<Integer> sosu = new ArrayList<>();
		
		// �Է�
		cnt = 0;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		visited = new boolean [n+1];

		// ¦�� ó��
		sosu.add(2);
		for (int i=4; i<=n; i+=2) {
			visited[i] = true;
		}
		// Ȧ�� ó��
		for (int i=3; i<=n; i+=2) {
			if (!visited[i]) {
				sosu.add(i);
				for (int j=2; j*i<=n; j++) {  // ��� n�� �����Ͽ����Ѵ�.
					if (!visited[j*i])
						visited[j*i] = true;
				}
			}
		}
		
		int left, right; left = 0; right = 1; // ���Եθ� ���ϴµ� (���������)
		int sum = 0;
		sum = sosu.get(0);
	
		while (left <= right) {
			if (sum == n) cnt++;  // right�� 1�̱� ������ ó���� üũ�ؾ���
			
			if (sum >= n) {
				sum -= sosu.get(left++);
			}
			else if (right == sosu.size()) break;
			else {
				sum += sosu.get(right++);  // ������ ��ġ �ſ� �߿�
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		bw.flush(); bw.close();
	}
}
