import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val input = Array<IntArray>(N) { IntArray(3) }
    for (i in 0 until N) {
        input[i] = br.readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    }
    val dp: Array<IntArray> = Array<IntArray>(N) { IntArray(3) }

    fun recursive(idx: Int, preColor: Int): Int {
        if (idx < 0) return 0
        var min = Int.MAX_VALUE
        for (i in input[idx].indices) {
            if (i == preColor) continue
            if(dp[idx][i] == 0) {
                dp[idx][i] = recursive(idx - 1, i) + input[idx][i]
            }
            min = dp[idx][i].coerceAtMost(min)
        }
        return min
    }

    println(recursive(N - 1, -1))
}

