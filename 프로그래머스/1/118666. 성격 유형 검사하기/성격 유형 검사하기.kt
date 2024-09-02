class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        val scoreMap = mutableMapOf<String, Int>(
            "RT" to 0,
            "CF" to 0,
            "JM" to 0,
            "AN" to 0,
        )
        
        survey.zip(choices.map { it - 4 }).forEach {
            if(scoreMap.containsKey(it.first)) {
                scoreMap[it.first] = scoreMap[it.first]!! + it.second
            } else {
                val key = it.first.reversed()
                scoreMap[key] = scoreMap[key]!! - it.second
            }
        }
        
        return scoreMap.map { (key: String, value: Int) ->
            if(value <= 0) key[0] else key[1]
        }.joinToString("")
    }
}