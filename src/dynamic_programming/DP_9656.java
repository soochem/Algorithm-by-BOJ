// ������ 2 ���������� �������� ����� ����.
// ���� : SK
package dynamic_programming;

import java.io.*;

public class DP_9656 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[] memo = new char [n+1];
		
		if (n>=1) memo[1] = 'c';
		if (n>=2) memo[2] = 's';
		if (n>=3) memo[3] = 'c'; // ���������� �� �������� ��� : SK
		for (int i=4; i<=n; i++) {
			if (memo[i-1] == 'c' && memo[i-3] == 'c') memo[i] = 's'; // ??
			else memo[i] = 'c';
		}
		if (memo[n] == 'c') System.out.println("CY");
		else System.out.println("SK");
	}
}
