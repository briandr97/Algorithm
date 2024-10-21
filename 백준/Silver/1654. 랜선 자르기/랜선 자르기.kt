package org.example

import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val K = st.nextToken().toInt()
    val N = st.nextToken().toInt()
    val lines = IntArray(K)
    for(i in 0 until K) {
        lines[i] = br.readLine().trim().toInt()
    }

    var start: Long = 0
    var end: Long = Int.MAX_VALUE.toLong() + 1

    while(start+1 < end) {
        val mid: Long = (start + end) / 2
        var sum: Long = 0
        for(line in lines) {
            sum += line / mid
        }
        if(sum >= N) {
            start = mid
        } else {
            end = mid
        }
    }


    println(start)
}