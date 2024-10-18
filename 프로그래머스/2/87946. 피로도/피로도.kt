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
        dfs(k, 0)
        return max
    }
    
    fun dfs(point: Int, depth: Int) {
        for(i in dungeons.indices) {
            if(visited[i] || point < dungeons[i][0]) continue
            visited[i] = true
            dfs(point - dungeons[i][1], depth + 1)
            visited[i] = false
        }
        max = Math.max(max, depth)
    }
}