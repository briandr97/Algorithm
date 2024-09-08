import java.io.*;
import java.util.*;

class Main {
    private static int[][] apt = new int[15][15];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int repeat = Integer.parseInt(br.readLine().trim());
        for(int i=0; i<repeat; i++) {
            int floor = Integer.parseInt(br.readLine().trim());
            int roomNumber = Integer.parseInt(br.readLine().trim());
            initApt(floor, roomNumber);
            
            StringBuilder answer = new StringBuilder();
            answer.append(apt[floor][roomNumber]).append("\n");
            bw.write(answer.toString());
            bw.flush();
            
            apt = new int[15][15];
        }
        bw.close();
    }
    
    private static void initApt(int floorInput, int roomNumberInput) {
        for(int floor=0; floor<=floorInput; floor++){
            for(int roomNumber=1; roomNumber<=roomNumberInput; roomNumber++) {
                if(floor==0) {
                    apt[floor][roomNumber] = roomNumber;
                    continue;
                }
                if(roomNumber==1) {
                    apt[floor][roomNumber] = 1;
                    continue;
                }
                apt[floor][roomNumber] = apt[floor-1][roomNumber] + apt[floor][roomNumber-1];
            }   
        }
    }
}