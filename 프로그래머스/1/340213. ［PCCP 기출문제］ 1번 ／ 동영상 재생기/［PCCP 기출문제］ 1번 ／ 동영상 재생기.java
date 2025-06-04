class Solution {
    private Time opStart;
    private Time opEnd;
    private Time cur;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        cur = new Time(pos);
        opStart = new Time(op_start);
        opEnd = new Time(op_end);
        Time tenSeconds = new Time(0, 10);
        Time videoEnd = new Time(video_len);
        
        skipOpening();
        for(int i=0; i<commands.length; i++) {
            String command = commands[i];
            if(command.equals("next")) {
                cur.plus(tenSeconds);
            } else if(command.equals("prev")) {
                cur.minus(tenSeconds);
            } else {
                throw new IllegalArgumentException();
            }
            
            if(cur.compareTo(videoEnd) > 0) {
                cur = videoEnd.clone();
            }
            skipOpening();
        }
        
        return cur.toString();
    }
    
    private void skipOpening() {
        if(cur.compareTo(opStart) >= 0 && cur.compareTo(opEnd) < 0) {
            cur = opEnd.clone();
        }
    }
}

class Time implements Comparable<Time> {
    int minute, second;
    
    public Time(String input) {
        String[] inputs = input.split(":");
        minute = Integer.parseInt(inputs[0]);
        second = Integer.parseInt(inputs[1]);                          
    }
    
    public Time(int minute, int second) {
        this.minute = minute;
        this.second = second;
    }
    
    public void plus(Time other) {
        second += other.second;
        minute += other.minute;
        
        if(second > 60) {
            second %= 60;
            minute++;
        }
    }
    
    public void minus(Time other) {
        second -= other.second;
        minute -= other.minute;
        
        if(second < 0) {
            minute--;
            second = 60 + second;
        }
        
        if(minute < 0) {
            minute = 0;
            second = 0;
        }
    }
    
    public int compareTo(Time other) {
        if(minute > other.minute) return 1;
        if(minute < other.minute) return -1;
        else {
            return Integer.compare(second, other.second);
        }
    }
    
    public Time clone() {
        return new Time(minute, second);
    }
    
    public String toString() {
        String mStr = Integer.toString(minute);
        String sStr = Integer.toString(second);
        if(mStr.length() == 1) mStr = "0" + mStr;
        if(sStr.length() == 1) sStr = "0" + sStr;
        return mStr + ":" + sStr;
    }
}