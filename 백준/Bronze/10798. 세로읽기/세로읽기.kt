import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val arr = Array<String>(5) { " " }
    repeat(5) {
    	arr[it] = br.readLine().trim()
    }
    println(solution(arr))
    br.close()    
}

private fun solution(arr: Array<String>): String {
    val sb = StringBuilder()
    for(i in 0 until 15) {
        for(j in 0 until 5) {
            if(i < arr[j].length) sb.append(arr[j][i])
        }
    }
    return sb.toString()
}