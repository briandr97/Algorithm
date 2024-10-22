import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine().trim())
    var N = st.nextToken().toInt()
    var L = st.nextToken().toInt()
    var I = st.nextToken().toLong()

    val comb = Array(N + 1) { LongArray(L + 1) }

    fun comb(n: Int, r: Int): Long {
        if (r == 0 || n == 0) return 1
        if (comb[n][r] == 0L) comb[n][r] = comb(n - 1, r) + comb(n - 1, r - 1)
        return comb[n][r]
    }

    val sb = StringBuilder()
    while (N-- > 0) {
        val ifCurZero = comb(N, L)
        if (I <= ifCurZero) {
            sb.append(0)
            continue
        }
        sb.append(1)
        I -= ifCurZero
        L--
    }
    println(sb.toString())
}
