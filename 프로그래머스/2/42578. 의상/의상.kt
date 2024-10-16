class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        val map = mutableMapOf<String, Int>()
        clothes.forEach { clothe -> 
            val key = clothe[1]
            map[key] = map.getOrDefault(key, 0) + 1
        }
        
        return map.values.fold(1) { acc, count -> acc * (count + 1)} - 1
    }
}