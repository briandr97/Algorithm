// 5시 45분

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            long result = getTime(diffs, times, mid);
            if(result > limit) {
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        
        return left;
    }
    
    private long getTime(int[] diffs, int[] times, int level) {
        long sum = 0;
        for(int i=0; i<diffs.length; i++) {
            int diff = diffs[i];
            int curTime = times[i];
            int prevTime = 0;
            if(i > 0) prevTime = times[i-1];
            
            int repeat = diff - level;
            if(repeat < 0) repeat = 0;
            
            sum += (curTime + prevTime) * repeat + curTime;
        }
        
        return sum;
    }
}