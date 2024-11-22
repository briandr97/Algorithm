import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val dp = IntArray(n + 1)
    fun recursive(cur: Int): Int {
        if(cur == 1) return 1
        if(cur == 2) return 2
        if(dp[cur] == 0) {
            dp[cur] = (recursive(cur-1) + recursive(cur-2)) % 10007
        }
        return dp[cur]
    }
    println(recursive(n))
}
