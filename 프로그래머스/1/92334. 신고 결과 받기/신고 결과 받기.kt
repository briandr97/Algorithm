class Solution {      
    fun solution(idList: Array<String>, report: Array<String>, k: Int): IntArray {
        return report.asSequence()
            .distinct()
            .map { it.split(" ").run { get(0) to get(1) }}
            .groupBy { it.second }
            .filter { it.value.size >= k }
            .map { it.value }
            .flatten()
            .groupingBy { it.first }
            .eachCount()
            .run { idList.map { getOrDefault(it, 0) }.toIntArray() }
    }
}