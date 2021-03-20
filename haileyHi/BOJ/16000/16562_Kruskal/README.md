# 16562번 친구비
[문제 보러가기](https://www.acmicpc.net/problem/16562)

## 🅰 설계

수업시간에 배웠던 크루스칼 알고리즘 복습하기에 좋았습니다.

준석이가 사귈 친구의 친구비는 준석이와 친구 간의 가중치로 두고 이미 친구인 관계에서는 가중치를 0으로 두었습니다.

union 할 때 더 작은 수를 루트로 담을 수 있도록
```java
if(aRoot > bRoot){
    int tmp = aRoot;
    aRoot = bRoot;
    bRoot = tmp;
}
parent[bRoot] = aRoot;
return true;
```
를 넣어주었습니다.

## 전체 코드
```java
public class FriendFee {
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
        int aRoot = find(a); //처리는 대표로 하기 때문에 a의 Root를 사용!
        int bRoot = find(b);
        if (aRoot == bRoot) return false;

        if(aRoot > bRoot){
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
```
## ✅ 후기
출력할 때는 대소문자를 잘 확인해야겠습니다. 유니온 파인드 너무 좋네요.