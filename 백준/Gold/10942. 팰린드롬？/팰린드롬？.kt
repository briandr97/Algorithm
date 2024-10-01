import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    
    val size = br.readLine().trim().toInt()
   	val input = arrayOf(0) + br.readLine().trim().split(" ").map { it.toInt() }
    var repeat = br.readLine().toInt()
    
    val dp: Array<IntArray> = Array(size + 1) { IntArray(size + 1) }
    val visited: Array<BooleanArray> = Array(size + 1) { BooleanArray(size + 1) }
    
    fun recursive(n1: Int, n2: Int): Int {
        if(visited[n1][n2]) return dp[n1][n2]
        if(n1 == n2) return 1
        if(input[n1] == input[n2]) {
            if(n2 - n1 == 1) return 1
            dp[n1][n2] = recursive(n1 + 1, n2 - 1)            
        }
        visited[n1][n2] = true
        return dp[n1][n2]
    }
    
    while(repeat-- > 0) {
        val st = StringTokenizer(br.readLine())
        val n1 = st.nextToken().toInt()
        val n2 = st.nextToken().toInt()
        
        bw.write("${recursive(n1, n2)}\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
}