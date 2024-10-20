import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val lectures = br.readLine().split(" ").map{ it.toInt() }

    var start = 0
    var end = 1000000000

    while(start+1 < end) {
        val mid = (start + end) / 2

        var sum = 0
        var bluerayCount = 1
        for(lecture in lectures) {
            if(bluerayCount > M) break
            if(lecture > mid) bluerayCount += M+1
            sum += lecture
            if(sum > mid) {
                sum = lecture
                bluerayCount++
            }
        }
        if(bluerayCount > M) start = mid
        else end = mid
    }
    println(end)
}