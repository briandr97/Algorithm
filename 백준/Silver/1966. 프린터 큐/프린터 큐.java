import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(br.readLine().trim());
        
        while(repeat-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int count = Integer.parseInt(st.nextToken());
            int targetIndex = Integer.parseInt(st.nextToken());
            int answer = 0;
            
            LinkedList<Integer> input = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toCollection(LinkedList::new));
            LinkedList<Integer> indices = IntStream.range(0, count).boxed().collect(Collectors.toCollection(LinkedList::new));
            LinkedList<Integer> importances = (LinkedList<Integer>) input.clone();
            importances.sort(Comparator.reverseOrder());
            
            while(true) {
                int inputValue = input.poll();
                int index = indices.poll();
                int importance = importances.peek();
                
                if(inputValue != importance) {
                    input.add(inputValue);
                    indices.add(index);
                    continue;
                }
            
                importances.poll();
                answer++;
                if(index == targetIndex) {
                    System.out.println(answer);
                    break;
                }
            }
        }
    }
}