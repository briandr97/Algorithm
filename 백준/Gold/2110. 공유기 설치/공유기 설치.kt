import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine().trim())
    val N = st.nextToken().toInt() // 집 개수
    val C = st.nextToken().toInt() // 공유기 개수

    val houses = IntArray(N) // 집 위치
    for(i in houses.indices) {
        houses[i] = br.readLine().trim().toInt()
    }

    houses.sort()

    var start = 1 // start는 무조건 가능한 값
    var end = houses[N-1] - houses[0] + 1 // end는 무조건 불가능한 값

    while(start+1 < end) {
        val mid = (start + end) / 2
        var curC = C - 1
        var last = houses[0]
        for(h in houses) {
            if(h - last < mid) continue
            last = h
            curC--
        }
        if(curC <= 0) start = mid // 설치한 공유기 수가 C개와 같거나 더 많으면 가능하므로 간격을 mid까지 늘린다.
        else end = mid // 설치한 공유기 수가 C개보다 작다면 더 설치해야하므로 mid일때 불가능하므로 간격을 mid까지 줄인다.
    }

    println(start)
}
