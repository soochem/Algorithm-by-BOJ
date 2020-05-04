package bipartite_matching;

import java.util.*;
import java.io.*;

public class Yeolhyeol_11375 {
	public static int m, n;
	public static int[] emp, work;
	public static int[] visit;
	public static ArrayList<ArrayList<Integer>> adj;
	
	static boolean dfs (int a) {
		//int size = adj.get(a);
		visit[a] = 1;
		for (int i : adj.get(a)) {  // i�� å��ȣ
			// �ݴ����� ��Ī���� �ʾҰų� (B�� �ʱ�ȭ ��)
			// ��Ī�Ǿ� ������ ���� ��Ī�Ǿ� �ִ� ����(B[b])�� �ٸ� ������ ��Ī(dfs==true)�� �� ������ ����
			if (work[i] == 0 || visit[work[i]] == 0 && dfs(work[i])) {  // visit[B[b]] == 0
				emp[a] = i;
				work[i] = a;
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
		
		// �ʱ�ȭ
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		emp = new int[n+1];
		work = new int[m+1];
		visit = new int[n+1];
		adj = new ArrayList<>();
		for (int i=0; i<n+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		
		// ��ǲ
		for (int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j=0; j<num; j++) {
				adj.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// �̺� ��Ī
		int cnt = 0;
		for (int i=1; i<n+1; i++) {
			Arrays.fill(visit, 0);;
			if (dfs(i)) cnt++;
			//print(A); print(B);
		}
		System.out.println(cnt);
	}
}
