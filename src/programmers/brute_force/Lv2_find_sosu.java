package programmers.brute_force;

// �Ҽ� ã��

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lv2_find_sosu {
    public static StringBuilder sb = new StringBuilder();
    public static boolean[] prime; // �Ҽ����� ? (�Ҽ��� FALSE)
    public static int[] map;       // ���ڸ� �̷�� ��� CHAR => INT
    public static int cnt;
    public static int len;

    public static String findBig(){
        // ��Ʈ������ ���� �� �ִ� ���� ū ���� ã��
		Arrays.sort(map);
		for(int tmp : map) {
		    sb.append(tmp);
        }
//		System.out.println(sb.reverse().toString());
        return sb.reverse().toString();
    }
    
    public static void search(int big){
	    // �Ҽ� ã��
        int i, j;
        prime = new boolean[big+1];
        prime[0] = true;
        prime[1] = true;
        for (i = 2; i <= big; i++){
            if (prime[i]) continue;
            for (j = 2; j*i <= big; j++){
                prime[i*j] = true;
            }
        }
    }

    public static void dfs(int depth, String sample, int[] map, boolean[] visited) {
//        System.out.println(sample);

        // �Ҽ� �˻� (ã���� cnt++)
        if (depth != 0) {
            int intSample = Integer.parseInt(sample);
            if (!prime[intSample]) {
                cnt++;
                System.out.println(intSample);
                prime[intSample] = true;
            }
        }
        // depth�� len�� �Ѿ�� return
        if (depth == len) {
            return;
        }

	    for (int i=0; i<len; i++) {
	        if (visited[i] | (depth == 0 & map[i] == 0)) {
                continue;
            }
	        visited[i] = true;
	        dfs(depth+1, sample+map[i], map, visited);
	        visited[i] = false;
        }
    }
	
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String numbers = "912223";
//        String numbers = "17";
        String numbers = "00000011";

        len = numbers.length();

        map = new int[len];

        for (int i = 0; i < len; i++) {
            map[i] = numbers.charAt(i) - '0';
        }

        numbers = findBig();
        search(Integer.parseInt(numbers));

        // dfs ��� ����� ��
        cnt = 0;
        dfs(0, "", map, new boolean[len]);
        System.out.println(cnt);

	}
}
