import java.io.*
import java.util.*

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val st = StringTokenizer(br.readLine().trim())
    val basketCount = st.nextToken().toInt()
    var repeatCount = st.nextToken().toInt()
    val baskets: MutableList<Int> = (0..basketCount).toMutableList()

    fun swap(n1: Int, n2: Int) {
        (0..(n2-n1)/2).forEach {
            val temp = baskets[n1 + it]
            baskets[n1 + it] = baskets[n2 - it]
            baskets[n2 - it] = temp
        }
    }

    while(repeatCount-- > 0) {
        val st2 = StringTokenizer(br.readLine().trim())
        val n1 = st2.nextToken().toInt()
        val n2 = st2.nextToken().toInt()
        swap(n1, n2)
    }

    bw.write("${baskets.slice(1..basketCount).joinToString(" ")}\n")

    bw.flush()
    bw.close()
}