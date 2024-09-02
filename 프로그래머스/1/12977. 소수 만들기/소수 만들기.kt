class Solution {
    var count = 0
    
    fun solution(nums: IntArray): Int {
        nums.forEachIndexed { idx: Int, value: Int ->
            recursive(idx, mutableListOf<Int>(), nums)
        }
        
        return count
    }
    
    private fun recursive(index: Int, selectedNums: List<Int>, nums: IntArray) {
        val indexAddedNums = selectedNums.toMutableList().apply { add(nums[index]) }
        
        if(indexAddedNums.size == 3) {
            if(isPrime(indexAddedNums.sumOf{it})) {
                count++
            }
            return
        }

        for(i in (index + 1) until nums.size) {
            recursive(i, indexAddedNums.toList(), nums)
        }
    }
    
    private fun isPrime(num: Int): Boolean {
        for(i in 2..num/2) {
            if(num%i==0) return false
        }
        return true
    }
}
