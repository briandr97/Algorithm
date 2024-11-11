import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashSet<Integer> nums = new HashSet<>();
        int repeat = Integer.parseInt(br.readLine().trim());
        
        while(repeat-- > 0) {
            String[] input = br.readLine().trim().split(" ");
            String op = input[0];
            Integer num;
            if(input.length == 1) {
                num = null;
            } else {
                num = Integer.parseInt(input[1]);
            }
            if(op.equals("add")) {
                nums.add(num);
            } else if(op.equals("check")) {
                if(nums.contains(num)) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }
            } else if(op.equals("remove")) {
                nums.remove(num);
            } else if(op.equals("toggle")) {
                if(nums.contains(num)) {
                    nums.remove(num);
                } else {
                    nums.add(num);
                }
            } else if(op.equals("all")) {
                nums.clear();
                for(int i=1; i<=20; i++) {
                    nums.add(i);
                }
            } else if(op.equals("empty")) {
                nums.clear();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}