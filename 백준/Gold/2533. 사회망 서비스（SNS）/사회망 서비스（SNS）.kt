import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val adj = List<MutableList<Int>>(N) { mutableListOf<Int>() }
    repeat(N - 1) {
        val st = StringTokenizer(br.readLine())
        val f = st.nextToken().toInt() - 1
        val s = st.nextToken().toInt() - 1
        adj[f].add(s)
        adj[s].add(f)
    }
    val graph = Graph(N, adj)
    println(graph.solution())
}

class Graph(
    N: Int,
    private val adjs: List<MutableList<Int>>,
) {
    private var visited = BooleanArray(N)
    private val dp = Array(N) { IntArray(2) } // 얼리어답터면 columnIdx가 1, 아니면 0

    fun solution(): Int {
        dfs(0)
        return dp[0].min()
    }

    private fun dfs(idx: Int) {
        visited[idx] = true
        for (adj in adjs[idx]) {
            if (visited[adj]) continue
            dfs(adj)
            dp[idx][0] += dp[adj][1]
            dp[idx][1] += dp[adj].min()
        }
        dp[idx][1] += 1
    }
}