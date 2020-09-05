package sds_summer.day2_data_structure;

import java.util.*;
import java.io.*;

// )
// ((()))
public class SDS_2504 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		
		// �ʱ�ȭ
		Stack<Character> stk = new Stack<>();
		int temp = 1;
		int ans = 0;
		char c;
		char peek = '0';
		boolean flag = false;  // ������
		
		String s;
		s = br.readLine();
		
		for (int i=0; i<s.length(); i++) {
			if (flag) break;
			c = s.charAt(i);
			
			if (c == '(') {
				stk.push(c);
				temp *= 2;
			}
			else if (c == '[') {
				stk.push(c);
				temp *= 3;
			}
			else if (c == ')') {
				if (stk.isEmpty()) flag = true;  // ���ÿ� �ƹ��͵� ���� ����
				else {
					peek = stk.peek();
					// ������
					if (peek != '(') flag = true;
					// ����
					else {
						if (s.charAt(i-1) == '(') ans += temp; // �ް�ȣ ������ ����
						stk.pop();
						temp /= 2;
					}
				}
				
			}
			else if (c == ']') {
				if (stk.isEmpty()) flag = true;
				else {
					peek = stk.peek();
					// ������
					if (peek != '[') flag = true;
					// ����
					else {
						if (s.charAt(i-1) == '[') ans += temp;
						stk.pop();
						temp /= 3;
					}
				}
			}
		}
		
		if (!stk.isEmpty()) flag = true;  // ������
		
		if (flag) ans = 0;
		bw.write(String.valueOf(ans)+"\n");
		bw.flush(); bw.close();
	}
}
