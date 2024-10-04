import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val input = br.readLine().trim().toInt()
    println(solution(input))
    br.close()    
}

private fun solution(n: Int): String {
    var level = 0
    var target = n
    while(target > 0) {
        target -= ++level
    }
    
    var child: Int
    var parent: Int
    
    if(level%2 == 0) {
        child = level
        parent = 1
    } else {
        child = 1
        parent = level
    }
    while(target!=0) {
        target++
     	if(level%2 == 0) {
        	child--
            parent++
    	} else {
    	    child++
            parent--
	    }   
    }
    
    return "$child/$parent"
}