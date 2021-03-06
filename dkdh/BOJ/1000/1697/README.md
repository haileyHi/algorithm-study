# 1697번 숨바꼭질
[문제 보러가기](https://www.acmicpc.net/problem/1697)

## 🅰 설계
수빈이가 동생을 찾아가는 방식에서 특별한 계산 방법을 발견하지 못했기 때문에   
-1, +1, x2 의 위치 변화를 모두 탐색해 최단 시간을 구하는 방법을 생각했습니다.

탐색에 있어 어떤 방법이 최선으로 이어질지 모르기 때문에 bfs를 사용했습니다.

그 전에 우선 수빈이가 동생보다 x축 상 오른쪽에 있다면, 계속해서 -1을 택하는 것이 최선의 방법이기 때문에 이 경우는 조건문으로 한 번에 결과가 나오도록 처리해줬습니다.
```jsx
// 수빈이가 동생보다 오른쪽에 있으면 무조건 -1로 계속 가야 하므로
if(N >= K) {
	System.out.print(N-K);
}else { // 아니면 탐색 시작
	bfs(N);
}
```

bfs로 최단 시간을 구하기 위해 각 정점에 대해 최단시간을 저장할 배열을 선언해줬습니다.
```jsx
public static int[] times;
```

배열이 0으로 초기화되기 때문에, 방문 여부도 이 배열로 알 수 있도록 0을 방문하지 않은 상태 0이 아니라면 방문한 상태로 정의했습니다.

따라서 초기 정점이 수빈이의 위치에 대해서 방문을 표시하기 위해 times[x]를 1로 표시해주었고, 이는 모든 시간이 +1 되는 것을 의미하므로 마지막에 구한 최단 시간을 출력할 때 -1을 해서 출력하게 됩니다.
```jsx
// visited 표시를 위해 1로 바꿔준다.
times[x] = 1;
// 루트 위치 큐에 입력
queue.add(x);
```
출력 시
```jsx
// 만약 목적 위치인 K에 다다랐다면 걸린 시간 출력하고 종료
if(cur==K) {
		System.out.print(times[cur]-1);
		break;
}
```

bfs 알고리즘은 아래와 같습니다.
1. 방문 여부를 표시하고 루트 노드를 큐에 넣는다.   
(반복 시작: 큐가 비지 않았다면)
2. 큐에서 노드를 하나 꺼냅니다.
3. 요소에 인접한 노드 중 방문하지 않은 노드를 방문 표시하고 모두 큐에 넣습니다.   
 -> 새로운 노드에 방문을 표시할 때 시간을 갱신해줍니다.   
(반복 끝)


## ✅ 후기
