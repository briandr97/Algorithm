class Solution {
    val indexMap = mutableMapOf<String, Int>()
    
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        var reported = MutableList(id_list.size) { 0 }
        val answer = IntArray(id_list.size) { 0 }
        initMap(id_list)
        
        val int_id_list = report.map { 
            val arr = it.split(" ")
            indexMap[arr[0]]!! to indexMap[arr[1]]!!
        }.distinct()
            
        int_id_list.forEach { reported[it.second] += 1 }
        reported = reported.mapIndexedNotNull {idx: Int, e: Int ->
            if(e>=k) idx
            else null
        }.toMutableList()
        
        int_id_list.forEach {
            if(it.second in reported) answer[it.first] += 1
        }
        
        return answer
    }
    
    private fun initMap(id_list: Array<String>) {
        id_list.forEachIndexed { idx: Int, e: String ->
            indexMap[e] = idx
        }
    }
}