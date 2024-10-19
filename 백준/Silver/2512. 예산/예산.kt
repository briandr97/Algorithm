import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()

    val cities = IntArray(N)
    val citySt = StringTokenizer(br.readLine())
    for (i in 0 until N) {
        cities[i] = citySt.nextToken().toInt()
    }
    val total = br.readLine().toInt()

    if (total >= cities.sum()) {
        println(cities.max())
        return
    }

    var start = 1
    var end = 100000

    while (start + 1 < end) {
        val mid = (start + end) / 2
        val sum = cities.sumOf { if (it > mid) mid else it }
        if (sum > total) {
            end = mid
        } else {
            start = mid
        }
    }
    println(start)
}