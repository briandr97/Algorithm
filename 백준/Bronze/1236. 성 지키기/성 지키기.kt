import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val castle = Array<Array<String>>(N) { Array<String>(M){ "" } }
    repeat(N) {
        castle[it] = br.readLine().chunked(1).toTypedArray()
    }
    val rows: LinkedList<Int> = LinkedList<Int>()
    val columns: LinkedList<Int> = LinkedList<Int>()

    for(i in 0 until N) {
        var isEmpty = true
        for(j in 0 until M) {
            if(castle[i][j] == "X") isEmpty = false
        }
        if(isEmpty) rows.add(i)
    }

    for(i in 0 until M) {
        var isEmpty = true
        for(j in 0 until N) {
            if(castle[j][i] == "X") isEmpty = false
        }
        if(isEmpty) columns.add(i)
    }

    val answer = Math.max(rows.size, columns.size)
    println(answer)
    br.close()
}