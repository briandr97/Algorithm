class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        var max = 0
        var min = 0
        sizes.forEach { size -> 
            val curMax = Math.max(size[0], size[1])
            val curMin = Math.min(size[0], size[1])
            max = Math.max(max, curMax)
            min = Math.max(min, curMin)
        }
        return max * min
    }
}