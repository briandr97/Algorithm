import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    // taller[i]: i번 학생보다 키가 큰 학생들의 목록
    // shorter[i]: i번 학생보다 키가 작은 학생들의 목록
    static ArrayList<Integer>[] taller;
    static ArrayList<Integer>[] shorter;
    
    // 메모이제이션을 위한 BitSet 배열 (1번부터 N번 학생까지)
    static BitSet[] memoTaller;
    static BitSet[] memoShorter;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            M = Integer.parseInt(br.readLine().trim());
            
            // 그래프 배열 초기화 (학생 번호는 1부터 N까지)
            taller = new ArrayList[N + 1];
            shorter = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                taller[i] = new ArrayList<>();
                shorter[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // a가 b보다 작으므로, a의 taller 리스트에 b를 추가
                // b의 shorter 리스트에 a를 추가
                taller[a].add(b);
                shorter[b].add(a);
            }
            
            // 메모이제이션 배열 초기화
            memoTaller = new BitSet[N + 1];
            memoShorter = new BitSet[N + 1];
            
            int result = 0;
            // 각 학생별로 자신보다 큰 학생 집합과 작은 학생 집합의 크기를 구함
            for (int i = 1; i <= N; i++) {
                BitSet bsTaller = getTaller(i);
                BitSet bsShorter = getShorter(i);
                // 두 집합의 합이 N-1이면 i의 순서가 결정됨
                if (bsTaller.cardinality() + bsShorter.cardinality() == N - 1)
                    result++;
            }
            
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
    
    // i번 학생보다 키가 큰 학생들의 전이 폐쇄를 BitSet으로 반환 (메모이제이션 적용)
    static BitSet getTaller(int i) {
        if (memoTaller[i] != null) return memoTaller[i];
        BitSet bs = new BitSet(N + 1);
        for (int j : taller[i]) {
            // j는 바로 i보다 큰 학생이므로 추가
            bs.set(j);
            // 재귀적으로 j번 학생보다 큰 학생들을 모두 추가 (이미 계산된 경우 재활용)
            bs.or(getTaller(j));
        }
        memoTaller[i] = bs;
        return bs;
    }
    
    // i번 학생보다 키가 작은 학생들의 전이 폐쇄를 BitSet으로 반환 (메모이제이션 적용)
    static BitSet getShorter(int i) {
        if (memoShorter[i] != null) return memoShorter[i];
        BitSet bs = new BitSet(N + 1);
        for (int j : shorter[i]) {
            bs.set(j);
            bs.or(getShorter(j));
        }
        memoShorter[i] = bs;
        return bs;
    }
}
