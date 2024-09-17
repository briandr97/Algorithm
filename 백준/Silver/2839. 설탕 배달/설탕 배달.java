import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine().trim());
        int answer = 0;
        
        while(true) {
            if(num==0) break;
            if(num<0) {
                answer=-1;
                break;
            }
            if(num%5==0) {
                answer += num/5;
                break;
            } else {
                num -= 3;
                answer++;
            }
        }
        
        System.out.println(answer);
    }
}