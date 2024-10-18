class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        val whole = brown + yellow
        for(height in 3..Math.sqrt(whole.toDouble()).toInt()) {
            if(whole%height != 0) continue
            val width = whole / height
            val y = (width-2) * (height-2)
            val b = width*2 + height*2 - 4
            if(y == yellow && b == brown) return intArrayOf(width, height)
        }
        return intArrayOf()
    }
}