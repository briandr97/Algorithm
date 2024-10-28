import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine().trim())
    val N = st.nextToken().toInt()
    val input = Array(N) { IntArray(N) }
    repeat(N) { row ->
        input[row] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    val answer = IntArray(3)

    fun count(startRow: Int, startColumn: Int, size: Int) {
        val first = input[startRow][startColumn]
        if (size == 1) {
            answer[first + 1]++
            return
        }

        val isPaper = (startRow until startRow + size).all { r ->
            (startColumn until startColumn + size).all { c ->
                first == input[r][c]
            }
        }

        if (isPaper) {
            answer[first + 1]++
        } else {
            val newSize = size / 3
            repeat(3) { r ->
                repeat(3) { c ->
                    count(startRow + r * newSize, startColumn + c * newSize, newSize)
                }
            }
        }
    }

    count(0, 0, N)
    answer.forEach { println(it) }
}