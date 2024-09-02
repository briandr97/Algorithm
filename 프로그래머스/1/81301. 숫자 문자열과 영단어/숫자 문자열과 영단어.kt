class Solution {
    val englishMap = mapOf<String, Int>(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )
    
    fun solution(s: String): Int {
        val answer = StringBuilder()
        val temp = StringBuilder()
        s.forEach {
            if(it.isDigit()) {
                answer.append(it)
                return@forEach
            }
            
            temp.append(it)
            val tempString = temp.toString()
            if(englishMap.containsKey(tempString)) {
                answer.append(englishMap[tempString])
                temp.clear()
            }
        }
        return answer.toString().toInt()
    }
}
