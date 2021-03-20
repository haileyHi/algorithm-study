import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_FriendFee_16562 {
    static int N, M, K;
    static int[] parent;
    static Edge[] list;

    public static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static int find(int x){
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);//대표값 반환

    }

    static boolean union(int a, int b) {
        int aRoot = find(a); //처리는 대표로 하기 때문에 a의 Root를 사용
        int bRoot = find(b);
        if (aRoot == bRoot) return false;

        if(aRoot > bRoot){//작은 수를 루트로 두고 싶음
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }
        parent[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new Edge[N+M];//준석이와 학생 수 만큼의 간선 + 학생 끼리
        parent = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = new Edge(0, i + 1, Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list[N + i] = new Edge(from, to, 0);
        }

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        Arrays.sort(list);//가중치 오름차순으로 정렬.

        int sum = 0;
        int count = 0;
        for (Edge e : list) {
            if (union(e.from, e.to)) { //사이클이 아니면
                sum += e.weight;
                if(++count == N) break;
            }

            if (sum > K) {
                break;
            }
        }
        if(sum > K){
            System.out.println("Oh no");
        }else {
            System.out.println(sum);
        }
    }
}
