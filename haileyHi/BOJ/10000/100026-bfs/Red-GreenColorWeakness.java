import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[][] dir = {
            {-1,0},{0,1},{1,0},{0,-1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s  = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        int g = 0, n = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    g += green(i, j) == 0 ? 0 : 1;
                }
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    n += normal(i, j) == 0 ? 0 : 1;
                }
            }
        }

        System.out.println(n + " " + g);
    }
    private static int green(int r, int c){
        int cnt = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            cnt++;
            for (int d = 0; d < 4; d++) {
                int newR = cur[0] + dir[d][0];
                int newC = cur[1] + dir[d][1];
                if(0 > newR || newR >= N || 0 > newC || newC >= N || visited[newR][newC]) continue;
                if((map[cur[0]][cur[1]] == 'B' && map[newR][newC] == 'B') || (map[cur[0]][cur[1]] != 'B' && map[newR][newC] != 'B')){
                    queue.add(new int[]{newR,newC});
                    visited[newR][newC] = true;
                }
            }
        }

        return cnt;
    }

    private static int normal(int r, int c){
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r,c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            cnt++;
            for (int d = 0; d < 4; d++) {
                int newR = cur[0] + dir[d][0];
                int newC = cur[1] + dir[d][1];
                if(0 > newR || newR >= N || 0 > newC || newC >= N || visited[newR][newC]) continue;
                if ((map[cur[0]][cur[1]] == map[newR][newC])) {
                    queue.add(new int[]{newR, newC});
                    visited[newR][newC] = true;
                }
            }
        }

        return cnt;
    }
}
