class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val todayDate: Date = Date.getDateFromString(today)
        val privaciesWithDate: List<Pair<String, Date>> = privacies.map { 
            val arr = it.split(" ")
            arr[1] to Date.getDateFromString(arr[0])
        }
        val termMap = terms.map { 
            val arr = it.split(" ")
            arr[0] to arr[1].toInt()
        }.toMap()
        
        return privaciesWithDate.mapIndexed { idx: Int, (term: String, date: Date) -> 
            val endDate = date.plusMonth(termMap[term]!!)
            if(endDate < todayDate) idx + 1 else null
        }.filterNotNull().toIntArray()
    }
}

data class Date(
    val year: Int,
    val month: Int,
    val day: Int,
): Comparable<Date> {
    fun plusMonth(count: Int): Date {
        var newYear = year
        var newMonth = month + count
        var newDay = day - 1
        
        if(newMonth > 12) {
            if(newMonth%12 == 0) {
                newYear += newMonth/12 - 1
                newMonth = 12
            } else {
                newYear += newMonth/12
                newMonth = newMonth%12
            }
        }
        
        if(newDay == 0) {
            newMonth--
            newDay = 28
            
            if(newMonth == 0) {
                newYear--
                newMonth = 12
            }
        }
        
        return Date(newYear, newMonth, newDay)
    }
    
    override fun compareTo(other: Date): Int {
        return if(year > other.year) {
            1
        } else if(year < other.year) {
            -1
        } else if(month > other.month) {
            1
        } else if(month < other.month) { 
            -1
        } else if(day > other.day) {
            1
        } else if(day < other.day) {
            -1
        } else {
            0
        }
    }
    
    companion object {
        fun getDateFromString(date: String): Date {
            val arr = date.split(".")
            return Date(
                year = arr[0].toInt(),
                month = arr[1].toInt(),
                day = arr[2].toInt(),
            )
        }
    }
}