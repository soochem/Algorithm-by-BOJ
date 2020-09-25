package bipartite_matching;

import java.util.*;
import java.io.*;

public class BOJ_9576_Book {
	public static int m, n;
	public static int[] stud, book;
	public static int[] visit;
	public static ArrayList<ArrayList<Integer>> adj;
	
	static boolean dfs (int a) {
		int down = adj.get(a).get(0);
		int up = adj.get(a).get(1);
		visit[a] = 1;
		for (int i=down; i<=up; i++) {  // i�� å��ȣ
			// �ݴ����� ��Ī���� �ʾҰų� (B�� �ʱ�ȭ ��)
			// ��Ī�Ǿ� ������ ���� ��Ī�Ǿ� �ִ� ����(B[b])�� �ٸ� ������ ��Ī(dfs==true)�� �� ������ ����
			if (book[i] == 0 || visit[book[i]] == 0 && dfs(book[i])) {  // visit[B[b]] == 0
				stud[a] = i;
				book[i] = a;
				return true;
			}
		}
		return false;  // ��Ī ����
	}
	static void print (int[] a) {
		for (int t: a) {
			System.out.print(t+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(br.readLine());
		
		while (--testcase >= 0) {
			// �ʱ�ȭ
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			stud = new int[m+1];
			book = new int[n+1];
			visit = new int[m+1];
			adj = new ArrayList<>();
			for (int i=0; i<m+1; i++) {
				adj.add(new ArrayList<Integer>());
			}
			
			// ��ǲ
			for (int i=1; i<m+1; i++) {
				st = new StringTokenizer(br.readLine());
				int down = Integer.parseInt(st.nextToken());
				int up = Integer.parseInt(st.nextToken());
				adj.get(i).add(down);
				adj.get(i).add(up);
//				for (int j=down; j<=up; j++) {
//					adj.get(i).add(j);
//				}
			}
			
			// �̺� ��Ī
			int cnt = 0;
			for (int i=1; i<m+1; i++) {
				Arrays.fill(visit, 0);;
				if (dfs(i)) cnt++;
				//print(A); print(B);
			}
			System.out.println(cnt);
		}
	}
	
}
