import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val room = Array<Array<String>>(n) { Array(n) { "" } }
    repeat(n) {
        room[it] = br.readLine().chunked(1).toTypedArray()
    }

    var rowAnswer = 0
    var columnAnswer = 0
    repeat(n) { row ->
        var consecutiveRow = 0
        var consecutiveColumn = 0
        repeat(n) { column ->
            if (room[row][column] == "X") {
                if (consecutiveRow >= 2) rowAnswer++
                consecutiveRow = 0
            } else {
                consecutiveRow++
            }

            if (room[column][row] == "X") {
                if (consecutiveColumn >= 2) columnAnswer++
                consecutiveColumn = 0
            } else {
                consecutiveColumn++
            }
        }
        if (consecutiveRow >= 2) rowAnswer++
        if (consecutiveColumn >= 2) columnAnswer++
    }
    println("$rowAnswer $columnAnswer")
    br.close()
}