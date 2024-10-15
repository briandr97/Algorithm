import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val baskets = (1..N).toList().toIntArray()
    for (i in 0 until M) {
        val st2 = StringTokenizer(br.readLine())
        val first = st2.nextToken().toInt() - 1
        val second = st2.nextToken().toInt() - 1
        val temp = baskets[first]
        baskets[first] = baskets[second]
        baskets[second] = temp
    }

    println(baskets.joinToString(" "))
}