import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val trees = IntArray(N)
    val treesSt = StringTokenizer(br.readLine())
    for (i in 0 until N) {
        trees[i] = treesSt.nextToken().toInt()
    }

    var start: Long = 0
    var end: Long = 1000000000

    while (start + 1 < end) {
        val mid = (start + end) / 2
        val sum: Long = trees.sumOf { if (it - mid >= 0) it - mid else 0 }
        if (sum >= M) {
            start = mid
        } else {
            end = mid
        }
    }
    println(start)
}
