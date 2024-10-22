import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val adj = List<MutableList<Int>>(N) { mutableListOf<Int>() }
    repeat(N-1) {
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
    private val N: Int,
    private val adjs: List<MutableList<Int>>,
) {
    private var visited = BooleanArray(N)
    private val dp = Array(N) { IntArray(2) } // 얼리어답터면 columnIdx가 1, 아니면 0
    private val nodes = Stack<Int>() // depth 순으로 정렬된 Stack. push하면 가장 depth가 높은 노트부터 나온다.

    fun solution(): Int {
        initDepths(0)
        visited = BooleanArray(N)
        while(!nodes.isEmpty()) {
            rr(nodes.pop())
        }
        return dp[0].min()
    }

    private fun rr(idx: Int) {
        visited[idx] = true
        val whenImEarly = adjs[idx].filter { visited[it] }.sumOf{ dp[it].min() } + 1
        val whenImNotEarly = adjs[idx].filter { visited[it] }.sumOf { dp[it][1] }
        dp[idx][0] = whenImNotEarly
        dp[idx][1] = whenImEarly
    }

    private fun initDepths(idx: Int) {
        visited[idx] = true
        nodes.push(idx)
        for(adj in adjs[idx]) {
            if(visited[adj]) continue
            initDepths(adj)
        }
    }
}
