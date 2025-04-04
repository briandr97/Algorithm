  import java.util.*;
  import java.io.*;
  
  public class Solution {
      static int N, M;
      static List<Integer>[] inEdges, outEdges;
      static boolean[] visited;
      
      public static void main(String[] args) throws IOException {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          StringBuilder sb = new StringBuilder();
          StringTokenizer st = null;
          
          int T = Integer.parseInt(br.readLine());
          for(int tc=1; tc<=T; tc++) {
              sb.append("#").append(tc).append(" ");
              N = Integer.parseInt(br.readLine());
              M = Integer.parseInt(br.readLine());
              
              inEdges = new List[N+1]; for(int i=1; i<=N; i++) inEdges[i] = new ArrayList<>();
              outEdges = new List[N+1]; for(int i=1; i<=N; i++) outEdges[i] = new ArrayList<>();
              
              for(int i=0; i<M; i++) {
                  st = new StringTokenizer(br.readLine());
                  int start = Integer.parseInt(st.nextToken());
                  int end = Integer.parseInt(st.nextToken());
                  
                  inEdges[end].add(start);
                  outEdges[start].add(end);
              }
              
              int count = 0;
              for(int i=1; i<=N; i++) {
                  if(isOrdered(i)) count++;
              }

              sb.append(count).append("\n");
          }
          
          System.out.println(sb);
      }
      
      private static boolean isOrdered (int n) {
          visited = new boolean[N+1];
          int in = getInDegrees(n);
          
          visited = new boolean[N+1];
          int out = getOutDegrees(n);
          
          return in + out == N - 1;
      }
      
      private static int getInDegrees(int n) {
          visited[n] = true;
          int count = 0;
          
          for(int edge: inEdges[n]) {
              if(visited[edge]) continue;
              count += getInDegrees(edge) + 1;
          }
          
          return count;
      }
      
      private static int getOutDegrees(int n) {
          visited[n] = true;
          int count = 0;
          
          for(int edge: outEdges[n]) {
              if(visited[edge]) continue;
              count += getOutDegrees(edge) + 1;
          }

          return count;
      }
  }