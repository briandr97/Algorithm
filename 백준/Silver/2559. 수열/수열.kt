import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }

    var max = Int.MIN_VALUE
    var sum = 0
    var left = 0

    for (right in arr.indices) {
        sum += arr[right]
        if (right - left + 1 < K) continue
        if (right - left + 1 > K) {
            sum -= arr[left]
            left++
        }

        max = Math.max(max, sum)
    }

    println(max)
}