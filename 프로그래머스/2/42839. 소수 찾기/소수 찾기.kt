class Solution {
    val set = mutableSetOf<String>()
    lateinit var visited: BooleanArray
    
    fun solution(numbers: String): Int {
        visited = BooleanArray(numbers.length)
        for(i in numbers.indices) {
            permutation(i, "", numbers)    
        }
        return set.filter { isPrime(it.toInt()) }.size
    }
    
    private fun permutation(index: Int, s: String, numbers: String) {
        if(index >= numbers.length) return
        
        val cur: String = removeFrontZero(s + numbers[index])
        if(cur.isNotEmpty()) set.add(cur)
        
        visited[index] = true
        for(i in numbers.indices) {
            if(visited[i]) continue
            permutation(i, cur, numbers)
        }
        visited[index] = false
    }
    
    private fun removeFrontZero(s: String): String {
        for(i in s.indices) {
            if(s[i] != '0') return s.substring(i, s.length)
        }
        return ""
    }
    
    private fun isPrime(n: Int): Boolean {
        if(n < 2) return false
        val end = Math.sqrt(n.toDouble()).toInt()
        for(i in 2..end) {
            if(n%i==0) return false
        }
        return true
    }
}