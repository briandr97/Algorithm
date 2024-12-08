import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n: Int = br.readLine().toInt()
    val points: List<Int> = br.readLine().split(" ").map { it.toInt() }
    val sortedPoints: List<Int> = points.sorted().distinct()
    val map = mutableMapOf<Int, Int>()
    for (i in sortedPoints.indices) {
        map[sortedPoints[i]] = i
    }
    val sb = StringBuilder()
    for(i in 0 until n) {
        sb.append(map[points[i]])
        if(i < n - 1) sb.append(" ")
    }
    sb.append("\n")
    BufferedWriter(OutputStreamWriter(System.`out`)).use {
        it.write(sb.toString())
        it.flush()
    }
    br.close()
}