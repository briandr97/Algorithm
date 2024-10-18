import java.util.*

class Solution {
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        val mySolution = MySolution(dungeons, BooleanArray(dungeons.size), k)
        return mySolution.dfsAll()
    }
}

class MySolution(
    private val dungeons: Array<IntArray>,
    private val visited: BooleanArray,
    private val k: Int
) {
    private var max = 0
    
    fun dfsAll(): Int {
        for(i in dungeons.indices) {
            dfs(k, i, 0)
        }
        return max
    }
    
    private fun dfs(point: Int, idx: Int, depth: Int) {
        if(point < dungeons[idx][0]) {
            max = Math.max(max, depth)
            return
        }
        if(depth == dungeons.size - 1) {
            max = Math.max(max, depth + 1)
            return
        }
        visited[idx] = true
        for(i in dungeons.indices) {
            if(visited[i]) continue
            dfs(point - dungeons[idx][1], i, depth + 1)
        }
        visited[idx] = false
    }
}