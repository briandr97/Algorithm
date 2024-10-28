import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine().trim())
    val N = st.nextToken().toInt()
    val input = Array(N) { IntArray(N) }
    repeat(N) { row ->
        input[row] = br.readLine().chunked(1).map { it.toInt() }.toIntArray()
    }
    val answer = StringBuilder()

    fun zip(sr: Int, sc: Int, size: Int) {
        val se = input[sr][sc]
        if (size == 1) {
            answer.append(se)
            return
        }

        val isZipAble = (sr until sr + size).all { r ->
            (sc until sc + size).all { c ->
                se == input[r][c]
            }
        }

        if (isZipAble) {
            answer.append(se)
        } else {
            answer.append('(')
            for (r in 0 until 2) {
                for (c in 0 until 2) {
                    zip(sr + r * size / 2, sc + c * size / 2, size / 2)
                }
            }
            answer.append(')')
        }
    }

    zip(0, 0, N)
    println(answer.toString())
}