import java.util.*
import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val minHeap = PriorityQueue<Int>()
    var N = br.readLine().toInt()
    while(N-- > 0) {
        val x = br.readLine().toInt()
        if(x != 0) {
            minHeap.add(x)
            continue
        }
        println(if(minHeap.isEmpty()) 0 else minHeap.poll())
    }
}