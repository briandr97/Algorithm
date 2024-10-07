import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numCount = Integer.parseInt(br.readLine().trim());
		int[] nums = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] ops = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
		Solution s = new Solution(numCount, nums, ops);
		s.solution(nums[0], 1);
		System.out.println(s.max);
		System.out.println(s.min);
	}
}

class Solution {
	int max = -1000000001;
	int min = 1000000001;
	
	int numCount;
	int[] nums;
	int[] ops;
	
	public Solution(int numCount, int[] nums, int[] ops) {
		this.numCount = numCount;
		this.nums = nums;
		this.ops = ops;
	}
	
	public void solution(int result, int idx) {
		if(idx == numCount) {
			if(result > max) max = result;
			if(result < min) min = result;
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(ops[i] > 0) {
				ops[i]--;
				if(i==0) solution(result + nums[idx], idx + 1);
				else if(i==1) solution(result - nums[idx], idx + 1);
				else if(i==2) solution(result * nums[idx], idx + 1);
				else solution(result / nums[idx], idx + 1);
				ops[i]++;
			}
		}
	}
}
