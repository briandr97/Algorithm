class Solution {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        var sameCount: Int = 0
        var zeroCount: Int = 0
        
        for(i in lottos.indices) {
            if(lottos[i]==0) {
                zeroCount++
                continue
            }
            for(j in win_nums.indices) {
                if(lottos[i] == win_nums[j]) {
                    sameCount++
                    continue
                }
            }
        }
        
        
        
        return intArrayOf(getRank(sameCount + zeroCount), getRank(sameCount))
    }
    
    private fun getRank(count: Int): Int {
        val result = 7-count
        return if(result > 6) return 6
        else result
    }
}