import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val input = br.readLine().trim()
    println(solution(input))
    br.close()    
}

private fun solution(s: String): String {
    val arr = IntArray(26)
    s.forEach {
        var idx = it.code
        if(it in 'a'..'z') idx -= 'a'.code
        if(it in 'A'..'Z') idx -= 'A'.code
        arr[idx] += 1
    }
	
    var max = 0
    var maxIdx = 0
    var maxCount = 0
    for((idx, e) in arr.withIndex()) { 
        if(e > max) {
            max = e
            maxIdx = idx
            maxCount = 1
            continue
        }
        if(e == max) {
            maxCount++
        }
    }
    
	return if(maxCount > 1) "?"
    else ('A' + maxIdx).toString()
}