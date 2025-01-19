import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int[] reserved = new int[200001]; // 예약자가 늦지 않았는가?
    static boolean[] visited = new boolean[200001]; // 예약자가 왔는가?
    static PriorityQueue<Person> queue = new PriorityQueue<>();
    static int n;
    static int max = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        Arrays.fill(reserved, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int reserve = Integer.parseInt(st.nextToken());
            int arrive = Integer.parseInt(st.nextToken());
            if (arrive <= reserve) reserved[reserve] = arrive;
            queue.add(new Person(reserve, arrive));
        }

        int time = 1;
        while (!queue.isEmpty()) {
            // 예약 가능 시간 범위 내에서 예약자가 늦지 않고 아직 입장하지 않았다면
            if (time <= 200000 && reserved[time] != -1 && !visited[time]) {
                visited[time] = true;
                max = Math.max(max, time - reserved[time]);
                time++;
                continue;
            }

            do {
                Person p = queue.poll();
                if (p.arrive > time) { // 다음 사람의 제일 빠른 도착시간이 현재 시간 이후라면 패스
                    queue.add(p);
                    break;
                }
                if (!visited[p.reserve]) { // 아직 식당에 들어가지 않은 사람이면 들어간다.
                    visited[p.reserve] = true;
                    max = Math.max(max, time - p.arrive);
                    break;
                }
            } while (!queue.isEmpty());
            time++;
        }

        System.out.println(max);
    }
}

class Person implements Comparable<Person> {
    int reserve;
    int arrive;

    public Person(int reserve, int arrive) {
        this.reserve = reserve;
        this.arrive = arrive;
    }

    @Override
    public int compareTo(Person other) {
        if (arrive < other.arrive) return -1;
        else if (arrive == other.arrive) return 0;
        else return 1;
    }
}