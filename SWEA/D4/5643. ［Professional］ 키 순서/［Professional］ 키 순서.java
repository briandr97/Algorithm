  import java.util.*;
  import java.io.*;
  
  public class Solution {
      static int N, M;
      static List<Integer>[] inEdges, outEdges;
      static BitSet[] inDegrees, outDegrees;
      
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
              inDegrees = new BitSet[N + 1];
              outDegrees = new BitSet[N + 1];
              
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
          return getInDegrees(n).cardinality() + getOutDegrees(n).cardinality() == N - 1;
      }
      
      private static BitSet getInDegrees(int n) {
          if(inDegrees[n] != null) return inDegrees[n];
          BitSet bs = new BitSet(N + 1);
          
          for(int edge: inEdges[n]) {
              bs.set(edge);
              bs.or(getInDegrees(edge));
          }
          
          inDegrees[n] = bs;
          return bs;
      }
      
      private static BitSet getOutDegrees(int n) {
          if(outDegrees[n] != null) return outDegrees[n];
          BitSet bs = new BitSet(N + 1);
          
          for(int edge: outEdges[n]) {
              bs.set(edge);
              bs.or(getOutDegrees(edge));
          }

          outDegrees[n] = bs;
          return bs;
      }
  }