import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int repeat = Integer.parseInt(br.readLine().trim());
        int[] arr = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        int sum = 0;
        int result = 0;
        for(int i=0; i<arr.length; i++) {
            sum += arr[i];
            result += sum;
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}