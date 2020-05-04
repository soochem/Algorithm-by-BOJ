// EOF���� ��Ÿ�� ����
// (s = br.readLine()) != null�� ó�� 

package sds_day3_4_graph;

//import java.util.*;
import java.io.*;

public class SDS_5639_binarySearchTree {
	static int[] map;
	static int len;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static void dfs(int left, int right) throws IOException {
		// �޳� ������
		if (left > right) return;
		
		int root = left;
		int r = right;
		
		while (map[right] > map[root]) right--;
		
		dfs(left+1, right);
		dfs(right+1, r);
		bw.write(map[root]+"\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
		
		String s = ""; // eof ������ null �� ����
		len = -1;
		map = new int[10000];
		
		while ((s = br.readLine()) != null && s.length() != 0) {
			int a = Integer.parseInt(s);
			map[++len] = a;
		}

		dfs(0, len);
		bw.flush();
		bw.close();
	}
}
