import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val needs = IntArray(N)
    for(i in 0 until N) {
        needs[i] = br.readLine().toInt()
    }

    var start = needs.max()
    var end = needs.sum()

    while(start < end) {
        val mid = (start + end) / 2
        var rest = 0
        var withdraw = 0
        for(need in needs) {
            if(rest < need) {
                rest = mid
                withdraw ++
            }
            rest -= need
        }

        if(withdraw > M) {
            start = mid + 1
        } else {
            end = mid
        }
    }
    println(end)
}