import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine().trim())
    val A = st.nextToken().toInt()
    val B = st.nextToken().toInt()
    val C = st.nextToken().toInt()

    fun pow(exponent: Int): Int {
        if (exponent == 1) return A % C
        val temp = pow(exponent / 2)
        var result: Long = (temp % C).toLong() * (temp % C) % C
        if (exponent % 2 == 1) {
            result = result * (A % C) % C
        }

        return result.toInt()
    }

    println(pow(B))
}