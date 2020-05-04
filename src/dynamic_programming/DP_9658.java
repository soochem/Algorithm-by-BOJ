// ������ 4 ���������� �������� ����� ����.
// ���� : SK
// d[i-1] = CK�� ��쿣 SK�� 1�� �������� CK�� �ٽ� ���� i-1�� �� SK �����̶� CK�� �й� (= SK �¸�)
package dynamic_programming;

import java.io.*;

public class DP_9658 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[] memo = new char [n+1];
		
		if (n>=1) memo[1] = 'c'; // ���� : SK
		if (n>=2) memo[2] = 's'; // ���� : CY
		if (n>=3) memo[3] = 'c';
		if (n>=4) memo[4] = 's';
		for (int i=5; i<=n; i++) {
			if (memo[i-1] == 'c' || memo[i-3] == 'c' || memo[i-4] == 'c') memo[i] = 's';
			else memo[i] = 'c'; // ����̰� �̱���� â���̷� ������ �÷���(S)�� �ϳ��� �������
		}
		
		if (memo[n] == 'c') System.out.println("CY");
		else System.out.println("SK");
				
	}
}
