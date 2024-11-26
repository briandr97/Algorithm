import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val original = Array<Array<Int>>(N) { Array<Int>(M) { 0 } }
    val target = Array<Array<Int>>(N) { Array<Int>(M) { 0 } }
    repeat(N) {
        original[it] = br.readLine().chunked(1).map { it.toInt() }.toTypedArray()
    }
    repeat(N) {
        target[it] = br.readLine().chunked(1).map { it.toInt() }.toTypedArray()
    }

    fun reverse(row: Int, column: Int) {
        for (i in row until row + 3) {
            for (j in column until column + 3) {
                original[i][j] = if (original[i][j] == 1) 0 else 1
            }
        }
    }

    fun isSame(): Boolean {
        for (i in 0 until N) {
            for (j in 0 until M) {
                if (original[i][j] != target[i][j]) return false
            }
        }
        return true
    }

    if (N < 3 || M < 3) {
        if (isSame()) println(0)
        else println(-1)
        return
    }

    var answer = 0
    for (i in 0 until N - 2) {
        for (j in 0 until M - 2) {
            if (original[i][j] == target[i][j]) continue
            reverse(i, j)
            answer++
        }
    }

    if(isSame().not()) {
        println(-1)
        return
    }

    println(answer)
    br.close()
}