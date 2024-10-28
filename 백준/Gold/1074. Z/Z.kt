import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine().trim())
    val N = st.nextToken().toInt()
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    
    var count = 0
    fun solution(sr: Int, sc: Int, size: Int) {
        if (sr == R && sc == C) {
            println(count)
            return
        }
        if (size == 0) return

        val rowDivider = sr + size - 1
        val columnDivider = sc + size - 1
        
        if (R > rowDivider && C > columnDivider) {
            count += size * size * 3
            solution(sr + size, sc + size, size / 2)
        } else if (R > rowDivider && C <= columnDivider) {
            count += size * size * 2
            solution(sr + size, sc, size / 2)
        } else if (R <= rowDivider && C > columnDivider) {
            count += size * size * 1   
            solution(sr, sc + size, size / 2)
        } else {
            solution(sr, sc, size / 2)
        }
    }

    val boardSize = Math.pow(2.0, N.toDouble()).toInt()
    solution(0, 0, boardSize / 2)
}